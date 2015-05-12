package com.project.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class EmailNotification {

	static final String host = "smtp.gmail.com";
	static final String username = "courselink273@gmail.com";
	static final String password = "newuser@123"; 

	public void sendEmailonSubscription(String userEmailAddress, String userName,String subject,StringBuilder msgBody)  
	{
		//String subject = "your choice of course is going to start soon";

		//	String msgBody = "Hello "+ userEmailAddress +" courses list which are going to start soon";

		emailGenerator(userEmailAddress, userName, subject, msgBody);


	}


	public void sendEmailOnSubscriptionSignUp(String userEmailAddress, String userName,String subject,StringBuilder msgBody){

		emailGenerator(userEmailAddress, userName, subject, msgBody);

	}


	public void emailGenerator(String userEmailAddress, String userName, String subject, StringBuilder msgBody)

	{
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);

		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message msg = new MimeMessage(session);
			try {
				msg.setFrom(new InternetAddress("courselink273@gmail.com", "Courses suggestion"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				msg.addRecipient(Message.RecipientType.TO,
						new InternetAddress(userEmailAddress, "Hello " + userName ));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg.setSubject(subject);			
			msg.setText(msgBody.toString());
			Transport.send(msg);

		} catch (AddressException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}



	}


}