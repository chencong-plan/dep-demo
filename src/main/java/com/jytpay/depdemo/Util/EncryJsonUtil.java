package com.jytpay.depdemo.Util;

import com.alibaba.fastjson.JSON;
import com.jytpay.depdemo.Util.sign.DESHelper;
import com.jytpay.depdemo.vo.BaseHttpParamsReq;
import com.jytpay.depdemo.vo.BaseJsonReqVo;

import java.util.HashMap;
import java.util.Map;

/**
 * 对请求信息进行加密和解密
 */
public class EncryJsonUtil {

    public static Map<String, String> encryReqJson(BaseJsonReqVo baseJsonReqVo) {
        MockClient client = new MockClient();
        String json = JSON.toJSONString(baseJsonReqVo);
        //加密密钥
        byte[] desKey = DESHelper.generateDesKey();
        Map<String, String> params = new HashMap<>();
        //请求报文商户号
        params.put("merchantNo", baseJsonReqVo.getHead().getMerchantNo());
        //请求报文订单号
        params.put("merOrderNo", baseJsonReqVo.getHead().getMerOrderNo());
        //请求报文加密
        params.put("jsonEnc", client.encryptJson(json, desKey));
        //请求报文密钥加密
        params.put("keyEnc", client.encryptKey(desKey));
        //请求报文签名
        params.put("sign", client.signMsg(json));
        return params;
    }

    /**
     * 加密请求bean
     *
     * @param baseJsonReqVo 请求报文
     */
    public static BaseHttpParamsReq encryReqBean(BaseJsonReqVo baseJsonReqVo) {
        BaseHttpParamsReq paramsReq = new BaseHttpParamsReq();
        MockClient client = new MockClient();
        String json = JSON.toJSONString(baseJsonReqVo);
        //加密密钥
        byte[] desKey = DESHelper.generateDesKey();

        paramsReq.setMerchantNo(baseJsonReqVo.getHead().getMerchantNo());
        paramsReq.setMerOrderNo(baseJsonReqVo.getHead().getMerOrderNo());
        paramsReq.setJsonEnc(client.encryptJson(json, desKey));
        paramsReq.setKeyEnc(client.encryptKey(desKey));
        paramsReq.setSign(client.signMsg(json));
        return paramsReq;
    }

}
