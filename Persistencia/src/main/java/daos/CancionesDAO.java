/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import conexion.MongoConexion;
import dtos.CancionesDTO;
import entidades.Cancion;
import interfaces.ICancionesDAO;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Oribiel
 */
public class CancionesDAO implements ICancionesDAO {

    private final MongoCollection<Cancion> coleccion;

    public CancionesDAO() {
        this.coleccion = MongoConexion.getCancionCollection();

    }

    public List<CancionesDTO> obtenerTodas() {
        List<CancionesDTO> lista = new ArrayList<>();
        try (MongoCursor<Cancion> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Cancion cancion = cursor.next();
                lista.add(convertirADTO(cancion));
            }
        }
        return lista;
    }
    
    //Regresa una lista
    @Override
    public List<CancionesDTO> buscarPorNombre(String nombre) {
        List<CancionesDTO> lista = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        try (MongoCursor<Cancion> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Cancion cancion = cursor.next();
                lista.add(convertirADTO(cancion));
            }
        }
        return lista;
    }

    //Regresa el id
    @Override
    public List<ObjectId> buscarCancionesPorNombre(String nombre) {
        List<ObjectId> ids = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        for (Cancion cancion : coleccion.find(filtro)) {
            ids.add(cancion.getId());
        }
        return ids;
    }
  //Las regresa en entidad
    @Override
    public List<Cancion> obtenerTodasEnEntidad() {
        List<Cancion> canciones = new ArrayList<>();
        try (MongoCursor<Cancion> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                canciones.add(cursor.next());
            }
        }
        return canciones;
    }
    
    private CancionesDTO convertirADTO(Cancion cancion) {
        return new CancionesDTO(
                cancion.getId(),
                cancion.getNombre(),
                cancion.getAlbumId(),
                cancion.getGenerosId(),
                cancion.getArtistasId()
        );
    }
}
