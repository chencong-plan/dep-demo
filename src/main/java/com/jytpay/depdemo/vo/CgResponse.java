package com.jytpay.depdemo.vo;

import com.jytpay.depdemo.util.PlatformErrorCode;

import java.io.Serializable;

/**
 *<B>系统名称：</B>存管系统<BR>
 *<B>模块名称：</B>渠道层<BR>
 *<B>中文类名：</B>业务系统调用统一响应bean<BR>
 *<B>概要说明：</B>业务系统调用统一响应bean<BR>
 * @author chencong@jytpat.com
 * @since 2018-12-10 10:00:54
 */
public class CgResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 901629064397797622L;

	/**
	 * 成功返回码 000
	 */
	public static final String SUCCESS_RESPONSE_CODE = "000";
	
	/**
	 * 失败返回码  001
	 */
	public static final String FAILED_RESPONSE_CODE = "001";
	
	/**
	 * 处理中返回码  002
	 */
	public static final String DOING_RESPONSE_CODE = "002";
	
	/**
	 * 发送短信返回码 003（通知业务层进行短信发送）
	 */
	public static final String SEND_SMS_RESPONSE_CODE = "003";
	
	/**
	 * 业务流水号
	 */
    private String bizFlow;

    /**
     * 渠道订单号
     */
    private String bsnOrderNo;
    
    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 交易响应码
     */
    private String tradeCode;

    /**
     * 交易响应描述
     */
    private String tradeDesc;
    
    /**
     * 交易响应流水号（渠道订单号）
     */
    private String tradeFlow;
    
    /**
     * 交易所走渠道编号
     */
    private String channelCode;
    
    /**
     * 渠道类型 00普通渠道  01协议渠道  枚举ChannelTypeEnums
     */
    private String channelType;

    /**
     * 渠道响应流水号
     */
    private String channelRespFlow;

    /**
     * 渠道响应码
     */
    private String channelRespCode;

    /**
     * 渠道响应描述
     */
    private String channelRespDesc;
    
    /**
     * 签约或订单确认编号
     */
    private String commitNo;
    
    /**
     * 协议号或者支付token信息
     */
    private String token;
    
	public CgResponse() {
		
	}
	
	public CgResponse(String bizFlow , String tradeStatus , PlatformErrorCode platformErrorCode) {
		this.bizFlow = bizFlow;
		this.tradeStatus = tradeStatus;
		this.tradeCode = platformErrorCode.getErrorCode();
		this.tradeDesc = platformErrorCode.getDefaultMessage();
	}
	
	public CgResponse(String bizFlow , String tradeStatus , PlatformErrorCode platformErrorCode , String tradeDesc) {
		this.bizFlow = bizFlow;
		this.tradeStatus = tradeStatus;
		this.tradeCode = platformErrorCode.getErrorCode();
		this.tradeDesc = tradeDesc;
	}
	
	public CgResponse(String bizFlow , String tradeStatus , String tradeCode , String tradeDesc) {
		this.bizFlow = bizFlow;
		this.tradeStatus = tradeStatus;
		this.tradeCode = tradeCode;
		this.tradeDesc = tradeDesc;
	}

	public String getBizFlow() {
		return bizFlow;
	}

	public void setBizFlow(String bizFlow) {
		this.bizFlow = bizFlow;
	}

	public String getBsnOrderNo() {
		return bsnOrderNo;
	}

	public void setBsnOrderNo(String bsnOrderNo) {
		this.bsnOrderNo = bsnOrderNo;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getTradeDesc() {
		return tradeDesc;
	}

	public void setTradeDesc(String tradeDesc) {
		this.tradeDesc = tradeDesc;
	}

	public String getChannelRespFlow() {
		return channelRespFlow;
	}

	public void setChannelRespFlow(String channelRespFlow) {
		this.channelRespFlow = channelRespFlow;
	}
	

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelRespCode() {
		return channelRespCode;
	}

	public void setChannelRespCode(String channelRespCode) {
		this.channelRespCode = channelRespCode;
	}

	public String getChannelRespDesc() {
		return channelRespDesc;
	}

	public void setChannelRespDesc(String channelRespDesc) {
		this.channelRespDesc = channelRespDesc;
	}

	public String getCommitNo() {
		return commitNo;
	}

	public void setCommitNo(String commitNo) {
		this.commitNo = commitNo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTradeFlow() {
		return tradeFlow;
	}

	public void setTradeFlow(String tradeFlow) {
		this.tradeFlow = tradeFlow;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
}
