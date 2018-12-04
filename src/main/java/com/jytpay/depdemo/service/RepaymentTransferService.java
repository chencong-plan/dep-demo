package com.jytpay.depdemo.service;

import com.jytpay.depdemo.vo.BaseJsonReqVo;

import java.util.Map;

/**
 * @author chencong@jytpay.com
 */
public interface RepaymentTransferService {
    /**
     * 还款转账
     *
     * @param params 请求参数
     * @return 返回请求报文
     */
    BaseJsonReqVo getReqJsonPayment(Map<String, String> params);
}
