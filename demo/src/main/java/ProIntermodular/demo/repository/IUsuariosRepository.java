package ProIntermodular.demo.repository;

import ProIntermodular.demo.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuarios,Long> {
    Usuarios findByEmailAndContrasena(String email, String contrasena);
}
