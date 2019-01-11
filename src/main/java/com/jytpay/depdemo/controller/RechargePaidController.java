package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.util.EncryJsonUtil;
import com.jytpay.depdemo.service.RechargePaidService;
import com.jytpay.depdemo.vo.BaseJsonReqVo;
import com.jytpay.depdemo.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RechargePaidController {

    private static final Logger log = LoggerFactory.getLogger(RechargePaidController.class);

    @Autowired
    private RechargePaidService rechargePaidService;


    @RequestMapping("/cg1045")
    @ResponseBody
    public Result rechargePaid(
            @RequestParam(value = "merchantNo", required = false) String merchantNo,
            @RequestParam(value = "acctNo", required = false) String acctNo,
            @RequestParam(value = "amount", required = false) String amount,
            @RequestParam(value = "incomeAmt", required = false) String incomeAmt,
            @RequestParam(value = "callbackUrl", required = false) String callbackUrl,
            @RequestParam(value = "responsePath", required = false) String responsePath) {
        Result result = new Result();
        if (StringUtils.isBlank(merchantNo) ||
                StringUtils.isBlank(acctNo) ||
                StringUtils.isBlank(amount) ||
                StringUtils.isBlank(incomeAmt) ||
                StringUtils.isBlank(callbackUrl) ||
                StringUtils.isBlank(responsePath)) {
            result.setStatus("001");
            result.setReqMsg("请求参数不能为空");
            return result;
        }
        Map<String, String> params = new HashMap<>();
        params.put("merchantNo", merchantNo);
        params.put("acctNo", acctNo);
        params.put("amount", amount);
        params.put("incomeAmt", incomeAmt);
        params.put("callbackUrl", callbackUrl);
        params.put("responsePath", responsePath);
        params.put("tradeCode", "CG1045");
        BaseJsonReqVo baseJsonReqVo = rechargePaidService.getReqJson(params);
        Map<String, String> encryReqJson = EncryJsonUtil.encryReqJson(baseJsonReqVo);
        result.setStatus("000");
        result.setReqMsg(baseJsonReqVo);
        result.setEncryMsg(encryReqJson);
        return result;
    }
}
