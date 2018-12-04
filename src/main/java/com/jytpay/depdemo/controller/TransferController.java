package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.Util.StringUtil;
import com.jytpay.depdemo.service.TransferService;
import com.jytpay.depdemo.vo.BaseJsonReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 实时接口，转账CG1008
 *
 * @author chencong@jytpay.com
 * @since 2018/12/3 15:27
 */
@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    /**
     * 实时模式，转账接口CG1008
     *
     * @param merchantNo  商户编号
     * @param payerAcctNo 转出方账户号码
     * @param payeeAcctNo 转入方账户号码
     * @param amount      转账金额
     * @param payType     转账类型 00 个人-个人 01 个人-商户  02 商户-个人
     * @param requestUrl  接口请求地址
     * @return 返回响应报文
     */
    @PostMapping("/cg1008")
    public String cg1008(
            @RequestParam(value = "merchantNo", required = false) String merchantNo,
            @RequestParam(value = "payerAcctNo", required = false) String payerAcctNo,
            @RequestParam(value = "payeeAcctNo", required = false) String payeeAcctNo,
            @RequestParam(value = "amount", required = false) String amount,
            @RequestParam(value = "payType", required = false) String payType,
            @RequestParam(value = "requestUrl",required = false) String requestUrl) {
        if (StringUtil.isBlank(merchantNo)
                || StringUtil.isBlank(payerAcctNo)
                || StringUtil.isBlank(payeeAcctNo)
                || StringUtil.isBlank(amount)
                || StringUtil.isBlank(payType)) {
            return "参数不能为空";
        }

        Map<String, String> params = new HashMap<>();
        params.put("merchantNo", merchantNo);
        params.put("payerAcctNo", payerAcctNo);
        params.put("payeeAcctNo", payeeAcctNo);
        params.put("amount",amount);
        params.put("payType",payType);
        params.put("tradeCode", "CG1008");
        BaseJsonReqVo baseJsonReqVo = transferService.getReqJsonPayment(params);
        String response = transferService.getRespJson(baseJsonReqVo, requestUrl);
//        result.setStatus("000");
//        result.setReqMsg(baseJsonReqVo);
//        result.setEncryMsg(response);
        return response;
    }

}
