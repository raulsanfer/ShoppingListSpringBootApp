package ProIntermodular.demo.controllers;

import ProIntermodular.demo.models.Usuarios;
import ProIntermodular.demo.services.UsuariosService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;
    @GetMapping
    public ArrayList<Usuarios> getUsuarios(){
        return this.usuariosService.getUsuarios();
    }



}