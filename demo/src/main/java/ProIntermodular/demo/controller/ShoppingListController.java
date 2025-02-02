package ProIntermodular.demo.controller;

import jakarta.servlet.http.HttpSession;
import ProIntermodular.demo.model.ShoppingList;
import ProIntermodular.demo.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
// Indica que esta clase es un controlador en Spring MVC
@Controller
// Define la ruta base para todas las solicitudes a este controlador
@RequestMapping("/api/lists")
public class ShoppingListController {
    // Inyección de dependencias: Spring gestionará el servicio automáticamente
    @Autowired
    private ShoppingListService service;

    @GetMapping()
    public String getAllLists(Model model){
        //yo obtengo primero los datos  // Obtiene todas las listas de compras desde el servicio
        var datos = service.findAll();

        // Agrega los datos obtenidos al modelo para que estén disponibles en la vista
        model.addAttribute("listasCompra", datos);
        // Retorna el nombre de la vista que se renderizará (listasCompra.html)
        return "listasCompra"; // Nombre de la vista (products.html)
    }

    @GetMapping("nuevaLista")
    public  String showNuevaLista()
    {
        return "NuevaLista";
    }

    @GetMapping("detalleslista")
    public  String showDetalleLista(int id)
    {
        //1 capturar el id de la lista que quiero mostrar

        //2 consultar a BD la lista que quiero consultar
        //objetoLista = servicio.consultarLista(3)

        //3 cargar tambien los productos de la lista

        //4 con todo el objeto lista de compra relleno lo mandamos a la vista Detalle

        return "DetallesLista";
    }
    // Maneja las solicitudes POST para crear una nueva lista de compras
    @PostMapping("/save")
    public ResponseEntity<?> createList(@RequestBody ShoppingList shoppingList, HttpSession session){
        //obtenemos el id usuario logueado para grabar la lista de compra
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        //antes de grabarlo en base de datos modificamos el modelo de lista de compra y le añadimos el idusuario
        shoppingList.setIdUsuario(usuarioId);

        // Llama al servicio para guardar la nueva lista de compras
        var shoppingListGuardada = service.guardar(shoppingList);

        if(shoppingListGuardada != null)
        {
           return ResponseEntity.ok().body("Ok todo");
        }
        else
        {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }
    }



}
