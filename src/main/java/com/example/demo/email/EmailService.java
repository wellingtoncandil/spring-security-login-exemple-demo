package com.example.demo.email;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	//private final JavaMailSender mailSender;
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("wcandil35@gmail.com");
	    mailSender.setPassword("rjjypkxnxtvahmqs");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	    
	    // outro jeito de setar propriedades
	    
	    /*Properties properties = mailSender.getJavaMailProperties();
      properties.put("mail.smtp.starttls.enable", Boolean.TRUE);
      properties.put("mail.transport.protocol", "smtp");
      properties.put("mail.smtp.auth", Boolean.TRUE);
      properties.put("mail.smtp.starttls.required", Boolean.TRUE);
      properties.put("mail.smtp.ssl.enable", Boolean.FALSE);
      properties.put("mail.test-connection", Boolean.TRUE);
      properties.put("mail.debug", Boolean.TRUE);

      mailSender.setJavaMailProperties(properties);*/
	}
	
	@Override
	@Async
	public void send(String to, String email) {
		try {
			MimeMessage mimeMessage = getJavaMailSender().createMimeMessage();//
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom("wcandil35@gmail.com");
			getJavaMailSender().send(mimeMessage);//
		}catch (MessagingException e) {
			LOGGER.error("Failed to send email", e);
			throw new IllegalStateException("Failed to send email");
		}
		
	}

}
