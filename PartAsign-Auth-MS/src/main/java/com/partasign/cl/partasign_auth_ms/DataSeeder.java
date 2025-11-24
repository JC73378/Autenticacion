package com.partasign.cl.partasign_auth_ms;

import com.partasign.cl.partasign_auth_ms.Model.Usuario;
import com.partasign.cl.partasign_auth_ms.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository repository;

    public DataSeeder(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }

        List<Usuario> seed = List.of(
                new Usuario(null, "admin@duoc.cl", "Admin123!", "ADMIN"),
                new Usuario(null, "vcruz@duoc.cl", "123456", "USER"),
                new Usuario(null, "jcarmona@arrimaq.com", "Nicoleg12$@", "USER"),
                new Usuario(null, "maxioporto@arrimaq.com", "TiaWaiffu2", "USER")
        );
        repository.saveAll(seed);
    }
}
