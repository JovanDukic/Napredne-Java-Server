package com.app.springbootmavenapp.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "covid_test")
@AllArgsConstructor
@NoArgsConstructor
public class CovidTest implements Serializable, EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6493700287589717471L;

	public enum TestType {
		PCR, QUICK
	}

	public enum Result {
		POSITIVE, NEGATIVE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "test_type", nullable = false, columnDefinition = "TEXT")
	private TestType testType;

	@Column(name = "result", nullable = false, columnDefinition = "TEXT")
	private Result result;

	@Column(name = "date", nullable = false)
	private Timestamp date;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "checkup_id", referencedColumnName = "id")
	private Checkup checkup;
	
}
