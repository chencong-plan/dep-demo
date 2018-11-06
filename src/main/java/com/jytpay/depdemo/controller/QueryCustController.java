package com.jytpay.depdemo.controller;

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
public class QueryCustController {

    @Autowired
    private CustomerService customerService;

    /**
     * 实时模式——查询客户信息
     *
     * @param requestUrl        请求地址
     * @param merchantNo 商户号
     * @param certNo     客户号
     * @return 返回请求报文和响应报文
     */
    @RequestMapping("/cg2006")
    @ResponseBody
    public Result queryCust(
            @RequestParam(value = "requestUrl", required = false) String requestUrl,
            @RequestParam(value = "merchantNo", required = false) String merchantNo,
            @RequestParam(value = "certNo", required = false) String certNo) {
        Result result = new Result();
        if (StringUtils.isBlank(requestUrl) ||
                StringUtils.isBlank(merchantNo) ||
                StringUtils.isBlank(certNo)) {
            result.setStatus("001");
            result.setReqMsg("请求参数不能为空");
            return result;
        }
        Map<String, String> params = new HashMap<>();
        params.put("merchantNo", merchantNo);
        params.put("certNo", certNo);
        params.put("tradeCode", "CG2006");
        BaseJsonReqVo baseJsonReqVo = customerService.getReqJson(params);
        String response = customerService.getRespJson(baseJsonReqVo, requestUrl);
        result.setStatus("000");
        result.setReqMsg(baseJsonReqVo);
        result.setEncryMsg(response);

        return result;
    }
}
