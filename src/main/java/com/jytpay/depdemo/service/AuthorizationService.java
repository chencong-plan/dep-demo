package com.jytpay.depdemo.service;

import com.jytpay.depdemo.vo.BaseJsonReqVo;

import java.util.Map;

/**
 * <B>系统名称: </B>收付款系统<BR>
 * <B>模块名称: </B><BR>
 * <B>中文类名: </B><BR>
 * <B>概要说明: </B><BR>
 *
 * @author chencong@jytpay.com
 * @since 2018/12/3 14:29
 */
public interface AuthorizationService {

    BaseJsonReqVo getReqJsonResetPayPwd(Map<String, String> params);

    BaseJsonReqVo getReqJson(Map<String, String> params);
}
