package com.jytpay.depdemo.controller;

import com.jytpay.depdemo.service.SubjectService;
import com.jytpay.depdemo.util.HttpRequestUtil;
import com.jytpay.depdemo.vo.BaseJsonReqVo;
import com.jytpay.depdemo.vo.CreateSubjectReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chencong@jytpay.com
 * @since 2019/1/11 10:23
 */
@RestController
@Slf4j
public class SubjectInfoController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectInfoController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/cg1012")
    public String subjectCreate(CreateSubjectReqVo subjectReqVo){
        BaseJsonReqVo baseJsonReqVo = subjectService.getReqJson(subjectReqVo);
        String response = HttpRequestUtil.getRespJson(baseJsonReqVo, subjectReqVo.getRequestUrl());
        return response;
    }

}
