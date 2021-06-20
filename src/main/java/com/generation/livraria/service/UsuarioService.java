package com.generation.livraria.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.boot.internal.MetadataBuildingContextRootImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.livraria.model.Usuario;
import com.generation.livraria.model.UsuarioLogin;
import com.generation.livraria.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository repositorio;
	
	public Usuario cadastrarUsuario(Usuario usuario){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return repositorio.save(usuario);
	}
	
	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repositorio.findByEmail(user.get().getEmail());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getEmail() + ":" + user.get().getSenha();
 			    byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
 			    String authHeader = "Basic " + new String(encodeAuth);
 			    
 			    user.get().setToken(authHeader);
 			    user.get().setNome(usuario.get().getNome());
 			    
 			    return user;
			}
			
		}
		return Optional.empty();
	}
}
