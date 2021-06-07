package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.exception.DataIntegrityException;
import com.nelioalves.cursomc.exception.ObjectNotFoundException;
import com.nelioalves.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Long id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + "Tipo:" + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null); // precisa ser um id nulo
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId()); // vai buscar o objeto no banco, caso não exista, já lança exceção
		return categoriaRepository.save(obj);
	}

	public void delete(Long id) {
		find(id); // verificara antes se o id existe
		try {
			categoriaRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) { // se for lançada a exceção
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui um produto ");
		}

	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

}
