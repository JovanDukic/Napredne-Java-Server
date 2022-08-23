package com.app.springbootmavenapp.util;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.app.springbootmavenapp.constants.Constants;
import com.app.springbootmavenapp.email.EmailRecipient;
import com.app.springbootmavenapp.model.AccountVerificationToken;
import com.app.springbootmavenapp.model.User;
import com.app.springbootmavenapp.response.HttpResponse;
import com.app.springbootmavenapp.service.MailService;

public class Utils {

	// === Email stuff ===
	public static void sendActivationEmail(MailService mailService, AccountVerificationToken verificationToken) {
		mailService.sendMail(generateEmailRecipient(verificationToken));
	}

	private static EmailRecipient generateEmailRecipient(AccountVerificationToken verificationToken) {
		return EmailRecipient.builder().recipient(getRecipient(verificationToken)).subject("Account activation")
				.content(generateContent(verificationToken)).build();
	}

	private static String generateContent(AccountVerificationToken verificationToken) {
		User user = verificationToken.getUser();
		return "Dear " + user.getFirstName() + " " + user.getLastName() + "," + "\n"
				+ "In order to activate your account please click on the following link: "
				+ generateActivationLink(verificationToken);
	}

	private static String generateActivationLink(AccountVerificationToken verificationToken) {
		return Constants.BACKEND_URL + "/authentication/activate/" + verificationToken.getVerificationToken();
	}

	private static String getRecipient(AccountVerificationToken verificationToken) {
		return verificationToken.getUser().getEmail();
	}

	// === HttpResponse stuff ===
	public static HttpResponse generateHttpResponseFull(String message, String reasone, Map<?, ?> data,
			HttpStatus httpStatus) {
		return HttpResponse.builder().timestamp(LocalDateTime.now()).message(message).data(data).status(httpStatus)
				.statusCode(httpStatus.value()).reasone(reasone).build();
	}

	public static HttpResponse generateHttpResponse(String message, HttpStatus httpStatus) {
		return HttpResponse.builder().timestamp(LocalDateTime.now()).message(message).status(httpStatus)
				.statusCode(httpStatus.value()).build();
	}

	public static HttpResponse generateHttpResponseWithData(String message, Map<?, ?> data, HttpStatus httpStatus) {
		return HttpResponse.builder().timestamp(LocalDateTime.now()).message(message).data(data).status(httpStatus)
				.statusCode(httpStatus.value()).build();
	}

	public static HttpResponse generateExceptionHttpResponse(String reasone, HttpStatus httpStatus) {
		return HttpResponse.builder().timestamp(LocalDateTime.now()).reasone(reasone).status(httpStatus)
				.statusCode(httpStatus.value()).build();
	}

	public static HttpResponse generateExceptionHttpResponseWithData(String reasone, Map<?, ?> data,
			HttpStatus httpStatus) {
		return HttpResponse.builder().timestamp(LocalDateTime.now()).reasone(reasone).data(data).status(httpStatus)
				.statusCode(httpStatus.value()).build();
	}

	private Utils() {

	}

}
