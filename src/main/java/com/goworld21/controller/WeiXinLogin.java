package com.goworld21.controller;

import com.goworld21.service.WeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信登陆
 * Created by DELL on 2017/11/3.
 */
@RestController
public class WeiXinLogin {
    @Autowired
    WeiXinService weiXinService;

    @GetMapping("/getWeiXin")
    public Map getCode(@RequestParam("code") String code) {
        Map map = new HashMap<String, String>();
        map = weiXinService.getModelByWeiXin(code);
        //根据code获取openId和token
        /*Map weiXin = WeChatLoginUtils.getMapByWeiXin(code);
        String openid = weiXin.get("openid").toString();
        String access_token = weiXin.get("access_token").toString();
        SNSUserInfo snsUserInfo = WeChatLoginUtils.getSNSUserInfo(access_token, openid);*/
        return map;
    }
}

