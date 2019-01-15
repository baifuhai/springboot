package com.test.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot14TaskApplicationTests {

	@Autowired
	JavaMailSender javaMailSender;

	@Test
	public void sendMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("通知-今晚开会");
		message.setText("今晚7:30开会");
		message.setFrom("993006470@qq.com");
		message.setTo("bfhtest01@163.com");

		javaMailSender.send(message);
	}

	@Test
	public void x() throws MessagingException {
		//创建一个复杂的消息邮件
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setSubject("通知-今晚开会");
		helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);
		helper.setFrom("993006470@qq.com");
		helper.setTo("bfhtest01@163.com");

		//上传文件
		helper.addAttachment("1.jpg", new File("D:/1.jpg"));
		helper.addAttachment("2.jpg", new File("D:/2.jpg"));

		javaMailSender.send(mimeMessage);
	}

}

