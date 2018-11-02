package com.jytpay.depdemo.vo;

public class Result<T> {
    private String status;
    private T reqMsg;
    private T encryMsg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(T reqMsg) {
        this.reqMsg = reqMsg;
    }

    public T getEncryMsg() {
        return encryMsg;
    }

    public void setEncryMsg(T encryMsg) {
        this.encryMsg = encryMsg;
    }
}
