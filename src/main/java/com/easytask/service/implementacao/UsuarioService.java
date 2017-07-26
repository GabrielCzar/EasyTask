package com.easytask.service.implementacao;

import com.easytask.model.Usuario;
import com.easytask.repository.UsuarioRepository;
import com.easytask.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean add(Usuario usuario) {
        try {
            usuario.setHashSenha(usuario.getPassword());
            usuario.setHabilitado(true);
            usuarioRepository.save(usuario);
            return true;
        }catch (DataIntegrityViolationException e){
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario) {
        try {
            Usuario usuarioAntigo = findUserByUsername(usuario.getUsername());
            usuario.setHabilitado(usuarioAntigo.isHabilitado());
            usuario.setPassword(usuarioAntigo.getPassword());
            usuarioRepository.saveAndFlush(usuario);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    @Override
    public Usuario findUserByUsername(String username) {
        return usuarioRepository.findOne(username);
    }

    @Override
    public Usuario findUsuarioByUsernameOrEmail(String username_or_email) {
        Usuario usuario = usuarioRepository.findOne(username_or_email);
        if (usuario == null)
            return  usuarioRepository.findUsuarioByEmail(username_or_email);
        return usuario;
    }

    @Override
    public List<Usuario> findUser() {
        return usuarioRepository.findAllByOrderByNomeAsc();
    }

    @Override
    public boolean delete(Usuario usuario) {
        try {
            usuarioRepository.delete(usuario);
            return true;
        } catch (DataIntegrityViolationException | NullPointerException e) {
            return false;
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findUsuarioByEmail(email);
    }


    @Override
    public boolean enableUser(String username, boolean enable) {
        Usuario usuario = findUserByUsername(username);
        usuario.setHabilitado(enable);
        return update(usuario);
    }
}
