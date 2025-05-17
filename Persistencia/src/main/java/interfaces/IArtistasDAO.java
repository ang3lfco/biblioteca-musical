/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ArtistasDTO;
import java.util.List;

/**
 *
 * @author Oribiel
 */
public interface IArtistasDAO {
    
    
    List<ArtistasDTO> obtenerTodos();
    List<ArtistasDTO> buscarPorNombre(String nombre);
    
}
