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
import dtos.UsuarioDTO;
import interfaces.IAlbumNegocio;
import interfaces.IArtistaNegocio;
import interfaces.ICancionNegocio;
import interfaces.IGeneroNegocio;
import interfaces.IPersonaNegocio;
import java.time.LocalDate;
import java.time.Month;
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
        //0
        PersonaDTO persona = new PersonaDTO(new ObjectId().toHexString(), "Andrew John", "Hozier-Byrne");
        personas.add(persona);
        //1
        persona = new PersonaDTO(new ObjectId().toHexString(), "Laufey Lín", "Bing Jónsdóttir");
        personas.add(persona);
        //2
        persona = new PersonaDTO(new ObjectId().toHexString(), "Beatrice Kristi", "Ilejay Laus");
        personas.add(persona);
        //3
        persona = new PersonaDTO(new ObjectId().toHexString(), "Bryant", "Barnes");
        personas.add(persona);
        //4
        persona = new PersonaDTO(new ObjectId().toHexString(), "Yamashita", "Tatsuro");
        personas.add(persona);
        //5
        persona = new PersonaDTO(new ObjectId().toHexString(), "Ha-yi", "Lee");
        personas.add(persona);
        //6
        persona = new PersonaDTO(new ObjectId().toHexString(), "Shakira Isabel", "Mebarak Ripoll");
        personas.add(persona);
        //7
        persona = new PersonaDTO(new ObjectId().toHexString(), "Robyn Rihanna", "Fenty");
        personas.add(persona);
        //8
        persona = new PersonaDTO(new ObjectId().toHexString(), "Dwayne Michael", "Carter Jr.");
        personas.add(persona);
        //9
        persona = new PersonaDTO(new ObjectId().toHexString(), "Daniel", "Zepeda");
        personas.add(persona);
        //10
        persona = new PersonaDTO(new ObjectId().toHexString(), "Iván", "De La Roja");
        personas.add(persona);
        //11
        persona = new PersonaDTO(new ObjectId().toHexString(), "Jesse", "Huerta");
        personas.add(persona);
        //12
        persona = new PersonaDTO(new ObjectId().toHexString(), "Joy", "Huerta");
        personas.add(persona);
        //13
        persona = new PersonaDTO(new ObjectId().toHexString(), "Ross", "Lynch");
        personas.add(persona);
        //14
        persona = new PersonaDTO(new ObjectId().toHexString(), "Rocky", "Lynch");
        personas.add(persona);
        //15
        persona = new PersonaDTO(new ObjectId().toHexString(), "Tyler", "Joseph");
        personas.add(persona);
        //16
        persona = new PersonaDTO(new ObjectId().toHexString(), "Josh", "Dun");
        personas.add(persona);
        //17
        persona = new PersonaDTO(new ObjectId().toHexString(), "Nick", "Thomas");
        personas.add(persona);
        //18
        persona = new PersonaDTO(new ObjectId().toHexString(), "Thomas", "Bangalter");
        personas.add(persona);
        //19
        persona = new PersonaDTO(new ObjectId().toHexString(), "Manuel", "de Homem-Christo");
        personas.add(persona);
        //20
        persona = new PersonaDTO(new ObjectId().toHexString(), "Alexander James", "O'Connor");
        personas.add(persona);
        personaNegocio.insertarPersonas(personas);
        
        // 2. Insertar generos
        List<GeneroDTO> generos = new ArrayList<>();
        for (String nombre : List.of("Rock", "Pop", "Jazz", "Metal", "Ska","kpop","rap","indie","electro")) {
            GeneroDTO genero = new GeneroDTO(nombre);
            genero.setId(new ObjectId().toHexString());
            generos.add(genero);
        }
        generoNegocio.insertarGeneros(generos);

        // 3. Insertar artistas
        List<ArtistaDTO> artistas = new ArrayList<>();
        List<ArtistaDTO.integranteDTO> integrantes = new ArrayList<>();
        //---0
        ArtistaDTO.integranteDTO integrante = new ArtistaDTO.integranteDTO(
                personas.get(0).getId(),
                "Vocalista",
                LocalDate.of(2008, Month.APRIL, 22),
                null
        );

        String id = new ObjectId().toHexString();
        ArtistaDTO artista = new ArtistaDTO(id, "Hozier", "Solista", "/portadas/hozier.png", List.of(generos.get(0).getId()), List.of(integrante));
        artistas.add(artista);
        //---1
        integrante = new ArtistaDTO.integranteDTO(personas.get(1).getId(), "vocalista", LocalDate.of(2020, Month.APRIL, 22), null);
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Laufey", "Solista", "/portadas/laufey.png", List.of(generos.get(1).getId()), List.of(integrante));
        artistas.add(artista);
        //---2
        integrante = new ArtistaDTO.integranteDTO(personas.get(2).getId(), "vocalista", LocalDate.of(2017, Month.MARCH, 14), null);
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "beabadoobee", "Solista", "/portadas/beabadoobee.png", List.of(generos.get(1).getId()), List.of(integrante));
        artistas.add(artista);
        //-3
        integrante = new ArtistaDTO.integranteDTO(personas.get(3).getId(), "vocalista", LocalDate.of(2023, Month.FEBRUARY, 14), null);
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "bryant barnes", "Solista", "/portadas/bryant.png", List.of(generos.get(3).getId()), List.of(integrante));
        artistas.add(artista);
        //-4
        integrante = new ArtistaDTO.integranteDTO(personas.get(4).getId(), "vocalista", LocalDate.of(1976, Month.JUNE, 12), null);
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "tatsuro yamashita", "Solista", "/portadas/tatsuro.png", List.of(generos.get(1).getId()), List.of(integrante));
        artistas.add(artista);
        
        //-5
        integrante = new ArtistaDTO.integranteDTO(personas.get(5).getId(), "vocalista", LocalDate.of(2012, Month.MARCH, 7), null);
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Lee Hi", "Solista", "/portadas/lee.png", List.of(generos.get(5).getId()), List.of(integrante));
        artistas.add(artista);
        //-6
        integrante = new ArtistaDTO.integranteDTO(personas.get(6).getId(), "vocalista", LocalDate.of(1991, Month.JUNE, 24), null);
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Shakira", "Solista", "/portadas/shakira.png", List.of(generos.get(1).getId()), List.of(integrante));
        artistas.add(artista);
        //-7
        integrante = new ArtistaDTO.integranteDTO(personas.get(7).getId(), "vocalista", LocalDate.of(2003, Month.MAY, 17), null);
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Rihanna", "Solista", "/portadas/rihanna.png", List.of(generos.get(1).getId()), List.of(integrante));
        artistas.add(artista);
        //-8
        integrante = new ArtistaDTO.integranteDTO(personas.get(8).getId(), "rapero", LocalDate.of(1999, Month.MAY, 25), null);
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "lil wayne", "Solista", "/portadas/lil.png", List.of(generos.get(1).getId()), List.of(integrante));
        artistas.add(artista);
        //-9
        integrante = new ArtistaDTO.integranteDTO(personas.get(9).getId(), "Vocalista", LocalDate.of(1999, Month.DECEMBER, 11), null);
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Daniel, me estás matando", "banda", "/portadas/daniel.png", List.of(generos.get(1).getId()), List.of(integrante,
                new ArtistaDTO.integranteDTO(personas.get(10).getId(), "Vocalista", LocalDate.of(2001, Month.AUGUST, 23), null)));
        
        artistas.add(artista);
        //-10
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "jesse y joy","banda","/portadas/jesse.png", List.of(generos.get(1).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(11).getId(), "Vocalista", LocalDate.of(2005, Month.APRIL, 23), null),
                new ArtistaDTO.integranteDTO(personas.get(12).getId(), "Vocalista", LocalDate.of(2005, Month.APRIL, 23), null)));
        
        artistas.add(artista);
        //-11
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "The Driver Era","banda","/portadas/driver.png", List.of(generos.get(7).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(13).getId(), "Vocalista", LocalDate.of(2018, Month.MARCH, 16), null),
                new ArtistaDTO.integranteDTO(personas.get(14).getId(), "Guitarra", LocalDate.of(2018, Month.MARCH, 16), null)));
        
        artistas.add(artista);
        //-12
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "twenty one pilots","banda","/portadas/pilots.png", List.of(generos.get(7).getId(),generos.get(6).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(15).getId(), "Vocalista", LocalDate.of(2009, Month.JANUARY, 16), null),
                new ArtistaDTO.integranteDTO(personas.get(16).getId(), "Bateria", LocalDate.of(2011, Month.MAY, 12), null),
                new ArtistaDTO.integranteDTO(personas.get(17).getId(), "Guitarra", LocalDate.of(2009, Month.JANUARY, 16), LocalDate.of(2011, Month.FEBRUARY, 24))));
        
        artistas.add(artista);
        
        //-13
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "daft punk","banda","/portadas/punk.png", List.of(generos.get(8).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(18).getId(), "Compositor", LocalDate.of(1993, Month.JULY, 13), LocalDate.of(2021, Month.FEBRUARY, 18)),
                new ArtistaDTO.integranteDTO(personas.get(19).getId(), "Compositor", LocalDate.of(1993, Month.JULY, 13), LocalDate.of(2021, Month.FEBRUARY, 18))));
        
        artistas.add(artista);
        //-14
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Rex Orange County","Solista","/portadas/orange.png", List.of(generos.get(1).getId(),generos.get(7).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(20).getId(), "Cantante", LocalDate.of(2016, Month.JULY, 29), null)));
        
        artistas.add(artista);
        artistaNegocio.insertarArtistas(artistas);
        // 4. Insertar albumes 
        List<AlbumDTO> albumes = new ArrayList<>();
        //-0
        AlbumDTO album = new AlbumDTO(new ObjectId().toHexString(), "Unreal Unearth", LocalDate.of(2023, Month.AUGUST, 18),
                List.of(generos.get(0).getId()), "/portadas/unreal.png", List.of(artistas.get(0).getId()));
        albumes.add(album);
        //---1
        album = new AlbumDTO(new ObjectId().toHexString(), "Everything I Know About Love", LocalDate.of(2022, Month.AUGUST, 26),
                List.of(generos.get(1).getId()), "/portadas/everything.png", List.of(artistas.get(1).getId()));
        albumes.add(album);
        //-2
        album = new AlbumDTO(new ObjectId().toHexString(), "the perfect pair", LocalDate.of(2022, Month.JULY, 11),
                List.of(generos.get(1).getId()), "/portadas/perfectpair.png", List.of(artistas.get(2).getId()));
        albumes.add(album);
        //-3
        album = new AlbumDTO(new ObjectId().toHexString(), "VANITY", LocalDate.of(2023, Month.JULY, 23),
                List.of(generos.get(2).getId()), "/portadas/vanity.png", List.of(artistas.get(3).getId()));
        albumes.add(album);
        //-4
        album = new AlbumDTO(new ObjectId().toHexString(), "COZY", LocalDate.of(1998, Month.SEPTEMBER, 17),
                List.of(generos.get(1).getId()), "/portadas/cozy.png", List.of(artistas.get(4).getId()));
        albumes.add(album);
        //-5
        album = new AlbumDTO(new ObjectId().toHexString(), "4 only", LocalDate.of(2021, Month.SEPTEMBER, 9),
                List.of(generos.get(5).getId()), "/portadas/only.png", List.of(artistas.get(5).getId()));
        albumes.add(album);
        //-6
        album = new AlbumDTO(new ObjectId().toHexString(), "Loba", LocalDate.of(2009, Month.OCTOBER, 9),
                List.of(generos.get(1).getId()), "/portadas/loba.png", List.of(artistas.get(6).getId()));
        albumes.add(album);
         //-7
        album = new AlbumDTO(new ObjectId().toHexString(), "Loud", LocalDate.of(2010, Month.NOVEMBER, 12),
                List.of(generos.get(1).getId()), "/portadas/loud.png", List.of(artistas.get(7).getId()));
        albumes.add(album);
        //-8
        album = new AlbumDTO(new ObjectId().toHexString(), "Tha Carter III", LocalDate.of(2008, Month.JUNE, 10),
                List.of(generos.get(6).getId()), "/portadas/carter.png", List.of(artistas.get(8).getId()));
        albumes.add(album);
        //-9
        album = new AlbumDTO(new ObjectId().toHexString(), "Suspiros", LocalDate.of(2019, Month.MAY, 10),
                List.of(generos.get(1).getId()), "/portadas/suspiros.png", List.of(artistas.get(9).getId()));
        albumes.add(album);
        //-10
        album = new AlbumDTO(new ObjectId().toHexString(), "¿Con quién se queda el perro?", LocalDate.of(2011, Month.DECEMBER, 6),
                List.of(generos.get(1).getId(),generos.get(0).getId()), "/portadas/perro.png", List.of(artistas.get(10).getId()));
        albumes.add(album);
        //-11
        album = new AlbumDTO(new ObjectId().toHexString(), "Obsession", LocalDate.of(2025, Month.APRIL, 11),
                List.of(generos.get(1).getId(),generos.get(7).getId()), "/portadas/obsession.png", List.of(artistas.get(11).getId()));
        albumes.add(album);
        //-12
        album = new AlbumDTO(new ObjectId().toHexString(), "Stressed Out", LocalDate.of(2015, Month.APRIL, 11),
                List.of(generos.get(6).getId(),generos.get(7).getId()), "/portadas/stressed.png", List.of(artistas.get(12).getId()));
        albumes.add(album);
        //-13
        album = new AlbumDTO(new ObjectId().toHexString(), "Discovery", LocalDate.of(2001, Month.MARCH, 12),
                List.of(generos.get(6).getId(),generos.get(8).getId()), "/portadas/discovery.png", List.of(artistas.get(13).getId()));
        albumes.add(album);
        //-14
        album = new AlbumDTO(new ObjectId().toHexString(), "Television", LocalDate.of(2001, Month.MARCH, 12),
                List.of(generos.get(1).getId(),generos.get(7).getId()), "/portadas/tv.png", List.of(artistas.get(14).getId()));
        albumes.add(album);
        albumNegocio.insertarAlbumes(albumes);

// 5. Insertar Canciones
        List<CancionDTO> canciones = new ArrayList<>();
        //---0
        CancionDTO cancion = new CancionDTO(new ObjectId().toHexString(),
                "Who we are", albumes.get(0).getId(), List.of(generos.get(0).getId()), List.of(artistas.get(0).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Too sweet", albumes.get(0).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(0).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Francesca", albumes.get(0).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(0).getId()));
        canciones.add(cancion);
        //----1
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Fragile", albumes.get(1).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(1).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Too sweet", albumes.get(1).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(1).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Francesca", albumes.get(1).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(1).getId()));
        canciones.add(cancion);
        //---2
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "the perfect pair", albumes.get(2).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(2).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Coffee", albumes.get(2).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(2).getId()));
        canciones.add(cancion);
        //--3
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Universe", albumes.get(3).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(3).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Give me a sign", albumes.get(3).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(3).getId()));
        canciones.add(cancion);
        //--4
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Fragil", albumes.get(4).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(4).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Donut song", albumes.get(4).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(4).getId()));
        canciones.add(cancion);
        //--5
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Only", albumes.get(5).getId(), List.of(generos.get(5).getId()), List.of(artistas.get(5).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Savor", albumes.get(5).getId(), List.of(generos.get(5).getId()), List.of(artistas.get(5).getId()));
        canciones.add(cancion);
        //--6
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "she wolf", albumes.get(6).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(6).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Did It Again", albumes.get(6).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(6).getId()));
        canciones.add(cancion);
        //--7
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "S&M", albumes.get(7).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(7).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "What´s my name", albumes.get(7).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(7).getId()));
        canciones.add(cancion);
        //--8
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "3 Peat", albumes.get(8).getId(), List.of(generos.get(6).getId()), List.of(artistas.get(8).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Phone Home", albumes.get(8).getId(), List.of(generos.get(6).getId()), List.of(artistas.get(8).getId()));
        canciones.add(cancion);
        //--9
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Primer Suspiro", albumes.get(9).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(9).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Fugate", albumes.get(9).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(9).getId()));
        canciones.add(cancion);
        //--10
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "¿Con quién se queda el perro?", albumes.get(10).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(10).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Gotitas de amor", albumes.get(10).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(10).getId()));
        canciones.add(cancion);
        //--11
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Touch", albumes.get(11).getId(), List.of(generos.get(7).getId()), List.of(artistas.get(11).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Same Old Night", albumes.get(11).getId(), List.of(generos.get(7).getId()), List.of(artistas.get(11).getId()));
        canciones.add(cancion);
        //--12
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Stressed Out", albumes.get(12).getId(), List.of(generos.get(7).getId()), List.of(artistas.get(12).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Same Old Night", albumes.get(12).getId(), List.of(generos.get(7).getId()), List.of(artistas.get(12).getId()));
        canciones.add(cancion);
        //--13
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "One more time", albumes.get(13).getId(), List.of(generos.get(8).getId()), List.of(artistas.get(13).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Superheroes", albumes.get(13).getId(), List.of(generos.get(8).getId()), List.of(artistas.get(13).getId()));
        canciones.add(cancion);
        //--14
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Televisiom", albumes.get(14).getId(), List.of(generos.get(7).getId()), List.of(artistas.get(14).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "So far so good", albumes.get(14).getId(), List.of(generos.get(7).getId()), List.of(artistas.get(14).getId()));
        canciones.add(cancion);
        cancionNegocio.insertarCanciones(canciones);
    }
}
