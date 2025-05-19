/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.ArtistaDTO;
import entidades.Artista;
import entidades.Artista.Integrante;
import java.util.List;
import org.bson.types.ObjectId;
import interfaces.IArtistaDAO;
import interfaces.IArtistaNegocio;
import java.util.ArrayList;

/**
 *
 * @author Oribiel
 */
public class ArtistaNegocio implements IArtistaNegocio {

    private IArtistaDAO artistasDAO;

    public ArtistaNegocio(IArtistaDAO artistasDAO) {
        this.artistasDAO = artistasDAO;
    }

    @Override
    public List<ArtistaDTO> obtenerTodos() {

        return artistasDAO.obtenerTodos();
    }

    @Override
    public List<ArtistaDTO> buscarPorNombre(String nombre) {

        return artistasDAO.buscarPorNombre(nombre);
    }

    @Override
    public List<ObjectId> buscarArtistasPorNombre(String nombre) {

        return artistasDAO.buscarArtistasPorNombre(nombre);
    }

    @Override
    public List<ObjectId> buscarArtistasPorGenero(String nombreGenero) {

        return artistasDAO.buscarArtistasPorGenero(nombreGenero);

    }

    @Override
    public ArtistaDTO buscarArtistaporId(String id) {
        Artista artista = artistasDAO.buscarArtistaporId(id);
        if (artista == null) {
            return null;
        }
        return convertirADTO(artista); // Convierte a DTO
    }

    @Override
    public void insertarArtistas(List<ArtistaDTO> artista) {
        List<Artista> lista = this.ConvertirListaDTOAListaEntidad(artista);
        this.artistasDAO.insertarArtistas(lista);
    }

    private List<Artista> ConvertirListaDTOAListaEntidad(List<ArtistaDTO> ArtistaDTO) {
        List<Artista> resultados = new ArrayList<>();

        if (ArtistaDTO != null) {

            for (ArtistaDTO dto : ArtistaDTO) {
                resultados.add(
                        this.convertirAEntidad(dto)
                );
            }
        }
        return resultados;
    }

    private Artista convertirAEntidad(ArtistaDTO artistaDTO) {
        if (artistaDTO == null) {
            return null;
        }

        // Convertir lista de géneros
        List<ObjectId> generosId = new ArrayList<>();
        if (artistaDTO.getGenerosId() != null) {
            for (String id : artistaDTO.getGenerosId()) {
                generosId.add(new ObjectId(id));
            }
        }

        List<Artista.Integrante> integrantes = new ArrayList<>();
        if (artistaDTO.getIntegrantes() != null) {
            for (ArtistaDTO.integranteDTO integranteDTO : artistaDTO.getIntegrantes()) {
                Artista.Integrante integrante = new Artista.Integrante();
                integrante.setPersonaId(new ObjectId(integranteDTO.getPersonaId()));
                integrante.setRol(integranteDTO.getRol());
                integrante.setFechaIngreso(integranteDTO.getFechaIngreso());
                integrante.setFechaSalida(integranteDTO.getFechaSalida());
                integrantes.add(integrante);
            }
        }

        Artista artista = new Artista();
        if (artistaDTO.getId() != null && !artistaDTO.getId().isBlank()) {
            artista.setId(new ObjectId(artistaDTO.getId()));
        }
        artista.setNombre(artistaDTO.getNombre());
        artista.setTipo(artistaDTO.getTipo());
        artista.setRutaImagen(artistaDTO.getRutaImagen());
        artista.setGenerosId(generosId);
        artista.setIntegrantes(integrantes);

        return artista;
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

        return new ArtistaDTO(
                artista.getId().toHexString(),
                artista.getNombre(),
                artista.getTipo(),
                artista.getRutaImagen(),
                generos,
                integrantes
        );
    }

}
