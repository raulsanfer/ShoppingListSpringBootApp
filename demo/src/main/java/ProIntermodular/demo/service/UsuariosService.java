package ProIntermodular.demo.service;

import ProIntermodular.demo.model.Usuarios;
import ProIntermodular.demo.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuariosService {
    @Autowired
    IUsuariosRepository usuariosRepositories;

    public ArrayList<Usuarios> getUsuarios() {
        return (ArrayList<Usuarios>) usuariosRepositories.findAll();
    }


    public Usuarios guardar(Usuarios usuarios) {
        return usuariosRepositories.save(usuarios);
    }

    public Optional<Usuarios> getById(Long id) {
        return usuariosRepositories.findById(id);
    }

    public Usuarios getByLogin(String email,String pass ) {
        return usuariosRepositories.findByEmailAndContrasena(email, pass);
    }

    public Usuarios updateById(Usuarios request, Long id) {
        Usuarios usuarios = usuariosRepositories.findById(id).get();
        usuarios.setNombre(request.getNombre());
        usuarios.setEmail(request.getEmail());
        usuarios.setContrasena(request.getContrasena());
        return usuarios;
    }


    public Boolean deleteUsuarios(Long id) {

        try {
            usuariosRepositories.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}