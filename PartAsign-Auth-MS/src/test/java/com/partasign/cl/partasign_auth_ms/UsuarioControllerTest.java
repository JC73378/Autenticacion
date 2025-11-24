package com.partasign.cl.partasign_auth_ms;

import com.partasign.cl.partasign_auth_ms.Controller.UsuarioController;
import com.partasign.cl.partasign_auth_ms.Model.Usuario;
import com.partasign.cl.partasign_auth_ms.Service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @DisplayName("login: devuelve 200 y el usuario cuando las credenciales son válidas")
    @Test
    void loginOk() throws Exception {
        Usuario usuario = new Usuario(1, "admin@duoc.cl", "Admin123!", "ADMIN");
        Mockito.when(usuarioService.login("admin@duoc.cl", "Admin123!"))
                .thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuarios/login")
                        .param("email", "admin@duoc.cl")
                        .param("password", "Admin123!")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("admin@duoc.cl"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }
}
