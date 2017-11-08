package com.goworld21.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.URLEncoder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DELL on 2017/11/3.
 */
public class Message {
    private final static String nzt_url = "http://www.nuozhengtong.com/sms.php";
    //走世界
    private final static String nzt_mall_id = "110858";
    private final static String nzt_appkey = "cdbed6f88959721a53a496182335c221";

    private static Logger LOG = Logger.getLogger(Message.class);

    /**
     * 发送短信(诺正通)
     */
    public static boolean sendNZTMessage(String phone, String content) {
        String title = "";
        String sign = MD5Tools.md5(nzt_mall_id, phone, content, title, nzt_appkey);
        try {
            String url = new StringBuffer(nzt_url)
                    .append("?mall_id=" + nzt_mall_id)
                    .append("&phone=" + phone)
                    .append("&code=" + URLEncoder.encode(content, "UTF-8"))
                    .append("&title=" + URLEncoder.encode(title, "UTF-8"))
                    .append("&sign=" + sign).toString();

            JSONObject reponseJson = JSON.parseObject(url2string(url));
            if ("1000".equals(reponseJson.getString("code"))) {
                return true;
            } else {
                LOG.info("发送验证码失败,手机号" + phone + ",短信接口返回错误码：" + reponseJson.getString("code"));
            }
        } catch (Exception e) {
            LOG.error("发送验证码出错,手机号" + phone, e);
        }
        return false;
    }

    private static String url2string(String url) {
        StringBuffer sb = new StringBuffer();
        try {
            InputStream is = new URL(url).openStream();
            byte[] buf = new byte[1024 * 10];
            int len = 0;
            while ((len = is.read(buf, 0, 1024 * 10)) > 0) {
                sb.append(new String(buf, 0, len));
            }
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //System.out.println(sendNZTMessage("18920042809", "您的校验码是8888，欢迎注册走世界平台，十分钟内有效，如非本人操作，请忽略。"));
    }

}
