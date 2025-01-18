package ProIntermodular.demo.controllers;

import ProIntermodular.demo.models.Usuarios;
import ProIntermodular.demo.services.UsuariosService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;
    @GetMapping
    public ArrayList<Usuarios> getUsuarios(){
        return this.usuariosService.getUsuarios();
    }

    @PostMapping
    public  Usuarios guardar (@RequestBody  Usuarios usuarios ){
        return this.usuariosService.guardar(usuarios);
    }

    @GetMapping(path = "/{id}")
    public Optional<Usuarios> getUsuarioById(@PathVariable long id){
        return this.usuariosService.getById(id);
    }

    @PutMapping (path = "/{id}")
    public Usuarios updateUsuariosById(@RequestBody Usuarios usuarios , @PathVariable Long id ){
        return this.usuariosService.updateById(usuarios , id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable ("id"  ) Long id ){
        boolean ok = this.usuariosService.deleteUsuarios(id);
        if (ok ){
            return "Usuario de id es " + id + "eliminado";
        }else {
            return  "Error tenemos un problema al eliminar el id ";
        }
    }

}