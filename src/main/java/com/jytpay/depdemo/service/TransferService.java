package com.jytpay.depdemo.service;

import com.jytpay.depdemo.vo.BaseJsonReqVo;

import java.util.Map;

/**
 * 实时转账接口
 * @author chencong@jytpay.com
 * @since 2018/12/3 15:33
 */
public interface TransferService {

    /**
     * 转账接口参数组装
     * @param params 参数
     * @return 返回组装参数
     */
    BaseJsonReqVo getReqJsonPayment(Map<String, String> params);

    /**
     * 发送转账请求，返回json数据
     * @param baseJsonReqVo
     * @param requestUrl
     * @return
     */
    String getRespJson(BaseJsonReqVo baseJsonReqVo, String requestUrl);
}
