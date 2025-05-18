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
    List<AlbumDTO> buscarAlbumesPorFecha(LocalDate fecha, String idUsuario);
    List<AlbumDTO> buscarAlbumesPorGenero(String genero, String idUsuario);
    List<AlbumDTO> buscarAlbumesPorNombre(String nombre, String idUsuario);
    List<AlbumDTO> getAlbumes(String idUsuario);
    AlbumDTO buscarAlbumPorId(String id);
    void agregarAlbum(AlbumDTO albumDTO);
    List<AlbumDTO> obtenerTodos();
}
