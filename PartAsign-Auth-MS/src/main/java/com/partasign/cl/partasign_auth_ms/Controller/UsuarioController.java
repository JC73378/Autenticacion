package com.partasign.cl.partasign_auth_ms.Controller;

import com.partasign.cl.partasign_auth_ms.Model.Usuario;
import com.partasign.cl.partasign_auth_ms.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Usuarios", description = "CRUD de usuarios y login")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    @Operation(summary = "Listar usuarios")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = service.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/crear")
    @Operation(summary = "Crear usuario")
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        Usuario creado = service.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuario por id")
    public ResponseEntity<Usuario> buscar(@PathVariable Integer id) {
        Optional<Usuario> usuario = service.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario")
    public ResponseEntity<Usuario> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Optional<Usuario> existenteOpt = service.findById(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario existente = existenteOpt.get();
        existente.setEmail(usuario.getEmail());
        existente.setPassword(usuario.getPassword());
        existente.setRole(usuario.getRole());

        Usuario actualizado = service.save(existente);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/login")
    @Operation(summary = "Validar credenciales", description = "Valida email y password contra la base.")
    public ResponseEntity<Usuario> login(@RequestParam String email, @RequestParam String password) {
        Optional<Usuario> usuario = service.login(email, password);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
