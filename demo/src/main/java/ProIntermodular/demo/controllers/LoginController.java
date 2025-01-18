package ProIntermodular.demo.controllers;

import ProIntermodular.demo.models.Usuarios;
import ProIntermodular.demo.services.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@Controller
public class LoginController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/dologin")
    public String DoLogin(@RequestParam String email, @RequestParam String password){
        var user = this.usuariosService.getByLogin(email, password);
        if(user != null)
        {
            //dejamos pasar
            //ir a la vista de gestion listas de compra
            return "home";
        }
        else
        {
            return "login";
        }
    }
}