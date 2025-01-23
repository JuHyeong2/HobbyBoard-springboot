package com.example.hamo.common.util;


//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailCertificationUtil {
//	private JavaMailSender mailSender;
//	private String title = "HAMO 아이디/비밀번호 찾기 인증 메일입니다.";
//	private String from = "wngud102345@gmail.com";
////	private String content;
//
//	public EmailCertificationUtil(JavaMailSender mailSender) {
//	    this.mailSender = mailSender;
//	}
//	
//	public void sendEmail(String to, String certificationCode) {
//		String content = 
//				System.getProperty("line.separator")+
//	            System.getProperty("line.separator")+
//	            "안녕하세요 HAMO를 다시 찾아주셔서 감사합니다"
//	            +System.getProperty("line.separator")+
//	            System.getProperty("line.separator")+
//	            "인증번호는 " +certificationCode+ " 입니다. " 
//	            +System.getProperty("line.separator");
//		
//		
//		
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(to);
//		message.setFrom(from);
//		message.setSubject(title);
//		message.setText(content);
//		mailSender.send(message);
//	}
}