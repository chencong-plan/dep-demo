package com.jytpay.depdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.jytpay.depdemo.Util.DateTimeUtils;
import com.jytpay.depdemo.Util.GenerateSequenceUtil;
import com.jytpay.depdemo.service.TransferService;
import com.jytpay.depdemo.vo.BaseJsonReqVo;
import com.jytpay.depdemo.vo.BaseReqHeadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransferServiceImpl implements TransferService {

    private static final Logger log = LoggerFactory.getLogger(TransferServiceImpl.class);

    @Override
    public BaseJsonReqVo getReqJsonPayment(Map<String, String> params) {
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
        body.put("subjectNo", params.get("subjectNo"));
        body.put("payerAcctNo", params.get("payerAcctNo"));
        body.put("amount", params.get("amount"));
        body.put("capital", params.get("capital"));
        body.put("incomeAmt", params.get("incomeAmt"));
        body.put("list", params.get("list"));
        body.put("callbackUrl", params.get("callbackUrl"));
        body.put("responsePath", params.get("responsePath"));

        baseJsonReqVo.setHead(headVo);
        baseJsonReqVo.setBody(body);

        String jsonReq = JSON.toJSONString(baseJsonReqVo, true);
        log.info("还款转账订单号:{},请求信息:{}", merOrderNo, jsonReq);
        return baseJsonReqVo;
    }
}
