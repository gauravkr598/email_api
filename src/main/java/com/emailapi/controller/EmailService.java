package com.emailapi.controller;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
public class EmailService {
	
	public boolean emailServices( String message,String subject,String to) {
		boolean f=false;
		String from="xxx@gmail.com";// your Email 
		// variable 
				 String host="smtp.gmail.com";
				// gety the System properties  
				 Properties properties = System.getProperties();
				 System.out.println(properties);
				 //Important Setting
				 properties.put("mail.smtp.host",host);
				 properties.put("mail.smtp.port","465");
				 properties.put("mail.smtp.ssl.enable","true");
				 properties.put("mail.smtp.auth","true");
				 
				 
				 //get Session 
				 
				 Session session = Session.getInstance(properties, new Authenticator() {

					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						// TODO Auto-generated method stub
						return new PasswordAuthentication("xxx@gmail.com", "your Email password");
						
					}
					 
				});
				 session.getDebug();
				  
				 //Compose the Email text document  
				 MimeMessage m = new MimeMessage(session);
				 try {
					 //from email
					m.setFrom(from);
					//To
					m.addRecipient(Message.RecipientType.TO,  new InternetAddress(to));
					
					//adding Subject
					m.setSubject(subject);
					m.setText(message); 
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				 //STEP :3 send message
				 try {
					Transport.send(m);
					System.out.println("success send message");
					f=true;
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				return f;
				 
		
	}
}
