/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Oribiel
 */
public class CancionDTO {

    private String id;
    private String nombre;
    private String albumId;
    private List<String> generosId;
    private List<String> artistasId;

    public CancionDTO() {
    }

    public CancionDTO(String id, String nombre, String albumId, List<String> generosId, List<String> artistasId) {
        this.id = id;
        this.nombre = nombre;
        this.albumId = albumId;
        this.generosId = generosId;
        this.artistasId = artistasId;
    }

    public CancionDTO(String nombre, String albumId, List<String> generosId, List<String> artistasId) {
        this.nombre = nombre;
        this.albumId = albumId;
        this.generosId = generosId;
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

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public List<String> getGenerosId() {
        return generosId;
    }

    public void setGenerosId(List<String> generosId) {
        this.generosId = generosId;
    }

    public List<String> getArtistasId() {
        return artistasId;
    }

    public void setArtistasId(List<String> artistasId) {
        this.artistasId = artistasId;
    }

    @Override
    public String toString() {
        return "CancionDTO{" + "id=" + id + ", nombre=" + nombre + ", albumId=" + albumId + ", generosId=" + generosId + ", artistasId=" + artistasId + '}';
    }

}
