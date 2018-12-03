package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.Util.EncryJsonUtil;
import com.jytpay.depdemo.Util.StringUtil;
import com.jytpay.depdemo.service.AuthorizationService;
import com.jytpay.depdemo.vo.BaseJsonReqVo;
import com.jytpay.depdemo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 存管测试demo
 *
 * @author chencong@jytpay.com
 * @since 2018/12/3 14:22
 */

@RestController
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/cg1050")
    public Result cg1050(
            @RequestParam(value = "merchantNo", required = false) String merchantNo,
            @RequestParam(value = "custNo", required = false) String custNo,
            @RequestParam(value = "authType", required = false) String authType,
            @RequestParam(value = "expiryTime", required = false) String expiryTime,
            @RequestParam(value = "amount", required = false) String amount,
            @RequestParam(value = "callbackUrl", required = false) String callbackUrl,
            @RequestParam(value = "responsePath", required = false) String responsePath
    ) {
        Result result = new Result();
        if (StringUtil.isBlank(merchantNo)
                || StringUtil.isBlank(authType)
                || StringUtil.isBlank(expiryTime)
                || StringUtil.isBlank(amount)
                || StringUtil.isBlank(callbackUrl)
                || StringUtil.isBlank(responsePath)) {
            result.setStatus("001");
            result.setReqMsg("请求参数不能为空");
            return result;
        }
        Map<String, String> params = new HashMap<>();
        params.put("merchantNo", merchantNo);
        params.put("custNo", custNo);
        params.put("authType", authType);
        params.put("expiryTime", expiryTime);
        params.put("amount", amount);
        params.put("callbackUrl", callbackUrl);
        params.put("responsePath", responsePath);
        params.put("tradeCode", "CG1050");
        BaseJsonReqVo baseJsonReqVo = authorizationService.getReqJsonResetPayPwd(params);
        Map<String, String> encryReqJson = EncryJsonUtil.encryReqJson(baseJsonReqVo);
        result.setStatus("000");
        result.setReqMsg(baseJsonReqVo);
        result.setEncryMsg(encryReqJson);
        return result;
    }

}
