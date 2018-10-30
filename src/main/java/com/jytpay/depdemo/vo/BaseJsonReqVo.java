package com.jytpay.depdemo.vo;


/**
 * 生成请求json
 */
public class BaseJsonReqVo {

    private BaseReqHeadVo head;
    private Object body;

    public BaseReqHeadVo getHead() {
        return head;
    }

    public void setHead(BaseReqHeadVo head) {
        this.head = head;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
