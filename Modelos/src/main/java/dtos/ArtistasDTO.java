/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import entidades.Artista;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Oribiel
 */
public class ArtistasDTO {

    private ObjectId id;
    private String nombre;
    private String tipo;
    private String rutaImagen;
    private List<ObjectId> generosId;
    private List<Artista.Integrante> integrantes;

    public ArtistasDTO() {
    }

    public ArtistasDTO(ObjectId id, String nombre, String tipo, String rutaImagen, List<ObjectId> generosId, List<Artista.Integrante> integrantes) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rutaImagen = rutaImagen;
        this.generosId = generosId;
        this.integrantes = integrantes;
    }

    public ArtistasDTO(String nombre, String tipo, String rutaImagen, List<ObjectId> generosId, List<Artista.Integrante> integrantes) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.rutaImagen = rutaImagen;
        this.generosId = generosId;
        this.integrantes = integrantes;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public List<ObjectId> getGenerosId() {
        return generosId;
    }

    public void setGenerosId(List<ObjectId> generosId) {
        this.generosId = generosId;
    }

    public List<Artista.Integrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Artista.Integrante> integrantes) {
        this.integrantes = integrantes;
    }
    
    
    
}
