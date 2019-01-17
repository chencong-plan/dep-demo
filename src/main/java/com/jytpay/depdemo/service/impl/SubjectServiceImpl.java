package com.jytpay.depdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.jytpay.depdemo.util.DateTimeUtils;
import com.jytpay.depdemo.util.GenerateSequenceUtil;
import com.jytpay.depdemo.service.SubjectService;
import com.jytpay.depdemo.util.HttpRequestUtil;
import com.jytpay.depdemo.vo.BaseJsonReqVo;
import com.jytpay.depdemo.vo.BaseReqHeadVo;
import com.jytpay.depdemo.vo.CreateSubjectReqVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SubjectServiceImpl implements SubjectService {

    private Logger log = LoggerFactory.getLogger(SubjectServiceImpl.class);


    @Override
    public BaseJsonReqVo getReqJsonT0(Map<String, String> params) {
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
        body.put("acctNo", params.get("acctNo"));
        body.put("subjectNo", params.get("subjectNo"));
        body.put("amount", params.get("amount"));
        body.put("callbackUrl", params.get("callbackUrl"));
        body.put("responsePath", params.get("responsePath"));

        baseJsonReqVo.setHead(headVo);
        baseJsonReqVo.setBody(body);

        String jsonReq = JSON.toJSONString(baseJsonReqVo, true);
        log.info("标的购买订单号:{},请求信息:{}", merOrderNo, jsonReq);
        return baseJsonReqVo;
    }

    @Override
    public BaseJsonReqVo getReqJson(CreateSubjectReqVo subjectReqVo) {
        BaseJsonReqVo baseJsonReqVo = new BaseJsonReqVo();
        String merOrderNo = GenerateSequenceUtil.generateProjectNoSequence();
        BaseReqHeadVo headVo = new BaseReqHeadVo();
        headVo.setMerchantNo(subjectReqVo.getMerchantNo());
        headVo.setMerOrderNo(merOrderNo);
        headVo.setTradeCode("CG1012");
        headVo.setTradeDate(DateTimeUtils.getNowDateStr(DateTimeUtils.DATE_FORMAT_YYYYMMDD));
        headVo.setTradeTime(DateTimeUtils.getNowDateStr(DateTimeUtils.DATETIME_FORMAT_HHMMSS));
        headVo.setTradeType("00");
        headVo.setVersion("1.0.0");

        Map<String, String> body = new HashMap<>();
        body.put("merSubjectNo",subjectReqVo.getMerSubjectNo());
        body.put("subjectName",subjectReqVo.getSubjectName());
        body.put("subjectAmt",subjectReqVo.getSubjectAmt());
        body.put("subjectRate",subjectReqVo.getSubjectRate());
        body.put("subjectPurpose",subjectReqVo.getSubjectPurpose());
        body.put("payeeAcctNo",subjectReqVo.getPayeeAcctNo());
        body.put("subjectType",subjectReqVo.getSubjectType());
        body.put("identificationNo",subjectReqVo.getIdentificationNo());
        body.put("serviceRate",subjectReqVo.getServiceRate());
        body.put("guarantor",subjectReqVo.getGuarantor());
        body.put("guarantorAcct",subjectReqVo.getGuarantorAcct());
        body.put("subjectStartDate",subjectReqVo.getSubjectStartDate());
        body.put("subjectEndDate",subjectReqVo.getSubjectEndDate());
        baseJsonReqVo.setHead(headVo);
        baseJsonReqVo.setBody(body);

        String jsonReq = JSON.toJSONString(baseJsonReqVo, true);
        log.info("标的创建订单号:{},请求信息:{}", merOrderNo, jsonReq);
        return baseJsonReqVo;

    }
}
