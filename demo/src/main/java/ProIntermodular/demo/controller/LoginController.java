package ProIntermodular.demo.controller;

import ProIntermodular.demo.service.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    // Creo el apartado de restro de usuario

}