/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.ArtistaDTO;
import entidades.Artista;
import java.util.List;
import org.bson.types.ObjectId;
import interfaces.IArtistaDAO;
import interfaces.IArtistaNegocio;

/**
 *
 * @author Oribiel
 */
public class ArtistaNegocio implements IArtistaNegocio{
       private IArtistaDAO artistasDAO;

    public ArtistaNegocio(IArtistaDAO artistasDAO) {
        this.artistasDAO = artistasDAO;
    }

  @Override
    public  List<ArtistaDTO> obtenerTodos(){
        
            return artistasDAO.obtenerTodos();  
    }
    
    @Override
    public  List<ArtistaDTO> buscarPorNombre(String nombre){
        
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
