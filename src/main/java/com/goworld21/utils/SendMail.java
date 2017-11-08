package com.goworld21.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	private JavaMailSender javaMailSender;
	
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	private String fromAddress;//发件人
		
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}


	/**
	 * 发送邮件
	 * @param toAddress 收件人
	 * @param subject 标题
	 * @param text 邮件文本
	 * @throws MessagingException
	 */
	public boolean sendMail(String toAddress,String subject,String text) throws MessagingException{
		try{
			//MimeMessage对邮件的封装
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			//MimeMessageHelper用于辅助构建MimeMessage
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
			helper.setFrom(fromAddress);//设置发件人
			helper.setTo(toAddress);//设置收件人
			helper.setSubject(subject);//设置标题
			helper.setText(text);//设置内容文本
			
			javaMailSender.send(mimeMessage);//发送
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
