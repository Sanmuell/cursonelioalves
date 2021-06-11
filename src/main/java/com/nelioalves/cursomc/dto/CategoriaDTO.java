package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty
	@Length(min = 5, max = 80, message= "O tamanha deve ser entre 5 e 80 caracteres ")
	private String nome;
	
	public CategoriaDTO() {
		
	}
	
	// Construtor que recebe o objeto correspondente da unidade de domínio 
	// responsável por instanciar o DTO a partir do objeto de categoria
	public CategoriaDTO (Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
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
	
	

}
