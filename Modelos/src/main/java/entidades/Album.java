/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author ang3lfco
 */
public class Album {
    private ObjectId id;
    private String nombre;
    private LocalDate lanzamiento;
    private List<ObjectId> generosId;
    private String rutaImagen;
    private List<ObjectId> artistasId;

    public Album() {
    }

    public Album(String nombre, LocalDate lanzamiento, List<ObjectId> generosId, String rutaImagen, List<ObjectId> artistasId) {
        this.nombre = nombre;
        this.lanzamiento = lanzamiento;
        this.generosId = generosId;
        this.rutaImagen = rutaImagen;
        this.artistasId = artistasId;
    }

    public Album(ObjectId id, String nombre, LocalDate lanzamiento, List<ObjectId> generosId, String rutaImagen, List<ObjectId> artistasId) {
        this.id = id;
        this.nombre = nombre;
        this.lanzamiento = lanzamiento;
        this.generosId = generosId;
        this.rutaImagen = rutaImagen;
        this.artistasId = artistasId;
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

    public LocalDate getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(LocalDate lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public List<ObjectId> getGenerosId() {
        return generosId;
    }

    public void setGenerosId(List<ObjectId> generosId) {
        this.generosId = generosId;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public List<ObjectId> getArtistasId() {
        return artistasId;
    }

    public void setArtistasId(List<ObjectId> artistasId) {
        this.artistasId = artistasId;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", nombre=" + nombre + ", lanzamiento=" + lanzamiento + ", generosId=" + generosId + ", rutaImagen=" + rutaImagen + ", artistasId=" + artistasId + '}';
    }
}
