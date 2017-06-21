package com.easytask.service.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.easytask.model.Usuario;
import com.easytask.service.implementacao.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServico implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username_or_email) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.findUsuarioByUsernameOrEmail(username_or_email);
		if (usuario != null) 
			return usuario;
		throw new UsernameNotFoundException("Login inválido");
	}
	
}