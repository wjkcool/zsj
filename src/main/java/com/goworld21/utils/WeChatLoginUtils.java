package com.goworld21.utils;

import com.goworld21.entity.SNSUserInfo;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信utils
 * Created by DELL on 2017/11/7.
 */
public class WeChatLoginUtils {
    /**
     * 根据code登陆微信 获取信息
     *
     * @param code
     * @return
     */
    public static Map getMapByWeiXin(String code) {
        String w = HttpRequest.sendPost("https://api.weixin.qq.com/sns/oauth2/access_token",
                "appid=wx81021e2f245c8c8f&secret=00785711710a8b542111c17da8a04b98&code=" + code
                        + "&grant_type=authorization_code");
        // String
        // w=HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token",
        // "appid=wx81021e2f245c8c8f&secret=00785711710a8b542111c17da8a04b98&code="+code+"&grant_type=authorization_code");
        JSONObject jsonObject = JSONObject.fromObject(w);
        String access_token = jsonObject.getString("access_token");
        System.out.println(access_token);
        System.out.println(w);
        String x = HttpRequest.sendPost("https://api.weixin.qq.com/sns/userinfo",
                "access_token=" + access_token + "&openid=o5YsRwpZzKO_H3TXAyDjs24vko8U");
        JSONObject jsonObject1 = JSONObject.fromObject(x);
        String openid = jsonObject1.getString("openid");
        String sex = jsonObject1.getString("sex");
        String nickname = jsonObject1.getString("nickname");
        String headimgurl = jsonObject1.getString("headimgurl");
        Map map = new HashMap<String, String>();
        map.put("nickname", nickname);
        map.put("sex", sex);
        map.put("headimgurl", headimgurl);
        return map;
    }

    /**
     * 根据code 获取access_token,openid
     *
     * @param code
     * @return
     */
    public static Map getByWeiXin(String code) {
        String w = HttpRequest.sendPost("https://api.weixin.qq.com/sns/oauth2/access_token",
                "appid=wx81021e2f245c8c8f&secret=00785711710a8b542111c17da8a04b98&code=" + code
                        + "&grant_type=authorization_code");
        // String
        // w=HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token",
        // "appid=wx81021e2f245c8c8f&secret=00785711710a8b542111c17da8a04b98&code="+code+"&grant_type=authorization_code");
        JSONObject jsonObject = JSONObject.fromObject(w);
        String access_token = jsonObject.getString("access_token");
        System.out.println(access_token);
        System.out.println(w);
        String x = HttpRequest.sendPost("https://api.weixin.qq.com/sns/userinfo",
                "access_token=" + access_token + "&openid=o5YsRwpZzKO_H3TXAyDjs24vko8U");
        JSONObject jsonObject1 = JSONObject.fromObject(x);
        String openid = jsonObject1.getString("openid");
        Map map = new HashMap<String, String>();
        map.put("openid", openid);
        map.put("access_token", access_token);
        return map;
    }

    /**
     * 获取网页授权凭证access_token
     *
     * @param code
     * @return access_token
     */
    public static String getAccess_token(String code) {
        String w = HttpRequest.sendPost("https://api.weixin.qq.com/sns/oauth2/access_token",
                "appid=wx81021e2f245c8c8f&secret=00785711710a8b542111c17da8a04b98&code=" + code
                        + "&grant_type=authorization_code");
        // String
        // w=HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token",
        // "appid=wx81021e2f245c8c8f&secret=00785711710a8b542111c17da8a04b98&code="+code+"&grant_type=authorization_code");
        JSONObject jsonObject = JSONObject.fromObject(w);
        String access_token = jsonObject.getString("access_token");
        return access_token;
    }

    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openId      用户标识
     * @return SNSUserInfo
     */
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo";
        String param = "access_token=ACCESS_TOKEN&openid=OPENID";
        String replace = param.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        /*JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);*/
        String w = HttpRequest.sendPost(requestUrl, replace);
        JSONObject jsonObject = JSONObject.fromObject(w);

        try {
            snsUserInfo = new SNSUserInfo();
            // 用户的标识
            snsUserInfo.setOpenId(jsonObject.getString("openid"));
            // 昵称
            snsUserInfo.setNickname(jsonObject.getString("nickname"));
            // 性别（1是男性，2是女性，0是未知）
            snsUserInfo.setSex(jsonObject.getInt("sex"));
            // 用户所在国家
            snsUserInfo.setCountry(jsonObject.getString("country"));
            // 用户所在省份
            snsUserInfo.setProvince(jsonObject.getString("province"));
            // 用户所在城市
            snsUserInfo.setCity(jsonObject.getString("city"));
            // 用户头像
            snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            // 用户特权信息
            // snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
        } catch (Exception e) {
            snsUserInfo = null;
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            //log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
        }
        return snsUserInfo;
    }

}
