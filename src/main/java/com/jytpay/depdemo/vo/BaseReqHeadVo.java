package com.jytpay.depdemo.vo;

/**
 * 基础请求报文头
 */
public class BaseReqHeadVo {

    /**
     * 报文版本号
     */
    private String version;


    /**
     * 报文类型 00请求报文
     */
    private String tradeType;

    /**
     * 商户号码
     */
    private String merchantNo;

    /**
     * 交易日期 yyyyMMdd
     */
    private String tradeDate;

    /**
     * 交易时间 hhmmss
     */
    private String tradeTime;

    /**
     * 接口类型
     */
    private String tradeCode;

    /**
     * 商户订单号
     */
    private String merOrderNo;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getMerOrderNo() {
        return merOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        this.merOrderNo = merOrderNo;
    }
}
