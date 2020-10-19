package com.example.compasso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@Builder
@Entity
@Table(name="cities")
@AllArgsConstructor
@NoArgsConstructor
public class City extends BaseEntity{
	
	@Column(name="name", nullable = false, length=100)
	private String name;
	
	@Column(name="state", nullable=false, length=30)
	private String state;

}
