/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.CancionDTO;
import entidades.Cancion;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Oribiel
 */
public interface ICancionDAO {
    //Regresa una Lista
    List<CancionDTO> obtenerTodas();
    //Regresa una lista
    List<CancionDTO> buscarPorNombre(String nombre);
    //Regresa el id
    List<ObjectId> buscarCancionesPorNombre(String nombre);
    //Obtiene todas y las regresa como en entidad
    List<Cancion> obtenerTodasEnEntidad();
  
    CancionDTO getCancionPorId(String id);

    void insertarCanciones(List<Cancion> canciones);
    
    List<CancionDTO> buscarPorArtistaId(String artistaId);

}
