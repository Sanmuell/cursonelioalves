package com.nelioalves.cursomc.resource.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String message;

	public FieldMessage(String fieldName2, String messagem) {

	}

	
	
	@Override
	public String toString() {
		return "FieldMessage [fieldName=" + fieldName + ", message=" + message + "]";
	}



	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
