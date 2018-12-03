package com.jytpay.depdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chencong
 */
@Controller
public class MainController {


    @RequestMapping("/index")
    public String index(){
        //TODO 将接口信息放入json传入index中
        return "index";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/cg1044/view")
    public String openCard(){
        return "openCard";
    }

    @RequestMapping("/cg1045/view")
    public String rechargePaid(){
        return "rechargePaid";
    }

    @RequestMapping("/cg1056/view")
    public String replaceCard(){
        return "replaceCard";
    }

    @RequestMapping("/cg1057/view")
    public String unbind(){
        return "unbindCard";
    }

    @RequestMapping("/cg1048/view")
    public String updatePayPwd(){
        return "updatePayPwd";
    }

    @RequestMapping("/cg1055/view")
    public String resetPayPwd(){
        return "resetPayPwd";
    }

    @RequestMapping("/cg1049/view")
    public String updateRegisterPhone(){
        return "updateRegisterPhone";
    }

    @RequestMapping("/cg1046/view")
    public String withdrawT0(){
        return  "withdrawT0";
    }

    @RequestMapping("/cg1047/view")
    public String withdrawT1(){
        return  "withdrawT1";
    }

    @RequestMapping("/cg1053/view")
    public String paymentTransfer(){
        return "paymentTransfer";
    }

    @RequestMapping("/cg1052/view")
    public String subjectBuy(){
        return "subjectBuy";
    }

    @GetMapping("/cg1050/view")
    public String authorization(){
        return "authorization";
    }
    /**
    *
    * 实时模式
    * cg2006——查询客户信息
    *
    * */
    @RequestMapping("/cg2006/view")
    public String queryCust(){
        return "services/queryCustomer";
    }
}
