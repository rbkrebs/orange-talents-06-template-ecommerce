package br.com.zupacademy.romulo.ecommerce.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioControler {

    @Autowired
    private EntityManager em;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioDto usuarioDto){

        UsuarioDto.save(usuarioDto, em);

        return ResponseEntity.ok().build();

    }
}
