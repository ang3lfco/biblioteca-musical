/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import conexion.MongoConexion;
import dtos.CancionDTO;
import entidades.Cancion;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import interfaces.ICancionDAO;
import java.util.regex.Pattern;

/**
 *
 * @author Oribiel
 */
public class CancionDAO implements ICancionDAO {

    private final MongoCollection<Cancion> coleccion;

    public CancionDAO() {
        this.coleccion = MongoConexion.getCancionCollection();

    }

    @Override
    public CancionDTO getCancionPorId(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            Cancion cancion = coleccion.find(Filters.eq("_id", objectId)).first();
            return convertirADTO(cancion);
        } catch (IllegalArgumentException e) {
            System.err.println("ID no válido: " + e.getMessage());
            return null;
        }
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
        Bson filtro = Filters.regex("nombre", Pattern.compile(nombre, Pattern.CASE_INSENSITIVE));
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

    @Override
    public void insertarCanciones(List<Cancion> canciones) {
        if (canciones != null && !canciones.isEmpty()) {
            coleccion.insertMany(canciones);
            System.out.println("Se insertaron " + canciones.size() + " canciones correctamente.");
        } else {
            System.out.println("No se inserto ningun cancion.");
        }
    }

    @Override
    public List<CancionDTO> buscarPorArtistaId(String artistaId) {
        List<CancionDTO> lista = new ArrayList<>();
        Bson filtro;

        if ("sin-artista".equals(artistaId)) {
            // Buscar canciones donde artistasId sea null o esté vacío
            filtro = Filters.or(
                    Filters.eq("artistasId", null),
                    Filters.size("artistasId", 0)
            );
        } else {
            ObjectId idObj;
            try {
                idObj = new ObjectId(artistaId);
            } catch (IllegalArgumentException e) {
                return lista; 
            }

            filtro = Filters.eq("artistasId", idObj);
        }

        try (MongoCursor<Cancion> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Cancion cancion = cursor.next();
                lista.add(convertirADTO(cancion));
            }
        }

        return lista;
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
