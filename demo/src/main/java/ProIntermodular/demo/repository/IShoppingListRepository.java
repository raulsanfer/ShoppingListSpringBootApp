package ProIntermodular.demo.repository;

import ProIntermodular.demo.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Define esta interfaz como un repositorio JPA gestionado por Spring
@Repository
// JpaRepository proporciona métodos básicos como save(), findAll(), findById(), etc.
public interface IShoppingListRepository extends JpaRepository<ShoppingList,Long> {

}
