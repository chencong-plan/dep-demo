package com.jytpay.depdemo.service;

import com.jytpay.depdemo.vo.BaseJsonReqVo;

import java.util.Map;

/**
 * @author chencong
 */
public interface SubjectService {
    BaseJsonReqVo getReqJsonT0(Map<String, String> params);
}
