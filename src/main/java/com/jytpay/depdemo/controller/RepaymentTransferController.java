package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.util.EncryJsonUtil;
import com.jytpay.depdemo.service.RepaymentTransferService;
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
 * 还款转账
 * @author chencong@jytpay.com
 */
@Controller
public class RepaymentTransferController {

    private final RepaymentTransferService repaymentTransferService;

    @Autowired
    public RepaymentTransferController(RepaymentTransferService repaymentTransferService) {
        this.repaymentTransferService = repaymentTransferService;
    }


    /**
     * 还款转账
     *
     * @param merchantNo   商户号
     * @param payerAcctNo  融资人账号号码
     * @param amount       还款总金额
     * @param capital      还款本金
     * @param incomeAmt    商户服务费
     * @param list         投资人列表(payeeAcctNo投资人账户，amount还款金额)
     * @param callbackUrl  回调地址
     * @param responsePath 跳转地址
     * @return 返回报文
     */
    @RequestMapping("/cg1053")
    @ResponseBody()
    public Result paymentTransfer(
            @RequestParam(value = "merchantNo", required = false) String merchantNo,
            @RequestParam(value = "subjectNo", required = false) String subjectNo,
            @RequestParam(value = "payerAcctNo", required = false) String payerAcctNo,
            @RequestParam(value = "amount", required = false) String amount,
            @RequestParam(value = "capital", required = false) String capital,
            @RequestParam(value = "incomeAmt", required = false) String incomeAmt,
            @RequestParam(value = "list", required = false) String list,
            @RequestParam(value = "callbackUrl", required = false) String callbackUrl,
            @RequestParam(value = "responsePath", required = false) String responsePath) {
        Result result = new Result();
        if (StringUtils.isBlank(merchantNo) ||
                StringUtils.isBlank(subjectNo) ||
                StringUtils.isBlank(payerAcctNo) ||
                StringUtils.isBlank(amount) ||
                StringUtils.isBlank(capital) ||
                StringUtils.isBlank(incomeAmt) ||
                StringUtils.isBlank(list) ||
                StringUtils.isBlank(callbackUrl) ||
                StringUtils.isBlank(responsePath)) {
            result.setStatus("001");
            result.setReqMsg("请求参数不能为空");
            return result;
        }
        Map<String, String> params = new HashMap<>();
        params.put("merchantNo", merchantNo);
        params.put("subjectNo", subjectNo);
        params.put("payerAcctNo", payerAcctNo);
        params.put("amount", amount);
        params.put("capital", capital);
        params.put("incomeAmt", incomeAmt);
        params.put("list", list);
        params.put("callbackUrl", callbackUrl);
        params.put("responsePath", responsePath);
        params.put("tradeCode", "CG1053");
        BaseJsonReqVo baseJsonReqVo = repaymentTransferService.getReqJsonPayment(params);
        Map<String, String> encryReqJson = EncryJsonUtil.encryReqJson(baseJsonReqVo);
        result.setStatus("000");
        result.setReqMsg(baseJsonReqVo);
        result.setEncryMsg(encryReqJson);
        return result;
    }

}
