package ProIntermodular.demo.repositories;

import ProIntermodular.demo.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosRepositories extends JpaRepository<Usuarios,Long> {

}
