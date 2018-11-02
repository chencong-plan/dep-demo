package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.Util.EncryJsonUtil;
import com.jytpay.depdemo.service.UnBindCardService;
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
public class UnbindCardController {

    @Autowired
    private UnBindCardService unBindCardService;

    @RequestMapping("/cg1057")
    @ResponseBody
    public Result unbind(
            @RequestParam(value = "merchantNo", required = false) String merchantNo,
            @RequestParam(value = "custNo", required = false) String custNo,
            @RequestParam(value = "callbackUrl", required = false) String callbackUrl,
            @RequestParam(value = "responsePath", required = false) String responsePath) {
        Result result = new Result();
        if (StringUtils.isBlank(merchantNo) ||
                StringUtils.isBlank(custNo) ||
                StringUtils.isBlank(callbackUrl) ||
                StringUtils.isBlank(responsePath)) {
            result.setStatus("001");
            result.setReqMsg("请求参数不能为空");
            return result;
        }
        Map<String, String> params = new HashMap<>();
        params.put("merchantNo", merchantNo);
        params.put("custNo", custNo);
        params.put("callbackUrl", callbackUrl);
        params.put("responsePath", responsePath);
        params.put("tradeCode", "CG1057");
        BaseJsonReqVo baseJsonReqVo = unBindCardService.getReqJson(params);
        //TODO 在这里将组装好的请求bean进行加密
        Map<String, String> encryReqJson = EncryJsonUtil.encryReqJson(baseJsonReqVo);
        result.setStatus("000");
        result.setReqMsg(baseJsonReqVo);
        result.setEncryMsg(encryReqJson);
        return result;
    }

}
