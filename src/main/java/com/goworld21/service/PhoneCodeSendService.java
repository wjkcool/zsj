package com.goworld21.service;

/**
 * Created by DELL on 2017/11/3.
 */

public interface PhoneCodeSendService {

    /**
     * 短信类型枚举
     */
    enum MESSAGE_TYPE {
        /**
         * 注册
         */
        REGISTER(1, "您的验证码为%s，欢迎注册走世界平台，十分钟内有效，如非本人操作，请忽略。"),

        /**
         * 改修手机号
         */

        NEWPHONE(2, "验证码为%s，您正在修改绑定手机号，十分钟内有效，如非本人操作，请忽略此条信息"),
        /**
         * 重置登录密码
         */
        PASSWORDRESET(3, "验证码为%s，您正在重置登录密码，十分钟内有效，如非本人操作，请忽略此条信息。"),

        /**
         * 重置微信绑定信息
         */
        WECHATBUNDING(4, "验证码为%s，十分钟内有效，如非本人操作，请忽略此条信息。"),
        /**
         * 邀请好友
         */
        INVITEFRIEND(5, "您的验证码为%s，十分钟内有效，如非本人操作，请忽略。"),
        /**
         * 订单分享
         */
        ORDERSHARE(6, "您的验证码为%s，您正在进行订单分享，十分钟内有效，如非本人操作，请忽略。"),
        /**
         * 活动
         */
        EVENTJOIN(7, "您的验证码为%s，您正在活动报名，十分钟内有效，如非本人操作，请忽略。");

        private int type;
        private String message;

        private MESSAGE_TYPE(int type, String message) {
            this.type = type;
            this.message = message;
        }

        public int getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 发送验证码并保存短信记录
     *
     * @param phone
     * @param type
     * @return
     */
    public boolean saveAndSendVerifyCode(String phone, int type);


}
