/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.AlbumDTO;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author ReneEzequiel23
 */
public interface IAlbumNegocio {
    List<AlbumDTO> buscarAlbumesPorFecha(LocalDate fecha);
    List<AlbumDTO> buscarAlbumesPorGenero(String genero);
    List<AlbumDTO> buscarAlbumesPorNombre(String nombre);
    List<AlbumDTO> getAlbumes();
    AlbumDTO buscarAlbumPorId(String id);
    void agregarAlbum(AlbumDTO albumDTO);
    List<AlbumDTO> obtenerTodos();
    void insertarAlbumes(List<AlbumDTO> lista);
}
