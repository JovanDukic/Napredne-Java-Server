package com.app.springbootmavenapp.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EmailRecipient {
	
	private String recipient;
	private String subject;
	private String content;

}
