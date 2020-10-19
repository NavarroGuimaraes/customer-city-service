package com.example.compasso.model.enums;

import java.io.Serializable;

import lombok.Getter;

@Getter
public enum Gender implements Serializable {
	
	HOMEM(0),
	MULHER(1),
	HOMEM_TRANS(2),
	MULHER_TRANS(3),
	NAO_IDENTIFICAR(4);
	
	private Integer code;
	
	Gender(Integer code) {
		this.code = code;
	}
}
