/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.ArtistasDTO;
import entidades.Artista;
import interfaces.IArtistasDAO;
import interfaces.IArtistasNegocio;
import java.util.List;
import org.bson.types.ObjectId;

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
    
    @Override 
    public List<ObjectId> buscarArtistasPorNombre(String nombre){
        
        return artistasDAO.buscarArtistasPorNombre(nombre);
    }
    
    @Override 
    public List<ObjectId> buscarArtistasPorGenero(String nombreGenero){
        
        return artistasDAO.buscarArtistasPorGenero(nombreGenero);
        
    }
    @Override 
    public Artista buscarArtistaporId(Object id){
        
        return artistasDAO.buscarArtistaporId(id);
    }
}
