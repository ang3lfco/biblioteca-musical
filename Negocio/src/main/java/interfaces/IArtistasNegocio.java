/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ArtistasDTO;
import entidades.Artista;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Oribiel
 */
public interface IArtistasNegocio {
    
    List<ArtistasDTO> obtenerTodos();
    
    List<ArtistasDTO> buscarPorNombre(String nombre);
    
    List<ObjectId> buscarArtistasPorNombre(String nombre);
    
    List<ObjectId> buscarArtistasPorGenero(String nombreGenero);
    
    Artista buscarArtistaporId(Object id);
}
