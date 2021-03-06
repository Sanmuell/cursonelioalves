package com.nelioalves.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;

	// Recebe o usuario e retorna o userDetails
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		// validação
		Cliente cli = repo.findByEmail(email); // instancia do usuario
		if(cli ==null) { // se não encontrar o usuario --> exceção
			throw new UsernameNotFoundException(email);
	}
		// se encontrar, 
		return new UserSS(
				cli.getId(), 
				cli.getEmail(), 
				cli.getSenha(), 
				cli.getPerfis());
		
	
}
}
