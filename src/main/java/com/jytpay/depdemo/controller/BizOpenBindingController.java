package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.service.OpenAndBindCardService;
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
public class BizOpenBindingController {

    @Autowired
    private OpenAndBindCardService openAndBindCardService;

    @RequestMapping("/cg1044")
    @ResponseBody
    public Result openBinding(
            @RequestParam(value = "callbackUrl", required = false) String callbackUrl,
            @RequestParam(value = "responsePath", required = false) String responsePath,
            @RequestParam(value = "registerPhone", required = false) String registerPhone,
            @RequestParam(value = "custType", required = false) String custType,
            @RequestParam(value = "merchantNo", required = false) String merchantNo) {
        Result result = new Result<>();
        if (StringUtils.isBlank(callbackUrl) ||
                StringUtils.isBlank(responsePath) ||
                StringUtils.isBlank(registerPhone) ||
                StringUtils.isBlank(custType) ||
                StringUtils.isBlank(merchantNo)) {
            result.setStatus("001");
            result.setData("请求参数不能为空");
            return result;
        }

        Map<String, String> params = new HashMap<>();
        params.put("callbackUrl", callbackUrl);
        params.put("responsePath", responsePath);
        params.put("registerPhone", registerPhone);
        params.put("custType", custType);
        params.put("tradeCode", "CG1044");
        params.put("merchantNo", merchantNo);
        BaseJsonReqVo baseJsonReqVo = openAndBindCardService.getReqJson(params);
        result.setStatus("000");
        result.setData(baseJsonReqVo);
        return result;
    }

}
