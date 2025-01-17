package ProIntermodular.demo.models;

import jakarta.persistence.*;

public class Usuarios {


    @Entity
    @Table(name = "usuarios")
    public class UsuariosModel {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY)
        private int id;

        @Column
        private String nombre;
        @Column
        private String email;
        @Column
        private String contrasena;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }
    }
}
