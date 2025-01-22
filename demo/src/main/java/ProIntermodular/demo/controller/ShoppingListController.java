package ProIntermodular.demo.controller;

import ProIntermodular.demo.model.ShoppingList;
import ProIntermodular.demo.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/api/lists")
public class ShoppingListController {

    @Autowired
    private ShoppingListService service;

    @GetMapping()
    public String getAllLists(Model model){
        //yo obtengo primero los datos
        var datos = service.findAll();
        model.addAttribute("listasCompra", datos);
        return "listasCompra"; // Nombre de la vista (products.html)

    }

    @PostMapping
    public  ShoppingList createList(@RequestBody ShoppingList shoppingList){
        return service.guardar(shoppingList);
    }



}
