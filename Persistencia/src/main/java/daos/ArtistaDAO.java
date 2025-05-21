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
import dtos.ArtistaDTO.integranteDTO;
import entidades.Artista;
import entidades.Artista.Integrante;
import entidades.Genero;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import interfaces.IArtistaDAO;
import java.util.regex.Pattern;

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

    @Override
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

    @Override
    public List<ArtistaDTO> buscarPorNombre(String nombre) {
        List<ArtistaDTO> lista = new ArrayList<>();
        Bson filtro = Filters.regex("nombre", Pattern.compile(Pattern.quote(nombre), Pattern.CASE_INSENSITIVE));
        try (MongoCursor<Artista> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Artista artista = cursor.next();
                lista.add(convertirADTO(artista));
            }
        }
        return lista;
    }

    @Override
    public List<ObjectId> buscarArtistasPorNombre(String nombre) {
        List<ObjectId> ids = new ArrayList<>();
        Bson filtro = Filters.eq("nombre", nombre);
        for (Artista artista : coleccion.find(filtro)) {
            ids.add(artista.getId());
        }
        return ids;
    }

    @Override
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

    @Override
    public Artista buscarArtistaporId(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return coleccion.find(Filters.eq("_id", objectId)).first();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public void insertarArtistas(List<Artista> artistas) {
        if (artistas != null && !artistas.isEmpty()) {
            coleccion.insertMany(artistas);
            System.out.println("Se insertaron " + artistas.size() + " artistas correctamente.");
        } else {
            System.out.println("No se inserto ningun artista.");
        }
    }

    @Override
    public List<ArtistaDTO> buscarPorGeneroId(String generoid) {
        List<ArtistaDTO> lista = new ArrayList<>();
        Bson filtro;

        if ("sin-generos".equals(generoid)) {
            // Buscar canciones donde artistasId sea null o esté vacío
            filtro = Filters.or(
                    Filters.eq("generosId", null),
                    Filters.size("generosId", 0)
            );
        } else {
            ObjectId idObj;
            try {
                idObj = new ObjectId(generoid);
            } catch (IllegalArgumentException e) {
                return lista;
            }

            filtro = Filters.eq("generosId", idObj);
        }

        try (MongoCursor<Artista> cursor = coleccion.find(filtro).iterator()) {
            while (cursor.hasNext()) {
                Artista genero = cursor.next();
                lista.add(convertirADTO(genero));
            }
        }

        return lista;
    }

    private ArtistaDTO convertirADTO(Artista artista) {
        if (artista == null) {
            return null;
        }

        List<String> generos = new ArrayList<>();
        if (artista.getGenerosId() != null) {
            for (ObjectId id : artista.getGenerosId()) {
                generos.add(id.toHexString());
            }
        }

        List<ArtistaDTO.integranteDTO> integrantes = new ArrayList<>();
        if (artista.getIntegrantes() != null) {
            for (Integrante integrante : artista.getIntegrantes()) {
                String personaId = null;
                if (integrante.getPersonaId() != null) {
                    personaId = integrante.getPersonaId().toHexString();
                }
                ArtistaDTO.integranteDTO dto = new ArtistaDTO.integranteDTO(
                        personaId,
                        integrante.getRol(),
                        integrante.getFechaIngreso(),
                        integrante.getFechaSalida()
                );
                integrantes.add(dto);
            }
        }

        ArtistaDTO dto = new ArtistaDTO(
                artista.getId().toHexString(),
                artista.getNombre(),
                artista.getTipo(),
                artista.getRutaImagen(),
                generos,
                integrantes
        );

        return dto;
    }

}
