package com.goworld21.service.impl;

import com.goworld21.service.WeiXinService;
import com.goworld21.utils.HttpRequest;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2017/11/3.
 */
@Service
public class WeiXinServiceImpl implements WeiXinService {
    @Override
    public Map getModelByWeiXin(String code) {
        String w = HttpRequest.sendPost("https://api.weixin.qq.com/sns/oauth2/access_token",
                "appid=wx81021e2f245c8c8f&secret=00785711710a8b542111c17da8a04b98&code=" + code
                        + "&grant_type=authorization_code");
        // String
        // w=HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token",
        // "appid=wx81021e2f245c8c8f&secret=00785711710a8b542111c17da8a04b98&code="+code+"&grant_type=authorization_code");
        JSONObject jsonObject = JSONObject.fromObject(w);
        String access_token = null;
        if (jsonObject.has("access_token")) {
            access_token = jsonObject.getString("access_token");
        }
        System.out.println(access_token);
        System.out.println(w);
        String x = HttpRequest.sendPost("https://api.weixin.qq.com/sns/userinfo",
                "access_token=" + access_token + "&openid=o5YsRwpZzKO_H3TXAyDjs24vko8U");
        JSONObject jsonObject1 = JSONObject.fromObject(x);
        System.out.println(jsonObject1);
        String openid = null;
        String sex = null;
        String nickname = null;
        String headimgurl = null;
        if (jsonObject1.has("openid")) {
            openid = jsonObject1.getString("openid");
        }
        if (jsonObject1.has("sex")) {
            sex = jsonObject1.getString("sex");
        }
        if (jsonObject1.has("nickname")) {

            nickname = jsonObject1.getString("nickname");
        }
        if (jsonObject1.has("headimgurl")) {
            headimgurl = jsonObject1.getString("headimgurl");
        }
        Map map = new HashMap<String, String>();
        map.put("nickname", nickname);
        map.put("sex", sex);
        map.put("headimgurl", headimgurl);
        map.put("openid", openid);
        map.put("access_token", access_token);
        return map;
    }
}
