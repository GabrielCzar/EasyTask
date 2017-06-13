package com.easytask.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.easytask.model.Usuario;
import com.easytask.service.implementacao.UsuarioService;

@Component
public class UserDetailsServico implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.findUserByUsername(username);
		if (usuario != null) 
			return usuario;
		throw new UsernameNotFoundException("Login inválido");
	}
	
}