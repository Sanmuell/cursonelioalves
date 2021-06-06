package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.exception.ObjectNotFoundException;
import com.nelioalves.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Long id)  {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + "Tipo:" + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null); // precisa ser um id nulo
		return categoriaRepository.save(obj);
	}

}
