package br.com.zupacademy.romulo.ecommerce.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioControler {

    @Autowired
    private EntityManager em;


    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioDto usuarioDto){

        

        return ResponseEntity.ok().build();

    }
}
