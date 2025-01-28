package ProIntermodular.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
// Indica que esta clase es una entidad JPA (se vincula con una tabla de la base de datos)
@Entity
public class ShoppingList {
    // Define la clave primaria de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Generación automática del ID

    private Long id;
    private String nombre;
    private LocalDate date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
