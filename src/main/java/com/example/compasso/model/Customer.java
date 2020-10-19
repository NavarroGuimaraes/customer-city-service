package com.example.compasso.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.compasso.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="customers")
public class Customer extends BaseEntity {

	@Column(name="name", nullable = false, length = 200)
	private String name;
	
	@Enumerated
	@Column(name="gender", nullable=true)
	private Gender gender;
	
	@Column(name="birth_date", nullable=false)
	@JsonProperty(value="birth_date")
	private Date birthDate;
	
	@Column(name="age", nullable=false)
	private Short age;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_city_id" , referencedColumnName = "id")
	private City city;

}
