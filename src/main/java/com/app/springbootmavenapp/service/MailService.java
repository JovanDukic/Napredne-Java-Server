package com.app.springbootmavenapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.springbootmavenapp.email.EmailRecipient;
import com.app.springbootmavenapp.exceptions.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender mailSender;

	@Async
	public void sendMail(EmailRecipient emailRecipient) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("jovandukic.business@outlook.com");
			messageHelper.setTo(emailRecipient.getRecipient());
			messageHelper.setSubject(emailRecipient.getSubject());
			messageHelper.setText(emailRecipient.getContent());
			messageHelper.setValidateAddresses(true);
		};
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			throw new CustomException("Activation email wasn't sent!" + "\n" + "Error message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
