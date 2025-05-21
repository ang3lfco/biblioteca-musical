/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.AlbumDTO;
import entidades.Album;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author ReneEzequiel23
 */
public interface IAlbumDAO {
    List<Album> buscarAlbumesPorNombre(String nombre);
    List<Album> buscarAlbumesPorGenero(String genero);
    List<Album> buscarAlbumesPorFecha(LocalDate fecha);
    List<Album> getAlbumes();
    void agregarAlbum(Album album);
    Album buscarAlbumPorId(Object id);
    List<Album> obtenerTodos();
    void insertarAlbumes(List<Album> albumes);
    List<AlbumDTO> buscarPorArtistaId(String artistaId);
}
