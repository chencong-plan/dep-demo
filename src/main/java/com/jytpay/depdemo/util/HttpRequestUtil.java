package com.jytpay.depdemo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jytpay.depdemo.vo.BaseHttpParamsReq;
import com.jytpay.depdemo.vo.BaseHttpParamsRes;
import com.jytpay.depdemo.vo.BaseJsonReqVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <B>系统名称: </B>收付款系统<BR>
 * <B>模块名称: </B><BR>
 * <B>中文类名: </B><BR>
 * <B>概要说明: </B><BR>
 *
 * @author chencong@jytpay.com
 * @since 2018/11/26 16:04
 */
public class HttpRequestUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static String getRespJson(BaseJsonReqVo baseJsonReqVo, String url) {
        BaseHttpParamsReq params = EncryJsonUtil.encryReqBean(baseJsonReqVo);
        MockClient mockClient = new MockClient();
        String jsonRes = null;
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(url);
            Response response = target.request().post(Entity.entity(params, MediaType.APPLICATION_JSON_TYPE));
            String result = response.readEntity(String.class);
            //解析报文
            BaseHttpParamsRes baseHttpParamsRes = JSON.parseObject(result, new TypeReference<BaseHttpParamsRes>() {
            });
            byte[] key = mockClient.decryptKey(baseHttpParamsRes.getKeyEnc());
            jsonRes = mockClient.decrytXml(baseHttpParamsRes.getJsonEnc(), key);
            if (!mockClient.verifyMsgSign(jsonRes, baseHttpParamsRes.getSign())) {
                log.info("验签失败，订单号:{},报文信息:{}", baseHttpParamsRes.getMerOrderNo(), jsonRes);
            }
        } catch (Exception e) {
            log.info("信息异常:{}", baseJsonReqVo.getHead().getMerOrderNo(), e);
            e.printStackTrace();
        }
        log.info("请求地址:{},响应信息:{}", url, jsonRes);
        return jsonRes;
    }

}
