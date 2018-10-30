package com.jytpay.depdemo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {


    @RequestMapping("/test")
    public String index(Model model){
        List<String> list = new ArrayList<>();
        list.add("chencong");
        list.add("22");
        Map<String,String> map = new HashMap<>();
        map.put("amount","12.1");
        map.put("list",list.toString());
        String result  = JSON.toJSONString(map,true);
        model.addAttribute("result",result);
        return "test";
    }

    @RequestMapping("/index")
    public String home(){
        return "index";
    }
}
