package com.jytpay.depdemo.vo;

import java.io.Serializable;

public class BaseHttpParamsReq implements Serializable {

    private String merchantNo;

    private String merOrderNo;

    private String jsonEnc;

    private String keyEnc;

    private String sign;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerOrderNo() {
        return merOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        this.merOrderNo = merOrderNo;
    }

    public String getJsonEnc() {
        return jsonEnc;
    }

    public void setJsonEnc(String jsonEnc) {
        this.jsonEnc = jsonEnc;
    }

    public String getKeyEnc() {
        return keyEnc;
    }

    public void setKeyEnc(String keyEnc) {
        this.keyEnc = keyEnc;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "BaseHttpParams [merchantNo=" + merchantNo + ", merOrderNo="
                + merOrderNo + ", jsonEnc=" + jsonEnc + ", keyEnc=" + keyEnc
                + ", sign=" + sign + "]";
    }
}
