package com.goworld21.controller;

import com.goworld21.service.PhoneCodeSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DELL on 2017/11/3.
 * 手机发送验证码
 */
@RestController
public class PhoneCodeSendController {


    @Autowired
    PhoneCodeSendService phoneCodeSendService;

    @GetMapping("/phoneCode")
    public boolean getCode(@RequestParam("phone") String phone, @RequestParam("type") int type) {
        System.out.println("3213");
        boolean flag = phoneCodeSendService.saveAndSendVerifyCode(phone, type);
        return flag;
    }
}
