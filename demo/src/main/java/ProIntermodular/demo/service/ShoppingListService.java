package ProIntermodular.demo.service;

import ProIntermodular.demo.model.ShoppingList;
import ProIntermodular.demo.repository.IShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingListService {

    @Autowired
    private IShoppingListRepository repository;

    public List<ShoppingList> findAll(){
        //este metodo devuelve todas las listas
        return repository.findAll();
    }

    public ShoppingList guardar(ShoppingList shoppingList){
        return  repository.save(shoppingList);
    }

}
