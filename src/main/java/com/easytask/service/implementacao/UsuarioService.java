package com.easytask.service.implementacao;

import com.easytask.model.Token;
import com.easytask.model.Usuario;
import com.easytask.repository.TokenRepository;
import com.easytask.repository.UsuarioRepository;
import com.easytask.service.IUsuarioService;
import com.easytask.storage.FotoStorage;
import com.easytask.storage.FotoStorageLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UsuarioService implements IUsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    private FotoStorage fotoStorage;

    public String salvarFoto(String username, MultipartFile foto) {
        String nomeFoto = fotoStorage.salvar(foto);

        Usuario usuario = usuarioRepository.findOne(username);
        usuario.setFoto(nomeFoto);
        usuarioRepository.save(usuario);

        return fotoStorage.getUrl(nomeFoto);
    }

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
    public void recoverPassword(String email) {
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email);
        if (usuario == null)
            return;
            Token token = tokenRepository.findByUsuario(usuario);
            if (token == null) {
                token = new Token();

                token.setUsuario(usuario);

                do {
                    token.setToken(UUID.randomUUID().toString());
                } while (tokenRepository.exists(token.getToken()));

                tokenRepository.save(token);
            }

            //emailService.emailRecuperacaoSenha(token);

    }

    @Override
    public void newPassword(Token token, String password) {
        if (token != null) {
            Usuario usuario = token.getUsuario();
            usuario.setHashSenha(password);
            usuarioRepository.save(usuario);
            tokenRepository.delete(token);
        }
    }

    @Override
    public boolean enableUser(String username, boolean enable) {
        Usuario usuario = findUserByUsername(username);
        usuario.setHabilitado(enable);
        return update(usuario);
    }
}
