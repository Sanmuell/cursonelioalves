package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message = "Preenchimento Obrigatório !")
	@Length(min = 5, message = "O tamanho deve ser entre 5 e 12 caracteres")
	private String nome;
	
	@NotEmpty (message = "Preenchimento Obrigatório !")
	@Email(message = "Email invalido")
	private String email;
	
	public ClienteDTO() {
		
	}
	
	// construtor que recebe ma entidade Cliente e instancia um DTO
	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
