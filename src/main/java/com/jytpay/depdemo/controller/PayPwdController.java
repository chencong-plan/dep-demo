package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.util.EncryJsonUtil;
import com.jytpay.depdemo.service.PayPwdService;
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
public class PayPwdController {

    private final PayPwdService payPwdService;

    @Autowired
    public PayPwdController(PayPwdService payPwdService) {
        this.payPwdService = payPwdService;
    }


    /**
     * 支付密码修改
     *
     * @param merchantNo   商户号
     * @param custNo       客户号
     * @param pswordCode   支付密码类型
     * @param callbackUrl  回调地址
     * @param responsePath 跳转地址
     * @return 返回报文
     */
    @RequestMapping("/cg1048")
    @ResponseBody
    public Result updatePayPass(
            @RequestParam(value = "merchantNo", required = false) String merchantNo,
            @RequestParam(value = "custNo", required = false) String custNo,
            @RequestParam(value = "pswordCode", required = false) String pswordCode,
            @RequestParam(value = "callbackUrl", required = false) String callbackUrl,
            @RequestParam(value = "responsePath", required = false) String responsePath) {
        Result result = new Result();
        if (StringUtils.isBlank(merchantNo) ||
                StringUtils.isBlank(custNo) ||
                StringUtils.isBlank(pswordCode) ||
                StringUtils.isBlank(callbackUrl) ||
                StringUtils.isBlank(responsePath)) {
            result.setStatus("001");
            result.setReqMsg("请求参数不能为空");
            return result;
        }
        Map<String, String> params = new HashMap<>();
        params.put("merchantNo", merchantNo);
        params.put("custNo", custNo);
        params.put("pswordCode", pswordCode);
        params.put("callbackUrl", callbackUrl);
        params.put("responsePath", responsePath);
        params.put("tradeCode", "CG1048");
        BaseJsonReqVo baseJsonReqVo = payPwdService.getReqJsonUpdatePayPwd(params);
        Map<String, String> encryReqJson = EncryJsonUtil.encryReqJson(baseJsonReqVo);
        result.setStatus("000");
        result.setReqMsg(baseJsonReqVo);
        result.setEncryMsg(encryReqJson);
        return result;
    }

    /**
     * 支付密码重置
     *
     * @param merchantNo   商户号
     * @param custNo       客户号
     * @param callbackUrl  回调地址
     * @param responsePath 跳转地址
     * @return 返回报文
     */
    @RequestMapping("/cg1055")
    @ResponseBody
    public Result resetPayPwd(
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
        params.put("tradeCode", "CG1055");
        BaseJsonReqVo baseJsonReqVo = payPwdService.getReqJsonResetPayPwd(params);
        Map<String, String> encryReqJson = EncryJsonUtil.encryReqJson(baseJsonReqVo);
        result.setStatus("000");
        result.setReqMsg(baseJsonReqVo);
        result.setEncryMsg(encryReqJson);
        return result;
    }

}
