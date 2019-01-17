package com.jytpay.depdemo.vo;

import lombok.Data;

/**
 * @author chencong@jytpay.com
 * @since 2019/1/11 10:28
 */
@Data
public class CreateSubjectReqVo {

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * 标的名称
     */
    private String subjectName;

    /**
     * 满标金额
     */
    private String subjectAmt;

    /**
     * 标的费率
     */
    private String subjectRate;

    /**
     * 标的有效期,废了
     */
    private String subjectTerm;

    /**
     * 商户标的编号
     */
    private String merSubjectNo;

    /**
     * 融资人账户
     */
    private String payeeAcctNo;

    /**
     * 标的用途
     */
    private String subjectPurpose;

    /**
     * 标的类型
     */
    private String subjectType;

    /**
     * 标识号
     */
    private String identificationNo;

    /**
     * 服务费费率
     */
    private String serviceRate;

    /**
     * 担保人名称
     */
    private String guarantor;

    /**
     * 担保人账户
     */
    private String guarantorAcct;

    /**
     * 标的开始时间
     */
    private String subjectStartDate;

    /**
     * 标的结束时间
     */
    private String subjectEndDate;
}
