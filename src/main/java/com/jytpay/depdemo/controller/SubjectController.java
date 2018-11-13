package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.Util.EncryJsonUtil;
import com.jytpay.depdemo.service.SubjectService;
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

/**
 * @author chencong
 */
@Controller
public class SubjectController {


    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/cg1052")
    @ResponseBody
    public Result subjectBuy(
            @RequestParam(value = "merchantNo", required = false) String merchantNo,
            @RequestParam(value = "acctNo", required = false) String acctNo,
            @RequestParam(value = "subjectNo", required = false) String subjectNo,
            @RequestParam(value = "amount", required = false) String amount,
            @RequestParam(value = "callbackUrl", required = false) String callbackUrl,
            @RequestParam(value = "responsePath", required = false) String responsePath) {
        Result result = new Result();
        if (StringUtils.isBlank(merchantNo) ||
                StringUtils.isBlank(acctNo) ||
                StringUtils.isBlank(subjectNo) ||
                StringUtils.isBlank(amount) ||
                StringUtils.isBlank(callbackUrl) ||
                StringUtils.isBlank(responsePath)) {
            result.setStatus("001");
            result.setReqMsg("请求参数不能为空");
            return result;
        }
        Map<String, String> params = new HashMap<>();
        params.put("merchantNo", merchantNo);
        params.put("acctNo", acctNo);
        params.put("subjectNo", subjectNo);
        params.put("amount", amount);
        params.put("callbackUrl", callbackUrl);
        params.put("responsePath", responsePath);
        params.put("tradeCode", "CG1052");
        BaseJsonReqVo baseJsonReqVo = subjectService.getReqJsonT0(params);
        Map<String, String> encryReqJson = EncryJsonUtil.encryReqJson(baseJsonReqVo);
        result.setStatus("000");
        result.setReqMsg(baseJsonReqVo);
        result.setEncryMsg(encryReqJson);
        return result;
    }
}
