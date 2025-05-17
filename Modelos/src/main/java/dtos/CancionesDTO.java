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
public class CancionesDTO {

    private ObjectId id;
    private String nombre;
    private ObjectId albumId;
    private List<ObjectId> generosId;
    private List<ObjectId> artistasId;

    public CancionesDTO() {
    }

    public CancionesDTO(ObjectId id, String nombre, ObjectId albumId, List<ObjectId> generosId, List<ObjectId> artistasId) {
        this.id = id;
        this.nombre = nombre;
        this.albumId = albumId;
        this.generosId = generosId;
        this.artistasId = artistasId;
    }

    public CancionesDTO(String nombre, ObjectId albumId, List<ObjectId> generosId, List<ObjectId> artistasId) {
        this.nombre = nombre;
        this.albumId = albumId;
        this.generosId = generosId;
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

    public ObjectId getAlbumId() {
        return albumId;
    }

    public void setAlbumId(ObjectId albumId) {
        this.albumId = albumId;
    }

    public List<ObjectId> getGenerosId() {
        return generosId;
    }

    public void setGenerosId(List<ObjectId> generosId) {
        this.generosId = generosId;
    }

    public List<ObjectId> getArtistasId() {
        return artistasId;
    }

    public void setArtistasId(List<ObjectId> artistasId) {
        this.artistasId = artistasId;
    }
    
    
}
