package com.jytpay.depdemo.service;

import com.jytpay.depdemo.vo.BaseJsonReqVo;

import java.util.Map;

public interface PayPwdService {
    BaseJsonReqVo getReqJsonUpdatePayPwd(Map<String, String> params);

    BaseJsonReqVo getReqJsonResetPayPwd(Map<String,String> params);
}
