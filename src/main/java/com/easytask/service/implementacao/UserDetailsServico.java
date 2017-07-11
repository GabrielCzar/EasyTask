package com.easytask.service.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServico implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username_or_email) throws UsernameNotFoundException {
        return usuarioService.findUsuarioByUsernameOrEmail(username_or_email);
	}
	
}