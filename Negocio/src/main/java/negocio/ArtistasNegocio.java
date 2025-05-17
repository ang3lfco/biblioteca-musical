/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.ArtistasDTO;
import interfaces.IArtistasDAO;
import interfaces.IArtistasNegocio;
import java.util.List;

/**
 *
 * @author Oribiel
 */
public class ArtistasNegocio implements IArtistasNegocio{
       private IArtistasDAO artistasDAO;

    public ArtistasNegocio(IArtistasDAO artistasDAO) {
        this.artistasDAO = artistasDAO;
    }

  @Override
    public  List<ArtistasDTO> obtenerTodos(){
        
            return artistasDAO.obtenerTodos();  
    }
    
    @Override
    public  List<ArtistasDTO> buscarPorNombre(String nombre){
        
            return artistasDAO.buscarPorNombre(nombre);  
    }
}
