package com.emailapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/emailapi")
	public String emailAPI(){
		
		return "welco email api";
	}
	//send email
	@RequestMapping(value="/sendEmail", method = RequestMethod.POST)
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest){
		   System.out.println("frontend call started..");
		System.out.println(emailRequest);
		String message = emailRequest.getMessage();
		String subject = emailRequest.getSubject();
		String to = emailRequest.getTo();
		boolean status = emailService.emailServices(message, subject, to);
		if(status) {
			return ResponseEntity.ok("Email successfully send...");
		}else {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not send");
		}
	}
}
