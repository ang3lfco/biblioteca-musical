/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.CancionesDTO;
import entidades.Cancion;
import interfaces.ICancionesDAO;
import interfaces.ICancionesNegocio;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Oribiel
 */
public class CancionesNegocio implements ICancionesNegocio {
    private ICancionesDAO cancionesDAO;
    
    public CancionesNegocio(ICancionesDAO cancionesDAO){
       this.cancionesDAO= cancionesDAO;
    }
    
    public List<CancionesDTO> obtenerTodas() {
        
        return cancionesDAO.obtenerTodas();
    }
    
   @Override
   public  List<CancionesDTO> buscarPorNombre(String nombre) {
       
       return cancionesDAO.buscarPorNombre(nombre);
   }
   
   @Override
   public List<ObjectId> buscarCancionesPorNombre(String nombre){
       
       return cancionesDAO.buscarCancionesPorNombre(nombre);
   }
   
   @Override
   public List<Cancion> obtenerTodasEnEntidad() {
       
       return cancionesDAO.obtenerTodasEnEntidad();
   }
}
