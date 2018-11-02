package com.jytpay.depdemo.vo;

public class Result {
    private String status;
    private Object reqMsg;
    private Object encryMsg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(Object reqMsg) {
        this.reqMsg = reqMsg;
    }

    public Object getEncryMsg() {
        return encryMsg;
    }

    public void setEncryMsg(Object encryMsg) {
        this.encryMsg = encryMsg;
    }
}
