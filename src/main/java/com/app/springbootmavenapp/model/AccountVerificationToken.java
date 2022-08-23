package com.app.springbootmavenapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccountVerificationToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3812737413550438728L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "verification_token", nullable = false)
	private String verificationToken;

	@Column(name = "activated", nullable = false)
	private boolean activated;

	@OneToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
}
