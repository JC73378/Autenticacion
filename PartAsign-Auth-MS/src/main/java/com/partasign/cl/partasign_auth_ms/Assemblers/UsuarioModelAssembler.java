package com.partasign.cl.partasign_auth_ms.Assemblers;

import com.partasign.cl.partasign_auth_ms.Controller.UsuarioControllerV2;
import com.partasign.cl.partasign_auth_ms.Model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(
                usuario,
                linkTo(methodOn(UsuarioControllerV2.class).getById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).getAll()).withRel("usuarios")
        );
    }
}
