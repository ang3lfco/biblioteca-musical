/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import conexion.MongoConexion;
import dtos.ArtistaDTO;
import entidades.Artista;
import entidades.Genero;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import interfaces.IArtistaDAO;

/**
 *
 * @author Oribiel
 */
public class ArtistaDAO implements IArtistaDAO {

    private final MongoCollection<Artista> coleccion;
    private final MongoCollection<Genero> generoCollection;

    public ArtistaDAO() {
        this.coleccion = MongoConexion.getArtistaCollection();
        this.generoCollection = MongoConexion.getGeneroCollection();
    }

    public List<ArtistaDTO> obtenerTodos() {
        List<ArtistaDTO> lista = new ArrayList<>();
        try (MongoCursor<Artista> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Artista artista = cursor.next();
                lista.add(convertirADTO(artista));
            }
        }
        return lista;
    }

    public List<ArtistaDTO> buscarPorNombre(String nombre) {
        List<ArtistaDTO> lista = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        try (MongoCursor<Artista> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Artista artista = cursor.next();
                lista.add(convertirADTO(artista));
            }
        }
        return lista;
    }

    public List<ObjectId> buscarArtistasPorNombre(String nombre) {
        List<ObjectId> ids = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        for (Artista artista : coleccion.find(filtro)) {
            ids.add(artista.getId());
        }
        return ids;
    }

    public List<ObjectId> buscarArtistasPorGenero(String nombreGenero) {
        List<ObjectId> ids = new ArrayList<>();
        Genero genero = generoCollection.find(Filters.eq("nombre", nombreGenero)).first();

        if (genero != null) {
            ObjectId idGenero = genero.getId();
            Bson filtro = Filters.in("generosId", idGenero);
            for (Artista artista : coleccion.find(filtro)) {
                ids.add(artista.getId());
            }
        }

        return ids;
    }

    public Artista buscarArtistaporId(Object id) {
        if (!(id instanceof ObjectId)) {
            return null;
        }
        return coleccion.find(Filters.eq("_id", id)).first();
    }

    private ArtistaDTO convertirADTO(Artista artista) {
        return new ArtistaDTO(
                artista.getId(),
                artista.getNombre(),
                artista.getTipo(),
                artista.getRutaImagen(),
                artista.getGenerosId(),
                artista.getIntegrantes()
        );
    }
}
