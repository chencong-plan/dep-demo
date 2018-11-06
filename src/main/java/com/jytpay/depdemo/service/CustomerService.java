package com.jytpay.depdemo.service;

import com.jytpay.depdemo.vo.BaseJsonReqVo;

import java.util.Map;

public interface CustomerService {
    BaseJsonReqVo getReqJson(Map<String, String> params);

    String getRespJson(BaseJsonReqVo baseJsonReqVo,String url);

    BaseJsonReqVo updateRegisterPhone(Map<String,String> params);
}
