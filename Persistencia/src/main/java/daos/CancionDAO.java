/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import conexion.MongoConexion;
import dtos.CancionDTO;
import entidades.Cancion;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import interfaces.ICancionDAO;

/**
 *
 * @author Oribiel
 */
public class CancionDAO implements ICancionDAO {

    private final MongoCollection<Cancion> coleccion;

    public CancionDAO() {
        this.coleccion = MongoConexion.getCancionCollection();

    }

    public List<CancionDTO> obtenerTodas() {
        List<CancionDTO> lista = new ArrayList<>();
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
    public List<CancionDTO> buscarPorNombre(String nombre) {
        List<CancionDTO> lista = new ArrayList<>();
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

    private CancionDTO convertirADTO(Cancion cancion) {
        if (cancion == null) {
            return null;
        }

        List<String> generos = new ArrayList<>();
        if (cancion.getGenerosId() != null) {
            for (ObjectId id : cancion.getGenerosId()) {
                generos.add(id.toHexString());
            }
        }

        List<String> artistas = new ArrayList<>();
        if (cancion.getArtistasId() != null) {
            for (ObjectId id : cancion.getArtistasId()) {
                artistas.add(id.toHexString());
            }
        }
 
        String albumId = null;
        if (cancion.getAlbumId() != null) {
            albumId = cancion.getAlbumId().toHexString();
        }

        CancionDTO dto = new CancionDTO(
                cancion.getId().toHexString(),
                cancion.getNombre(),
                albumId,
                generos,
                artistas
        );

        return dto;
    }

}
