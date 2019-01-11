package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.util.EncryJsonUtil;
import com.jytpay.depdemo.util.HttpRequestUtil;
import com.jytpay.depdemo.util.StringUtil;
import com.jytpay.depdemo.service.AuthorizationService;
import com.jytpay.depdemo.vo.BaseJsonReqVo;
import com.jytpay.depdemo.vo.Result;
import com.jytpay.depdemo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 授权取消CG1030
     *
     * @param merchantNo 商户号
     * @param custNo     客户编号
     * @param authType   委托类型
     *                   <ul>
     *                   <li>标的购买CG1007</li>
     *                   <li>还款转账CG1010</li>
     *                   <li>购买撤销CG1014</li>
     *                   <li>代偿转账CG1025</li>
     *                   <li>商户放款CG1021</li>
     *                   <li>转账接口CG1008</li>
     *                   </ul>
     * @return
     */
    @PostMapping("/cg1030")
    public String authorizationCancel(@RequestParam(value = "merchantNo", required = false) String merchantNo,
                                      @RequestParam(value = "custNo", required = false) String custNo,
                                      @RequestParam(value = "authType", required = false) String authType,
                                      @RequestParam(value = "requestUrl", required = false) String url) {
        if (StringUtil.isBlank(merchantNo) || StringUtil.isBlank(custNo) || StringUtil.isBlank(authType)) {
            return "参数不能为空";
        }
        Map<String, String> params = new HashMap<>();
        params.put("merchantNo", merchantNo);
        params.put("custNo", custNo);
        params.put("authType", authType);
        params.put("tradeCode", "CG1030");
        BaseJsonReqVo baseJsonReqVo = authorizationService.getReqJson(params);
        String response = HttpRequestUtil.getRespJson(baseJsonReqVo, url);
        return response;
    }

    @RequestMapping("/checkLogin.json")
    public String checkLogin(User user) {
        //这里直接拿username 和  password进行你要做的后续操作
        System.out.println(user.getUsername() + " : " + user.getPassword());
        return "success";
    }

}
