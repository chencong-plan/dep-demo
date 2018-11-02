package com.jytpay.depdemo.service;

import com.jytpay.depdemo.vo.BaseJsonReqVo;

import java.util.Map;

public interface ReplaceCardService {


    BaseJsonReqVo getReqJson(Map<String, String> params);
}
