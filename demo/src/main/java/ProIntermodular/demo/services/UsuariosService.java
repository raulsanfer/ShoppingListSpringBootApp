package ProIntermodular.demo.services;

import ProIntermodular.demo.models.Usuarios;
import ProIntermodular.demo.repositories.IUsuariosRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuariosService {
    @Autowired
    IUsuariosRepositories usuariosRepositories;
    public ArrayList<Usuarios> getUsuarios(){
        return (ArrayList<Usuarios>)  usuariosRepositories.findAll();
    }
}
