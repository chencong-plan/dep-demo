package com.jytpay.depdemo.controller.rest;

import com.jytpay.depdemo.util.GenerateSequenceUtil;
import com.jytpay.depdemo.vo.CgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author chencong@jytpay.com
 * @since 2018/12/10 9:59
 */
@RestController
public class RestAuthorizationController {

    private static final String auth4 = "http://localhost:8080/";

    private final RestTemplate restTemplate;

    @Autowired
    public RestAuthorizationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/auth4")
    public CgResponse auth4(@RequestParam String cardName,
                            @RequestParam String cardNo,
                            @RequestParam String bankMobile,
                            @RequestParam String certNo) {
        String bizFlow = GenerateSequenceUtil.generateProjectNoSequence();
        MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<>();
        uriVariables.add("cardName", cardName);
        uriVariables.add("cardType", "D");
        uriVariables.add("bankMobile", bankMobile);
        uriVariables.add("cardNo", cardNo);
        uriVariables.add("certType", "01");
        uriVariables.add("certNo", certNo);
        uriVariables.add("bizFlow", bizFlow);
        uriVariables.add("tradeCode", "CG4004");
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(uriVariables, header);
        CgResponse response = restTemplate.postForObject(auth4, httpEntity, CgResponse.class);
        return response;
    }
}
