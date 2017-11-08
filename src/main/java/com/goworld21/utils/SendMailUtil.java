package com.goworld21.utils;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SendMailUtil {
    // 设置服务器
    private static String KEY_SMTP = "mail.smtp.host";
    private static String VALUE_SMTP = "smtp.163.com";
    // 服务器验证
    private static String KEY_PROPS = "mail.smtp.auth";
    private static boolean VALUE_PROPS = true;
    // 发件人用户名、密码
    private String SEND_USER = "m18920282517@163.com";
    private String SEND_UNAME = "18920282517";
    private String SEND_PWD = "h000000";
    // 建立会话
    private MimeMessage message;
    private Session s;
    private Logger logger = Logger.getLogger(this.getClass());

    /*
    * 初始化方法
    */
    public SendMailUtil() {
        Properties props = System.getProperties();
        props.setProperty(KEY_SMTP, VALUE_SMTP);
        props.put(KEY_PROPS, "true");
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.port", "465");  
        s = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SEND_UNAME, SEND_PWD);
            }
        });
        s.setDebug(true);
        message = new MimeMessage(s);
    }

    /**
     * 单发
     */
    public void doSendHtmlEmail(String headName, String sendHtml,
                                String[] receiveUser) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(SEND_USER);
            message.setFrom(from);

            int receiverLen = receiveUser.length;
            //群发
            for (int i = 0; i < receiverLen; i++) {
                InternetAddress toAddr = new InternetAddress(receiveUser[i]);
                message.addRecipient(javax.mail.Message.RecipientType.TO, toAddr);
            }
            // 邮件标题
            message.setSubject(headName);
            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=GBK");
            message.saveChanges();
            Transport transport = s.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            logger.info("发送成功");
        } catch (AddressException e) {
            logger.info("发送失败" + e);
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            logger.info("发送失败" + e);
            e.printStackTrace();
        }
    }

    /**
     * 群发
     */
    public void doSendHtmlEmail(String headName, String sendHtml,
                                String receiveUser) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(SEND_USER);
            message.setFrom(from);

            //单发
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);

            // 邮件标题
            message.setSubject(headName);
            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"
            message.setContent(content, "text/html;charset=GBK");
            message.saveChanges();
            Transport transport = s.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("send success!");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SendMailUtil se = new SendMailUtil();
        //单发
        se.doSendHtmlEmail("2！", "1！", "598283245@qq.com");
        //群发
        //String[] receiverAddr = {"415405800@qq.com","342097593@qq.com"};
        //se.doSendHtmlEmail("宝贝我爱你", "宝贝我爱你", receiverAddr);


    }

}
