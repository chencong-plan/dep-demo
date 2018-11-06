package com.jytpay.depdemo.service;

import com.jytpay.depdemo.vo.BaseJsonReqVo;

import java.util.Map;

public interface WithdrawService {

    BaseJsonReqVo getReqJsonT0(Map<String, String> params);

    BaseJsonReqVo getReqJsonT1(Map<String, String> params);
}
