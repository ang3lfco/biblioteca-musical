/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author ReneEzequiel23
 */
public class AlbumDTO {
    private String id;
    private String nombre;
    private LocalDate lanzamiento;
    private List<String> generosId;
    private String rutaImagen;
    private List<String> artistasId;

    public AlbumDTO() {
    }

    public AlbumDTO(String id, String nombre, LocalDate lanzamiento, List<String> generosId, String rutaImagen, List<String> artistasId) {
        this.id = id;
        this.nombre = nombre;
        this.lanzamiento = lanzamiento;
        this.generosId = generosId;
        this.rutaImagen = rutaImagen;
        this.artistasId = artistasId;
    }

    public AlbumDTO(String nombre, LocalDate lanzamiento, List<String> generosId, String rutaImagen, List<String> artistasId) {
        this.nombre = nombre;
        this.lanzamiento = lanzamiento;
        this.generosId = generosId;
        this.rutaImagen = rutaImagen;
        this.artistasId = artistasId;
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

    public LocalDate getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(LocalDate lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public List<String> getGenerosId() {
        return generosId;
    }

    public void setGenerosId(List<String> generosId) {
        this.generosId = generosId;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public List<String> getArtistasId() {
        return artistasId;
    }

    public void setArtistasId(List<String> artistasId) {
        this.artistasId = artistasId;
    }

    @Override
    public String toString() {
        return "AlbumDTO{" + "id=" + id + ", nombre=" + nombre + ", lanzamiento=" + lanzamiento + ", generosId=" + generosId + ", rutaImagen=" + rutaImagen + ", artistasId=" + artistasId + '}';
    }
   
    
}
