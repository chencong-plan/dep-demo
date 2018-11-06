package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.Util.EncryJsonUtil;
import com.jytpay.depdemo.Util.MockClient;
import com.jytpay.depdemo.Util.sign.CryptoUtils;
import com.jytpay.depdemo.service.OpenAndBindCardService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BizOpenBindingController {

    private static Logger log = LoggerFactory.getLogger(BizOpenBindingController.class);

    @Autowired
    private OpenAndBindCardService openAndBindCardService;

    /**
     * @param requestUrl    请求地址
     * @param callbackUrl   回调地址
     * @param responsePath  跳转地址
     * @param registerPhone 注册手机号
     * @param custType      客户类型
     * @param merchantNo    商户号
     * @return 返回请求报文和加密报文
     */
    @RequestMapping("/cg1044")
    @ResponseBody
    public Result openBinding(
            @RequestParam(value = "requestUrl", required = false) String requestUrl,
            @RequestParam(value = "callbackUrl", required = false) String callbackUrl,
            @RequestParam(value = "responsePath", required = false) String responsePath,
            @RequestParam(value = "registerPhone", required = false) String registerPhone,
            @RequestParam(value = "custType", required = false) String custType,
            @RequestParam(value = "merchantNo", required = false) String merchantNo) {
        Result result = new Result();
        if (StringUtils.isBlank(requestUrl) ||
                StringUtils.isBlank(callbackUrl) ||
                StringUtils.isBlank(responsePath) ||
                StringUtils.isBlank(registerPhone) ||
                StringUtils.isBlank(custType) ||
                StringUtils.isBlank(merchantNo)) {
            result.setStatus("001");
            result.setReqMsg("请求参数不能为空");
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
        Map<String, String> encryReqJson = EncryJsonUtil.encryReqJson(baseJsonReqVo);
        result.setStatus("000");
        result.setReqMsg(baseJsonReqVo);
        result.setEncryMsg(encryReqJson);
        return result;
    }


    @RequestMapping("/cg1044/callback")
    @ResponseBody
    public Result openBindingCall(HttpServletRequest request) {
        Result result = new Result();
        result.setStatus("000");
        String json = decrytMsg(request);
        log.info(json);
        return result;
    }

    private String decrytMsg(HttpServletRequest request) {
        String merchantNo = request.getParameter("merchantNo");
        String merOrderNo =request.getParameter("merOrderNo");
        String keyEnc = request.getParameter("keyEnc");
        String jsonEnc = request.getParameter("jsonEnc");
        String sign = request.getParameter("sign");

        MockClient mockClient = new MockClient();
        String json = null;
        try {
            byte[] desKey = mockClient.decryptKey(keyEnc);
            json = CryptoUtils.desDecryptFromHex(jsonEnc,desKey);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

}
