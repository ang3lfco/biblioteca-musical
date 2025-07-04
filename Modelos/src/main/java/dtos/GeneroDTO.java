/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author ReneEzequiel23
 */
public class GeneroDTO {
    private String id;
    private String nombre;

    public GeneroDTO() {
    }

    public GeneroDTO(String nombre) {
        this.nombre = nombre;
    }

    public GeneroDTO(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Override
    public String toString() {
        return "GeneroDTO{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    
}
