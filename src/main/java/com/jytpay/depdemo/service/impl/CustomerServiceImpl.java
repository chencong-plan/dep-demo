package com.jytpay.depdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jytpay.depdemo.Util.*;
import com.jytpay.depdemo.service.CustomerService;
import com.jytpay.depdemo.vo.BaseHttpParamsReq;
import com.jytpay.depdemo.vo.BaseHttpParamsRes;
import com.jytpay.depdemo.vo.BaseJsonReqVo;
import com.jytpay.depdemo.vo.BaseReqHeadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public BaseJsonReqVo getReqJson(Map<String, String> params) {
        BaseJsonReqVo baseJsonReqVo = new BaseJsonReqVo();
        String merOrderNo = GenerateSequenceUtil.generateProjectNoSequence();
        BaseReqHeadVo headVo = new BaseReqHeadVo();
        headVo.setMerchantNo(params.get("merchantNo"));
        headVo.setMerOrderNo(merOrderNo);
        headVo.setTradeCode(params.get("tradeCode"));
        headVo.setTradeDate(DateTimeUtils.getNowDateStr(DateTimeUtils.DATE_FORMAT_YYYYMMDD));
        headVo.setTradeTime(DateTimeUtils.getNowDateStr(DateTimeUtils.DATETIME_FORMAT_HHMMSS));
        headVo.setTradeType("00");
        headVo.setVersion("1.0.0");

        Map<String, String> body = new HashMap<>();
        body.put("certNo", params.get("certNo"));

        baseJsonReqVo.setHead(headVo);
        baseJsonReqVo.setBody(body);

        String jsonReq = JSON.toJSONString(baseJsonReqVo, true);
        log.info("查询客户信息订单号:{},请求信息:{}", merOrderNo, jsonReq);
        return baseJsonReqVo;
    }

    @Override
    public String getRespJson(BaseJsonReqVo baseJsonReqVo,String url) {
       BaseHttpParamsReq params  = EncryJsonUtil.encryReqBean(baseJsonReqVo);
       MockClient mockClient = new MockClient();
        String jsonRes = null;
        try{
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(url);
            Response response = target.request().post(Entity.entity(params, MediaType.APPLICATION_JSON_TYPE));
            String result = response.readEntity(String.class);
            //解析报文
            BaseHttpParamsRes baseHttpParamsRes = JSON.parseObject(result,new TypeReference<BaseHttpParamsRes>(){});
            byte[] key = mockClient.decryptKey(baseHttpParamsRes.getKeyEnc());
            jsonRes = mockClient.decrytXml(baseHttpParamsRes.getJsonEnc(),key);
            if(!mockClient.verifyMsgSign(jsonRes,baseHttpParamsRes.getSign())){
                log.info("c查询客户信息验签失败，订单号:{},报文信息:{}",baseHttpParamsRes.getMerOrderNo(),jsonRes);
            }
        }catch (Exception e){
            log.info("查询客户信息异常:{}",baseJsonReqVo.getHead().getMerOrderNo(),e);
            e.printStackTrace();
        }
        log.info("请求地址:{},响应信息:{}",url,jsonRes);
        return jsonRes;
    }

    @Override
    public BaseJsonReqVo updateRegisterPhone(Map<String, String> params) {
        BaseJsonReqVo baseJsonReqVo = new BaseJsonReqVo();
        String merOrderNo = GenerateSequenceUtil.generateProjectNoSequence();
        BaseReqHeadVo headVo = new BaseReqHeadVo();
        headVo.setMerchantNo(params.get("merchantNo"));
        headVo.setMerOrderNo(merOrderNo);
        headVo.setTradeCode(params.get("tradeCode"));
        headVo.setTradeDate(DateTimeUtils.getNowDateStr(DateTimeUtils.DATE_FORMAT_YYYYMMDD));
        headVo.setTradeTime(DateTimeUtils.getNowDateStr(DateTimeUtils.DATETIME_FORMAT_HHMMSS));
        headVo.setTradeType("00");
        headVo.setVersion("1.0.0");

        Map<String, String> body = new HashMap<>();
        body.put("custNo", params.get("custNo"));
        body.put("oldMobile", params.get("oldMobile"));
        body.put("newMobile", params.get("newMobile"));
        body.put("callbackUrl", params.get("callbackUrl"));
        body.put("responsePath", params.get("responsePath"));

        baseJsonReqVo.setHead(headVo);
        baseJsonReqVo.setBody(body);

        String jsonReq = JSON.toJSONString(baseJsonReqVo, true);
        log.info("注册手机号修改申请订单号:{},请求信息:{}", merOrderNo, jsonReq);
        return baseJsonReqVo;
    }
}
