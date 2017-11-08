package com.goworld21.service.impl;


import com.goworld21.service.PhoneCodeSendService;
import com.goworld21.utils.Message;
import com.goworld21.utils.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * Created by DELL on 2017/11/3.
 */
@Service
public class PhoneCodeSendServiceImpl implements PhoneCodeSendService {

    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean saveAndSendVerifyCode(String phone, int type) {
        MESSAGE_TYPE messageType = null;
        switch (type) {
            case 1:
                // 1.注册
                messageType = MESSAGE_TYPE.REGISTER;
                break;
            case 2:
                // 2.修改手机号
                messageType = MESSAGE_TYPE.NEWPHONE;
                break;
            case 3:
                // 3.修改登录密码
                messageType = MESSAGE_TYPE.PASSWORDRESET;
                break;
            case 4:
                // 4微信绑定
                messageType = MESSAGE_TYPE.WECHATBUNDING;
                break;
            case 5:
                // 5邀请好友
                messageType = MESSAGE_TYPE.INVITEFRIEND;
                break;
            case 6:
                // 6订单分享
                messageType = MESSAGE_TYPE.ORDERSHARE;
                break;
            case 7:
                // 7活动1
                messageType = MESSAGE_TYPE.EVENTJOIN;
                break;
            default:
                break;

        }

        if (messageType == null) {
            log.info("sendVerifyCode失败,不存在的业务类型验证码,业务类型:" + type);
            return false;
        }
        //查看redis中有没有，如果有删除
        String key = phone + "_" + type;
        Boolean aBoolean = stringRedisTemplate.hasKey(key);
        if (aBoolean) {
            stringRedisTemplate.delete(key);
        }
        //生成6位随机数
        String verifyCode = RandomUtils.getRandomNum(6);
        log.info("手机号为" + phone + "的验证码为============" + verifyCode);
        String content = messageType.getMessage().replace("%s", verifyCode);
        //发送短信
        boolean flag = Message.sendNZTMessage(phone, content);
        log.info("发送短信返回:" + flag + ",phone=" + phone + ",短信内容:" + content + messageType.getType());
        //如果发送成功，则存到redis中
        if (flag) {
            System.out.println(key);
            //redisTemplate.opsForValue().set(key,verifyCode,10, TimeUnit.MINUTES);
            //stringRedisTemplate.opsForValue().set(phone,verifyCode,10,TimeUnit.MINUTES);
            stringRedisTemplate.opsForValue().set(key, verifyCode, 10, TimeUnit.MINUTES);
        } else {
            return flag;
        }
        return flag;

    }

}
