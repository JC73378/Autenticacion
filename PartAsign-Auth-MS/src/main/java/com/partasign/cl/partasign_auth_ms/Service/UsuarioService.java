package com.partasign.cl.partasign_auth_ms.Service;

import com.partasign.cl.partasign_auth_ms.Model.Usuario;
import com.partasign.cl.partasign_auth_ms.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return repository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Usuario> login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }
}
