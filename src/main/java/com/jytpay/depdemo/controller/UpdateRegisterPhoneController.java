package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.util.EncryJsonUtil;
import com.jytpay.depdemo.service.CustomerService;
import com.jytpay.depdemo.vo.BaseJsonReqVo;
import com.jytpay.depdemo.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UpdateRegisterPhoneController {


    private final CustomerService customerService;

    @Autowired
    public UpdateRegisterPhoneController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/cg1049")
    @ResponseBody
    public Result update(
            @RequestParam(value = "merchantNo", required = false) String merchantNo,
            @RequestParam(value = "custNo", required = false) String custNo,
            @RequestParam(value = "oldMobile", required = false) String oldMobile,
            @RequestParam(value = "newMobile", required = false) String newMobile,
            @RequestParam(value = "callbackUrl", required = false) String callbackUrl,
            @RequestParam(value = "responsePath", required = false) String responsePath){
        Result result = new Result();
        if (StringUtils.isBlank(merchantNo) ||
                StringUtils.isBlank(custNo) ||
                StringUtils.isBlank(oldMobile) ||
                StringUtils.isBlank(newMobile) ||
                StringUtils.isBlank(callbackUrl) ||
                StringUtils.isBlank(responsePath)) {
            result.setStatus("001");
            result.setReqMsg("请求参数不能为空");
            return result;
        }
        Map<String, String> params = new HashMap<>();
        params.put("merchantNo", merchantNo);
        params.put("custNo", custNo);
        params.put("oldMobile", oldMobile);
        params.put("newMobile", newMobile);
        params.put("callbackUrl", callbackUrl);
        params.put("responsePath", responsePath);
        params.put("tradeCode", "CG1049");
        BaseJsonReqVo baseJsonReqVo = customerService.updateRegisterPhone(params);
        Map<String, String> encryReqJson = EncryJsonUtil.encryReqJson(baseJsonReqVo);
        result.setStatus("000");
        result.setReqMsg(baseJsonReqVo);
        result.setEncryMsg(encryReqJson);
        return result;
    }
}
