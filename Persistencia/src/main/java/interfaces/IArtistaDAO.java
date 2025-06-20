/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ArtistaDTO;
import entidades.Artista;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Oribiel
 */
public interface IArtistaDAO {

    List<ArtistaDTO> obtenerTodos();

    List<ArtistaDTO> buscarPorNombre(String nombre);

    List<ObjectId> buscarArtistasPorNombre(String nombre);

    List<ObjectId> buscarArtistasPorGenero(String nombreGenero);

    Artista buscarArtistaporId(String id);

    void insertarArtistas(List<Artista> artistas);
    
    List<ArtistaDTO> buscarPorGeneroId(String generoid);

}
