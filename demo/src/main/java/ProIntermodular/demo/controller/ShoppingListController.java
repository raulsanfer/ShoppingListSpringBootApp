package ProIntermodular.demo.controller;

import ProIntermodular.demo.model.ProductModel;
import ProIntermodular.demo.model.ShoppingListProducts;
import ProIntermodular.demo.model.Usuarios;
import ProIntermodular.demo.service.ShoppingListProductService;
import ProIntermodular.demo.service.UsuariosService;
import jakarta.servlet.http.HttpSession;
import ProIntermodular.demo.model.ShoppingList;
import ProIntermodular.demo.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

// Indica que esta clase es un controlador en Spring MVC
@Controller
// Define la ruta base para todas las solicitudes a este controlador
@RequestMapping("/api/lists")
public class ShoppingListController {
    // Inyección de dependencias: Spring gestionará el servicio automáticamente
    @Autowired
    private ShoppingListService service;
    @Autowired
    private UsuariosService userService;
    @Autowired
    private MercadonaController mercaController;
    @Autowired
    private ShoppingListProductService listaProductService;

    @GetMapping()
    public CompletableFuture<String> getAllLists(Model model){
        // Obtiene todas las listas de compras desde el servicio de forma asincrona
        return service.findAll().thenApply(datos -> {
            model.addAttribute("listasCompra", datos);
            return "fragments/listasCompra";
        });
    }

    @GetMapping("agregarProducto")
    public String agregarProducto(@RequestParam String idLista,Model model)
    {
        model.addAttribute("idLista", idLista);
        return "fragments/agregarProducto";
    }

    @GetMapping("nuevaLista")
    public String showNuevaLista()
    {
        return "fragments/NuevaLista";
    }

    @PostMapping("grabarProducto")
    public ResponseEntity<?> agregarProductoALista(@RequestParam String idLista, @RequestParam String mercaId)
    {
        ProductModel product = mercaController.obtenerProducto(mercaId);
        Optional<ShoppingList> lista = service.getById(Long.valueOf(idLista));
        if(product != null && lista.isPresent())
        {
            //grabar el producto a la lista
            listaProductService.guardarProductoALista(lista.get(),product);
            return ResponseEntity.ok().body("ok");
        }
        else
        {
            return ResponseEntity.badRequest().body("ko");
        }
    }

    @GetMapping("detallesLista")
    public String showDetalleLista(@RequestParam Long id,Model model)
    {
        //2 consultar a BD la lista que quiero consultar
        var objetoLista = service.getById(id);
        var productosLista = listaProductService.getListProducts(objetoLista.get());

        //3 cargar tambien los productos de la lista
        //if(productosLista.stream().count() > 0)
        //{
            model.addAttribute("productos", productosLista);
            model.addAttribute("idLista", id);
            return "fragments/detallesLista";
        //}
        //return "DetallesLista";
        //4 con todo el objeto lista de compra relleno lo mandamos a la vista Detalle
    }

    // Maneja las solicitudes POST para crear una nueva lista de compras
    @PostMapping("/save")
    public ResponseEntity<?> createList(@RequestBody ShoppingList shoppingList, HttpSession session){
        //obtenemos el id usuario logueado para grabar la lista de compra
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        //antes de grabarlo en base de datos modificamos el modelo de lista de compra y le añadimos el idusuario
        if(usuarioId != 0)
        {
            var user = userService.getById(usuarioId);
            if(user.isPresent())
            {
                shoppingList.setUsuario(user.get());
                // Llama al servicio para guardar la nueva lista de compras
                var shoppingListGuardada = service.guardar(shoppingList);

                if(shoppingListGuardada != null)
                {
                    return ResponseEntity.ok().body("ok");
                }
            }
        }
        return ResponseEntity.badRequest().body("ko");
    }

}
