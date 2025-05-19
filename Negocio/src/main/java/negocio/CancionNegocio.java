/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.CancionDTO;
import entidades.Cancion;
import java.util.List;
import org.bson.types.ObjectId;
import interfaces.ICancionDAO;
import interfaces.ICancionNegocio;

/**
 *
 * @author Oribiel
 */
public class CancionNegocio implements ICancionNegocio {
    private ICancionDAO cancionesDAO;
    
    public CancionNegocio(ICancionDAO cancionesDAO){
       this.cancionesDAO= cancionesDAO;
    }
    
    public List<CancionDTO> obtenerTodas() {
        
        return cancionesDAO.obtenerTodas();
    }
    
   @Override
   public  List<CancionDTO> buscarPorNombre(String nombre) {
       
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
   
   @Override
   public void insertarCanciones(List<Cancion> canciones){
       cancionesDAO.insertarCanciones(canciones);
   }
}
