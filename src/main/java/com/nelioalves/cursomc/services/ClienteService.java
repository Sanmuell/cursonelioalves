package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.exception.DataIntegrityException;
import com.nelioalves.cursomc.exception.ObjectNotFoundException;
import com.nelioalves.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + "Tipo:" + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null); // precisa ser um id nulo
		return clienteRepository.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId()); // instancia o objeto  a partir do banco de dados 
		updateData(newObj, obj); // atualizo o objeto "newObj" com os dados do "obj"
		return clienteRepository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Long id) {
		find(id); // verificara antes se o id existe
		try {
			clienteRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) { // se for lançada a exceção
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas ");
		}

	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	// encapsula informações e operações sobre paginação 
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	//metodo auxiliar que instancia um cliente a partir de um DTO
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	

}
