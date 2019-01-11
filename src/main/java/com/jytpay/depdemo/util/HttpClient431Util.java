package com.jytpay.depdemo.util;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;

import com.alibaba.fastjson.JSON;


/**
 * <B>系统名称: </B>存管系统<BR>
 * <B>模块名称: </B>渠道层<BR>
 * <B>中文类名: </B>http请求工具类<BR>
 * <B>概要说明: </B>为渠道实现进行服务的http请求工具类，包含Post、Get等系列请求方式<BR>
 *
 * @author chencong@jytpay.com
 * @since 2018年10月16日 上午11:29:15
 */
public class HttpClient431Util {

    private static final Logger logger = Logger.getLogger(HttpClient431Util.class);

    private static final RequestConfig config;

    //连接时间为10秒
    private static final RequestConfig configForNotify;

    public static final String DEFAULT_RES_CHARSET = "UTF-8";

    public static final String DEFAULT_SEND_CHARSET = "UTF-8";

    static {
        config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(60000).build();
    }

    static {
        configForNotify = RequestConfig.custom().setConnectTimeout(10000).
                setSocketTimeout(10000).setConnectionRequestTimeout(10000).setStaleConnectionCheckEnabled(true).build();
    }

    public static String doPost(Map<String, String> params, String url) {
        return doPost(params, url, DEFAULT_SEND_CHARSET, DEFAULT_RES_CHARSET);
    }

    /**
     * 发送短信/通知
     *
     * @param bean 请求数据
     * @param url  请求地址
     * @return
     */
    @Async
    public static String jsonPost(Object bean, String url) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Response response = target.request().post(Entity.entity(bean, MediaType.APPLICATION_JSON_TYPE));

        String res;
        try {
            if (response.getStatus() != 200) {
                logger.error("发送请求的响应状态是" + response.getStatus());
                throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
            }
            res = response.readEntity(String.class);
        } finally {
            response.close();
            client.close();
        }
        return res;
    }

    /**
     * 通知超时时间为10秒
     *
     * @param dataContent
     * @param contentType
     * @param contentCharset
     * @param resCharset
     * @param url
     * @return
     */
    public static String doPostContentForNotify(String dataContent, String contentType, String contentCharset, String resCharset, String url) {
        String result = null;
        CloseableHttpClient httpClient = getSingleSSLConnection(configForNotify);
        CloseableHttpResponse response = null;
        if (StringUtil.isBlank(url)) {
            throw new RuntimeException("url doesn't exists");
        }
        try {
            HttpPost httpPost = new HttpPost(url);

            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Content-Type", contentType);
            httpPost.addHeader("Connection", "close");
            logger.info("HTTP请求参数：" + dataContent);
            HttpEntity reqentity = new StringEntity(dataContent, ContentType.create(contentType, contentCharset));
            httpPost.setEntity(reqentity);

            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("请求发送的状态码是:" + statusCode);
            if (statusCode != 200) {
                httpPost.abort();
                throw new Exception("HttpClient,error status code :" + statusCode);
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, resCharset == null ? DEFAULT_RES_CHARSET : resCharset);
            }
            EntityUtils.consume(entity);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("HttpClient connect fail");
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 创建单向ssl的连接
     *
     * @return
     * @throws Exception
     */
    private static CloseableHttpClient getSingleSSLConnection(RequestConfig config) {
        CloseableHttpClient httpClient = null;

        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null, new TrustStrategy() {
                        @Override
                        public boolean isTrusted(
                                X509Certificate[] paramArrayOfX509Certificate,
                                String paramString) throws CertificateException {
                            return true;
                        }
                    }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslContext,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
                    .setDefaultRequestConfig(config).build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpClient;

    }

    /**
     * HTTP Post 获取内容
     *
     * @param params     请求的参数
     * @param url        请求的url地址 ?之前的地址
     * @param reqCharset 编码格式
     * @param resCharset 编码格式
     * @return 页面内容
     */
    public static String doPost(Map<String, String> params, String url, String reqCharset, String resCharset) {
        CloseableHttpClient httpClient = getSingleSSLConnection(config);
        CloseableHttpResponse response = null;
        if (StringUtil.isBlank(url)) {
            return null;
        }
        String result = null;
        try {
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Connection", "close");
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, reqCharset == null ? DEFAULT_SEND_CHARSET : reqCharset));
            }
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                logger.error("状态码非200, 为:" + statusCode);
                httpPost.abort();
                throw new Exception();
            }
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity, resCharset == null ? DEFAULT_RES_CHARSET : resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //TODO LOG
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    /**
     * https
     *
     * @param params
     * @param url
     * @param reqCharset
     * @param resCharset
     * @return
     */
    public static String httpsPostJSON(Map<String, String> params, String url, String reqCharset, String resCharset) {
        CloseableHttpClient httpClient = getSingleSSLConnection(config);
        CloseableHttpResponse response = null;
        if (StringUtil.isBlank(url)) {
            return null;
        }
        String result = null;
        try {
            String reqStr = JSON.toJSONString(params);

            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            httpPost.addHeader("Connection", "close");

            StringEntity stringEntiry = new StringEntity(reqStr, reqCharset);
            httpPost.setEntity(stringEntiry);

            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                logger.error("状态码非200, 为:" + statusCode);
                httpPost.abort();
                throw new Exception();
            }
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity, resCharset == null ? DEFAULT_RES_CHARSET : resCharset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null)
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }
}

