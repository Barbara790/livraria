package com.generation.livraria.security;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.livraria.model.Usuario;
import com.generation.livraria.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private UsuarioRepository userRepositorio;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Optional<Usuario> user = userRepositorio.findByEmail(usuario);
		user.orElseThrow(()-> new UsernameNotFoundException (usuario + "não encontrado"));
		
		return user.map(UserDetailsImpl :: new).get();
	}
	
	
	
	
	

}
