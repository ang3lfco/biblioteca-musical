/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import daos.AlbumDAO;
import daos.ArtistaDAO;
import daos.CancionDAO;
import daos.GeneroDAO;
import daos.PersonaDAO;
import dtos.AlbumDTO;
import dtos.ArtistaDTO;
import dtos.CancionDTO;
import dtos.GeneroDTO;
import dtos.PersonaDTO;
import interfaces.IAlbumNegocio;
import interfaces.IArtistaNegocio;
import interfaces.ICancionNegocio;
import interfaces.IGeneroNegocio;
import interfaces.IPersonaNegocio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import negocio.AlbumNegocio;
import negocio.ArtistaNegocio;
import negocio.CancionNegocio;
import negocio.GeneroNegocio;
import negocio.PersonaNegocio;
import org.bson.types.ObjectId;

/**
 *
 * @author ang3lfco
 */
public class InsercionMasiva {

    private final IPersonaNegocio personaNegocio;
    private final IGeneroNegocio generoNegocio;
    private final IArtistaNegocio artistaNegocio;
    private final IAlbumNegocio albumNegocio;
    private final ICancionNegocio cancionNegocio;

    public InsercionMasiva() {
        this.personaNegocio = new PersonaNegocio(new PersonaDAO());
        this.generoNegocio = new GeneroNegocio(new GeneroDAO());
        this.artistaNegocio = new ArtistaNegocio(new ArtistaDAO());
        this.albumNegocio = new AlbumNegocio(new AlbumDAO());
        this.cancionNegocio = new CancionNegocio(new CancionDAO());
    }

    public void insertaDatos() {
        // 1. Insertar personas
        List<PersonaDTO> personas = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            PersonaDTO persona = new PersonaDTO("Edgar" + i, "Ozuna" + i);
            persona.setId(new ObjectId().toHexString());
            personas.add(persona);
        }
        personaNegocio.insertarPersonas(personas);

        // 2. Insertar generos
        List<GeneroDTO> generos = new ArrayList<>();
        for (String nombre : List.of("Rock", "Pop", "Jazz", "Metal", "Ska")) {
            GeneroDTO genero = new GeneroDTO(nombre);
            genero.setId(new ObjectId().toHexString());
            generos.add(genero);
        }
        generoNegocio.insertarGeneros(generos);

        // 3. Insertar artistas
        List<ArtistaDTO> artistas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ArtistaDTO artista = new ArtistaDTO();
            artista.setId(new ObjectId().toHexString()); 
            artista.setNombre("Artista " + (i + 1));
            artista.setTipo("Banda");
            artista.setRutaImagen("ruta/imagen_artista_" + (i + 1) + ".jpg");
            artista.setGenerosId(List.of(generos.get(i).getId()));

            ArtistaDTO.integranteDTO integrante = new ArtistaDTO.integranteDTO(
                    personas.get(i).getId(),
                    "Vocalista",
                    LocalDate.now().minusYears(1),
                    null
            );
            artista.setIntegrantes(List.of(integrante));

            artistas.add(artista);
        }
        artistaNegocio.insertarArtistas(artistas);

        // 4. Insertar albumes 
        List<AlbumDTO> albumes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AlbumDTO album = new AlbumDTO();
            album.setId(new ObjectId().toHexString()); 
            album.setNombre("Album " + (i + 1));
            album.setLanzamiento(LocalDate.of(2020 + i, 1 + i, 10));
            album.setRutaImagen("ruta/imagen_album_" + (i + 1) + ".jpg");
            album.setGenerosId(List.of(generos.get(i).getId()));
            album.setArtistasId(List.of(artistas.get(i).getId()));

            albumes.add(album);
        }
        albumNegocio.insertarAlbumes(albumes);

// 5. Insertar Canciones
        List<CancionDTO> canciones = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CancionDTO cancion = new CancionDTO();
            cancion.setId(new ObjectId().toHexString()); 
            cancion.setNombre("Canción " + (i + 1));
            cancion.setAlbumId(albumes.get(i).getId());
            cancion.setGenerosId(List.of(generos.get(i).getId()));
            cancion.setArtistasId(List.of(artistas.get(i).getId()));

            canciones.add(cancion);
        }
        cancionNegocio.insertarCanciones(canciones);
    }
}
