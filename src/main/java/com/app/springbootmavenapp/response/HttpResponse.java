package com.app.springbootmavenapp.response;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(NON_NULL) // izostavlja null vrednosti
public class HttpResponse {

	private LocalDateTime timestamp;
	private int statusCode;
	private HttpStatus status;
	private String reasone;
	private String message;
	private Map<?, ?> data;

}
