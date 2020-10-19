package com.example.compasso.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings({ "serial" })
@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "is_enabled", columnDefinition = "tinyint(1) not null default 1")
	private boolean isEnabled = true;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT current_timestamp")
	protected Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT current_timestamp")
	protected Date updatedAt;

	@Column(name = "is_deleted", columnDefinition = "tinyint(1) not null default 0")
	private boolean isDeleted;

	@PreUpdate
	public void updateTimeStamp() {
		updatedAt = new Date();
	}
	
	@PrePersist
	public void initTimeStamps() {
		createdAt = new Date();
	}

}

