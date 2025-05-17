/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Album;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author ReneEzequiel23
 */
public interface IAlbumDAO {
    List<Album> buscarAlbumesPorNombre(String nombre, ObjectId idUsuario);
    List<Album> buscarAlbumesPorGenero(String genero, ObjectId idUsuario);
    List<Album> buscarAlbumesPorFecha(LocalDate fecha, ObjectId idUsuario);
    List<Album> getAlbumes(ObjectId idUsuario);
    void agregarAlbum(Album album);
    Album buscarAlbumPorId(Object id);
}
