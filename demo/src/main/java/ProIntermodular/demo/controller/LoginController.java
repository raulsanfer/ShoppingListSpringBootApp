package ProIntermodular.demo.controller;

import jakarta.servlet.http.HttpSession;
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
    public String DoLogin(@RequestParam String email, @RequestParam String password, HttpSession session){
        var user = this.usuariosService.getByLogin(email, password);
        if(user != null)
        {
            //dejamos pasar
            // Guardamos el ID del usuario en la sesión
            session.setAttribute("usuarioId", user.getId());
            session.setAttribute("usuarioEmail", user.getEmail());
            //ir a la vista de gestion listas de compra
            return "index";
        }
        else
        {
            return "login";
        }
    }

    // Creo el apartado de restro de usuario

}