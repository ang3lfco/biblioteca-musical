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
        //21
        persona = new PersonaDTO(new ObjectId().toHexString(), "Freddie", "Mercury");
        personas.add(persona);
        //22
        persona = new PersonaDTO(new ObjectId().toHexString(), "Brian", "May");
        personas.add(persona);
        //23
        persona = new PersonaDTO(new ObjectId().toHexString(), "John ", "Deacon");
        personas.add(persona);
        //24
        persona = new PersonaDTO(new ObjectId().toHexString(), "Roger", "Taylor");
        personas.add(persona);
        //25
        persona = new PersonaDTO(new ObjectId().toHexString(), "Adele", "Adkins");
        personas.add(persona);
        //26
        persona = new PersonaDTO(new ObjectId().toHexString(), "Ed", "Sheeran");
        personas.add(persona);
        //27
        persona = new PersonaDTO(new ObjectId().toHexString(), "Beyonce", "Knowles");
        personas.add(persona);
        //28
        persona = new PersonaDTO(new ObjectId().toHexString(), "Jay-z", "Carter");
        personas.add(persona);
        //29
        persona = new PersonaDTO(new ObjectId().toHexString(), "Kendric ", "Lamar");
        personas.add(persona);
        //30
        persona = new PersonaDTO(new ObjectId().toHexString(), "Taylor ", "Swift");
        personas.add(persona);
        //31
        persona = new PersonaDTO(new ObjectId().toHexString(), "Bruno ", "Mars");
        personas.add(persona);
        //32
        persona = new PersonaDTO(new ObjectId().toHexString(), "Lady ", "Gaga");
        personas.add(persona);
        //33
        persona = new PersonaDTO(new ObjectId().toHexString(), "Paul ", "McCartney");
        personas.add(persona);
        //34
        persona = new PersonaDTO(new ObjectId().toHexString(), "John", "Lennon");
        personas.add(persona);
        //35
        persona = new PersonaDTO(new ObjectId().toHexString(), "George", "Harrison");
        personas.add(persona);
        //36
        persona = new PersonaDTO(new ObjectId().toHexString(), "Dan", "Reynolds");
        personas.add(persona);
        //37
        persona = new PersonaDTO(new ObjectId().toHexString(), "Wayne", "Sermon");
        personas.add(persona);
        //38
        persona = new PersonaDTO(new ObjectId().toHexString(), "Ben", "McKee");
        personas.add(persona);
        //39
        persona = new PersonaDTO(new ObjectId().toHexString(), "Daniel", "Platzman");
        personas.add(persona);
        //40
        persona = new PersonaDTO(new ObjectId().toHexString(), "Chester", "Bennington");
        personas.add(persona);
        //41
        persona = new PersonaDTO(new ObjectId().toHexString(), "Mike", "Shinoda");
        personas.add(persona);
        //42
        persona = new PersonaDTO(new ObjectId().toHexString(), "Brad", "Delson");
        personas.add(persona);
        //43
        persona = new PersonaDTO(new ObjectId().toHexString(), "Rob", "Bourdon");
        personas.add(persona);
        //44
        persona = new PersonaDTO(new ObjectId().toHexString(), "Joe", "Hahn");
        personas.add(persona);
        //45
        persona = new PersonaDTO(new ObjectId().toHexString(), "Dave", "Farrell");
        personas.add(persona);
        //46
        persona = new PersonaDTO(new ObjectId().toHexString(), "Julian", "Casablancas");
        personas.add(persona);
        //47
        persona = new PersonaDTO(new ObjectId().toHexString(), "Nick", "Valensi");
        personas.add(persona);
        //48
        persona = new PersonaDTO(new ObjectId().toHexString(), "Albert", "Hammong Jr.");
        personas.add(persona);
        //49
        persona = new PersonaDTO(new ObjectId().toHexString(), "Nikolai", "Fraiture");
        personas.add(persona);
        //50
        persona = new PersonaDTO(new ObjectId().toHexString(), "Fabritzio", "Moretti");
        personas.add(persona);
        //51
        persona = new PersonaDTO(new ObjectId().toHexString(), "Hayley", "Williams");
        personas.add(persona);
        //52
        persona = new PersonaDTO(new ObjectId().toHexString(), "Taylor", "York");
        personas.add(persona);
        //53
        persona = new PersonaDTO(new ObjectId().toHexString(), "Zac", "Farro");
        personas.add(persona);
        //54
        persona = new PersonaDTO(new ObjectId().toHexString(), "Antony", "Kiedis");
        personas.add(persona);
        //55
        persona = new PersonaDTO(new ObjectId().toHexString(), "Flea", "F");
        personas.add(persona);
        //56
        persona = new PersonaDTO(new ObjectId().toHexString(), "Chad", "Smith");
        personas.add(persona);
        //57
        persona = new PersonaDTO(new ObjectId().toHexString(), "John", "Frusciante");
        personas.add(persona);
        
        
        //INSERTAR PERSONAS POR ang3lfco
        personas.add(new PersonaDTO(new ObjectId().toHexString(), "Juan Carlos", "Sauceda Vásquez"));
        personas.add(new PersonaDTO(new ObjectId().toHexString(), "José Luis", "Maldonado Ramos"));  
        personas.add(new PersonaDTO(new ObjectId().toHexString(), "Gerardo Daniel", "Torres Montante")); 
        personas.add(new PersonaDTO(new ObjectId().toHexString(), "Jesica Yocelin", "Martínez Montiel")); 
        personas.add(new PersonaDTO(new ObjectId().toHexString(), "Alan Alejandro", "Maldonado Tamez"));
        personas.add(new PersonaDTO(new ObjectId().toHexString(), "David", "Sierra Treviño"));
        personas.add(new PersonaDTO(new ObjectId().toHexString(), "Eduardo", "Dávalos De Luna"));
        personas.add(new PersonaDTO(new ObjectId().toHexString(), "Román", "Rodríguez"));
        personas.add(new PersonaDTO(new ObjectId().toHexString(), "Martín Geovanni", "Aldana Cervantes"));
        personas.add(new PersonaDTO(new ObjectId().toHexString(), "Erick Raúl", "Alemán Ramírez"));
        personas.add(new PersonaDTO(new ObjectId().toHexString(), "Irvin", "Puerto Ramírez"));

        

        personaNegocio.insertarPersonas(personas);

        // 2. Insertar generos
        List<GeneroDTO> generos = new ArrayList<>();
        for (String nombre : List.of("Rock", "Pop", "Jazz", "Metal", "Ska", "kpop", "rap", "indie", "electro", "Classic Rock", "Soul", "Hip Hop", "R&B", "Country", "Funk", "Rock Alternativo",
                "Pop Rock", "Nu Metal", "Indie Rock", "Garage Rock", "pop punk", "Emo", "Funk rock", "Alternative Rock", "Rap Mexicano")) {
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
        artista = new ArtistaDTO(id, "jesse y joy", "banda", "/portadas/jesse.png", List.of(generos.get(1).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(11).getId(), "Vocalista", LocalDate.of(2005, Month.APRIL, 23), null),
                new ArtistaDTO.integranteDTO(personas.get(12).getId(), "Vocalista", LocalDate.of(2005, Month.APRIL, 23), null)));

        artistas.add(artista);
        //-11
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "The Driver Era", "banda", "/portadas/driver.png", List.of(generos.get(7).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(13).getId(), "Vocalista", LocalDate.of(2018, Month.MARCH, 16), null),
                new ArtistaDTO.integranteDTO(personas.get(14).getId(), "Guitarra", LocalDate.of(2018, Month.MARCH, 16), null)));

        artistas.add(artista);
        //-12
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "twenty one pilots", "banda", "/portadas/pilots.png", List.of(generos.get(7).getId(), generos.get(6).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(15).getId(), "Vocalista", LocalDate.of(2009, Month.JANUARY, 16), null),
                new ArtistaDTO.integranteDTO(personas.get(16).getId(), "Bateria", LocalDate.of(2011, Month.MAY, 12), null),
                new ArtistaDTO.integranteDTO(personas.get(17).getId(), "Guitarra", LocalDate.of(2009, Month.JANUARY, 16), LocalDate.of(2011, Month.FEBRUARY, 24))));

        artistas.add(artista);

        //-13
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "daft punk", "banda", "/portadas/punk.png", List.of(generos.get(8).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(18).getId(), "Compositor", LocalDate.of(1993, Month.JULY, 13), LocalDate.of(2021, Month.FEBRUARY, 18)),
                new ArtistaDTO.integranteDTO(personas.get(19).getId(), "Compositor", LocalDate.of(1993, Month.JULY, 13), LocalDate.of(2021, Month.FEBRUARY, 18))));

        artistas.add(artista);
        //-14
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Rex Orange County", "Solista", "/portadas/orange.png", List.of(generos.get(1).getId(), generos.get(7).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(20).getId(), "Cantante", LocalDate.of(2016, Month.JULY, 29), null)));

        artistas.add(artista);
        //15
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Queen", "banda", "/portadas/queen.png", List.of(generos.get(9).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(21).getId(), "Vocalista", LocalDate.of(1970, Month.JANUARY, 1), LocalDate.of(1991, Month.FEBRUARY, 18)),
                new ArtistaDTO.integranteDTO(personas.get(22).getId(), "Guitarrista", LocalDate.of(1970, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(23).getId(), "Bajor", LocalDate.of(1970, Month.JANUARY, 1), LocalDate.of(1997, Month.FEBRUARY, 18)),
                new ArtistaDTO.integranteDTO(personas.get(24).getId(), "Bateria", LocalDate.of(1970, Month.JANUARY, 1), null)));

        artistas.add(artista);
        //16
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Adele", "Solista", "/portadas/adele.png", List.of(generos.get(10).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(25).getId(), "Vocalista", LocalDate.of(2008, Month.JANUARY, 1), null)));

        artistas.add(artista);
        //17
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Ed Sheeran", "Solista", "/portadas/edsheeran.png", List.of(generos.get(9).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(26).getId(), "Vocalista", LocalDate.of(2010, Month.JANUARY, 1), null)));

        artistas.add(artista);
        //18
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Beyonce", "Solista", "/portadas/beyonce.png", List.of(generos.get(12).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(27).getId(), "Vocalista", LocalDate.of(2003, Month.JUNE, 1), null)));

        artistas.add(artista);
        //19
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Jay-Z", "Solista", "/portadas/jay-z.png", List.of(generos.get(11).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(28).getId(), "Vocalista", LocalDate.of(1995, Month.MARCH, 1), null)));

        artistas.add(artista);
        //20
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Kendric Lamar", "Solista", "/portadas/kendricklamar.png", List.of(generos.get(11).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(29).getId(), "Vocalista", LocalDate.of(2010, Month.JANUARY, 1), null)));

        artistas.add(artista);
        //21
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Taylor Swift", "Solista", "/portadas/taylorSwift.png", List.of(generos.get(13).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(30).getId(), "Vocalista", LocalDate.of(2006, Month.JULY, 1), null)));

        artistas.add(artista);
        //22
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Bruno Mars", "Solista", "/portadas/brunomars.png", List.of(generos.get(0).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(31).getId(), "Vocalista", LocalDate.of(2010, Month.JANUARY, 1), null)));

        artistas.add(artista);
        //23
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Lady Gaga", "Solista", "/portadas/ladygaga.png", List.of(generos.get(13).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(32).getId(), "Vocalista", LocalDate.of(2007, Month.JANUARY, 1), null)));

        artistas.add(artista);
        //24
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "The beatles", "banda", "/portadas/thebeatles.png", List.of(generos.get(9).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(33).getId(), "Vocalista", LocalDate.of(1960, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(34).getId(), "Guitarrista", LocalDate.of(1960, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(35).getId(), "Bajista", LocalDate.of(1960, Month.JANUARY, 1), null)));

        artistas.add(artista);
        //25
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Imagine Dragons", "banda", "/portadas/imagineDragons.png", List.of(generos.get(15).getId(), generos.get(16).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(36).getId(), "Vocalista", LocalDate.of(2008, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(37).getId(), "Guitarrista", LocalDate.of(2009, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(38).getId(), "Bajista", LocalDate.of(2009, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(39).getId(), "Baterista", LocalDate.of(2011, Month.JANUARY, 1), null)));

        artistas.add(artista);

        // 26
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Linkin Park", "banda", "/portadas/linkinPark.png", List.of(generos.get(17).getId(), generos.get(23).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(40).getId(), "Vocalista", LocalDate.of(1999, Month.JANUARY, 1), LocalDate.of(2017, Month.JULY, 20)),
                new ArtistaDTO.integranteDTO(personas.get(41).getId(), "MC / Tecladista", LocalDate.of(1996, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(42).getId(), "Guitarrista", LocalDate.of(1996, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(43).getId(), "Baterista", LocalDate.of(1996, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(44).getId(), "DJ / Sampler", LocalDate.of(1997, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(45).getId(), "Bajista", LocalDate.of(2001, Month.JANUARY, 1), null)
        ));
        artistas.add(artista);

        //27
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "The Strokes", "banda", "/portadas/theStrokes.png", List.of(generos.get(18).getId(), generos.get(19).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(46).getId(), "Vocalista", LocalDate.of(1998, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(47).getId(), "Guitarrista principal", LocalDate.of(1998, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(48).getId(), "Guitarrista rítmico", LocalDate.of(1998, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(49).getId(), "Bajista", LocalDate.of(1998, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(50).getId(), "Baterista", LocalDate.of(1998, Month.JANUARY, 1), null)
        ));
        artistas.add(artista);

        //28
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Paramore", "banda", "/portadas/paramore.png", List.of(generos.get(23).getId(), generos.get(20).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(51).getId(), "Vocalista", LocalDate.of(2004, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(52).getId(), "Guitarrista", LocalDate.of(2007, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(53).getId(), "Baterista", LocalDate.of(2004, Month.JANUARY, 1), LocalDate.of(2010, Month.JANUARY, 1))));
        artistas.add(artista);

        //29
        id = new ObjectId().toHexString();
        artista = new ArtistaDTO(id, "Red Hot Chili Peppers", "banda", "/portadas/rhcp.png", List.of(generos.get(22).getId(), generos.get(23).getId()), List.of(
                new ArtistaDTO.integranteDTO(personas.get(54).getId(), "Vocalista", LocalDate.of(1983, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(55).getId(), "Bajista", LocalDate.of(1983, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(56).getId(), "Baterista", LocalDate.of(1988, Month.JANUARY, 1), null),
                new ArtistaDTO.integranteDTO(personas.get(57).getId(), "Guitarrista", LocalDate.of(1988, Month.JANUARY, 1), LocalDate.of(1992, Month.JANUARY, 1))));
        artistas.add(artista);
        
        
        //INSERT ARTISTAS POR ang3lfco
        // Lefty SM
        ArtistaDTO.integranteDTO leftyPersona = new ArtistaDTO.integranteDTO(
                personas.get(58).getId(),
                "Solista",
                LocalDate.of(2010, Month.JANUARY, 1),
                null
        );
        ArtistaDTO leftyArtista = new ArtistaDTO(
                new ObjectId().toHexString(), 
                "Lefty SM", 
                "Rapero", 
                "/portadas/leftysm.png", 
                List.of(generos.get(24).getId()), 
                List.of(leftyPersona)
        );
        artistas.add(leftyArtista);

        // C-Kan
        ArtistaDTO.integranteDTO ckanPersona = new ArtistaDTO.integranteDTO(
                personas.get(59).getId(),
                "Solista",
                LocalDate.of(2004, Month.JULY, 5),
                null
        );
        ArtistaDTO ckanArtista = new ArtistaDTO(
                new ObjectId().toHexString(), 
                "C-Kan", 
                "Rapero", 
                "/portadas/ckan.png", 
                List.of(generos.get(24).getId()), 
                List.of(ckanPersona)
        );
        artistas.add(ckanArtista);

        // Gera MX
        ArtistaDTO.integranteDTO geraPersona = new ArtistaDTO.integranteDTO(
                personas.get(60).getId(),
                "Solista",
                LocalDate.of(2013, Month.JUNE, 1),
                null
        );
        ArtistaDTO geraArtista = new ArtistaDTO(
                new ObjectId().toHexString(), 
                "Gera MX", 
                "Rapero", 
                "/portadas/geramx.png", 
                List.of(generos.get(24).getId()), 
                List.of(geraPersona)
        );
        artistas.add(geraArtista);

        // Yoss Bones
        ArtistaDTO.integranteDTO yossPersona = new ArtistaDTO.integranteDTO(
                personas.get(61).getId(),
                "Solista",
                LocalDate.of(2017, Month.JANUARY, 1),
                null
        );
        ArtistaDTO yossArtista = new ArtistaDTO(
                new ObjectId().toHexString(), 
                "Yoss Bones", 
                "Rapera", 
                "/portadas/yossbones.png", 
                List.of(generos.get(24).getId()), 
                List.of(yossPersona)
        );
        artistas.add(yossArtista);

        // Dharius
        ArtistaDTO.integranteDTO dhariusPersona = new ArtistaDTO.integranteDTO(
                personas.get(62).getId(),
                "Solista",
                LocalDate.of(1994, Month.JANUARY, 1),
                null
        );
        ArtistaDTO dhariusArtista = new ArtistaDTO(
                new ObjectId().toHexString(), 
                "Dharius", 
                "Rapero", 
                "/portadas/dharius.png", 
                List.of(generos.get(24).getId()), 
                List.of(dhariusPersona)
        );
        artistas.add(dhariusArtista);

        // MC Davo
        ArtistaDTO.integranteDTO davoPersona = new ArtistaDTO.integranteDTO(
                personas.get(63).getId(),
                "Solista",
                LocalDate.of(2012, Month.JANUARY, 1),
                null
        );
        ArtistaDTO davoArtista = new ArtistaDTO(
                new ObjectId().toHexString(), 
                "MC Davo", 
                "Rapero", 
                "/portadas/mcdavo.png", 
                List.of(generos.get(24).getId()), 
                List.of(davoPersona)
        );
        artistas.add(davoArtista);

        // Cartel de Santa (Grupo)
        ArtistaDTO.integranteDTO baboPersona = new ArtistaDTO.integranteDTO(
                personas.get(64).getId(),
                "Vocalista",
                LocalDate.of(1996, Month.JANUARY, 1),
                null
        );
        ArtistaDTO.integranteDTO monoPersona = new ArtistaDTO.integranteDTO(
                personas.get(65).getId(),
                "Productor",
                LocalDate.of(1996, Month.JANUARY, 1),
                null
        );
        ArtistaDTO cartelArtista = new ArtistaDTO(
                new ObjectId().toHexString(), 
                "Cartel de Santa", 
                "Grupo de Rap", 
                "/portadas/carteldesanta.png", 
                List.of(generos.get(24).getId()), 
                List.of(baboPersona, monoPersona)
        );
        artistas.add(cartelArtista);

        // Eme Malafe
        ArtistaDTO.integranteDTO emePersona = new ArtistaDTO.integranteDTO(
                personas.get(66).getId(),
                "Solista",
                LocalDate.of(2017, Month.JUNE, 1),
                null
        );
        ArtistaDTO emeArtista = new ArtistaDTO(
                new ObjectId().toHexString(), 
                "Eme Malafe", 
                "Rapero", 
                "/portadas/ememalafe.png", 
                List.of(generos.get(24).getId()), 
                List.of(emePersona)
        );
        artistas.add(emeArtista);

        // Alemán
        ArtistaDTO.integranteDTO alemanPersona = new ArtistaDTO.integranteDTO(
                personas.get(67).getId(),
                "Solista",
                LocalDate.of(2009, Month.JANUARY, 1),
                null
        );
        ArtistaDTO alemanArtista = new ArtistaDTO(
                new ObjectId().toHexString(), 
                "Alemán", 
                "Rapero", 
                "/portadas/aleman.png", 
                List.of(generos.get(24).getId()), 
                List.of(alemanPersona)
        );
        artistas.add(alemanArtista);

        // El Mara
        ArtistaDTO.integranteDTO maraPersona = new ArtistaDTO.integranteDTO(
                personas.get(68).getId(),
                "Solista",
                LocalDate.of(2015, Month.MARCH, 1),
                null
        );
        ArtistaDTO maraArtista = new ArtistaDTO(
                new ObjectId().toHexString(), 
                "El Mara", 
                "Rapero", 
                "/portadas/elmara.png", 
                List.of(generos.get(24).getId()), 
                List.of(maraPersona)
        );
        artistas.add(maraArtista);

       
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
                List.of(generos.get(1).getId(), generos.get(0).getId()), "/portadas/perro.png", List.of(artistas.get(10).getId()));
        albumes.add(album);
        //-11
        album = new AlbumDTO(new ObjectId().toHexString(), "Obsession", LocalDate.of(2025, Month.APRIL, 11),
                List.of(generos.get(1).getId(), generos.get(7).getId()), "/portadas/obsession.png", List.of(artistas.get(11).getId()));
        albumes.add(album);
        //-12
        album = new AlbumDTO(new ObjectId().toHexString(), "Stressed Out", LocalDate.of(2015, Month.APRIL, 11),
                List.of(generos.get(6).getId(), generos.get(7).getId()), "/portadas/stressed.png", List.of(artistas.get(12).getId()));
        albumes.add(album);
        //-13
        album = new AlbumDTO(new ObjectId().toHexString(), "Discovery", LocalDate.of(2001, Month.MARCH, 12),
                List.of(generos.get(6).getId(), generos.get(8).getId()), "/portadas/discovery.png", List.of(artistas.get(13).getId()));
        albumes.add(album);
        //-14
        album = new AlbumDTO(new ObjectId().toHexString(), "Television", LocalDate.of(2001, Month.MARCH, 12),
                List.of(generos.get(1).getId(), generos.get(7).getId()), "/portadas/tv.png", List.of(artistas.get(14).getId()));
        albumes.add(album);
        //15
        album = new AlbumDTO(new ObjectId().toHexString(), "A night at the Opera", LocalDate.of(1975, Month.NOVEMBER, 21),
                List.of(generos.get(9).getId(), generos.get(1).getId()), "/portadas/AnightattheOpera.png", List.of(artistas.get(15).getId()));
        albumes.add(album);
        //16
        album = new AlbumDTO(new ObjectId().toHexString(), "21", LocalDate.of(2011, Month.JANUARY, 24),
                List.of(generos.get(1).getId(), generos.get(10).getId(), generos.get(12).getId()), "/portadas/21.png", List.of(artistas.get(16).getId()));
        albumes.add(album);
        //17
        album = new AlbumDTO(new ObjectId().toHexString(), "Divide", LocalDate.of(2017, Month.MARCH, 3),
                List.of(generos.get(1).getId(), generos.get(10).getId()), "/portadas/divide.png", List.of(artistas.get(17).getId()));
        albumes.add(album);
        //18
        album = new AlbumDTO(new ObjectId().toHexString(), "Lemonade", LocalDate.of(2016, Month.APRIL, 23),
                List.of(generos.get(1).getId(), generos.get(14).getId()), "/portadas/lemonade.png", List.of(artistas.get(18).getId()));
        albumes.add(album);
        //19
        album = new AlbumDTO(new ObjectId().toHexString(), "The Blueprint", LocalDate.of(2001, Month.SEPTEMBER, 11),
                List.of(generos.get(11).getId()), "/portadas/theblueprint.png", List.of(artistas.get(19).getId()));
        albumes.add(album);
        //20
        album = new AlbumDTO(new ObjectId().toHexString(), "DAMN", LocalDate.of(2017, Month.APRIL, 14),
                List.of(generos.get(11).getId()), "/portadas/damn.png", List.of(artistas.get(20).getId()));
        albumes.add(album);
        //21
        album = new AlbumDTO(new ObjectId().toHexString(), "1989", LocalDate.of(2014, Month.OCTOBER, 27),
                List.of(generos.get(1).getId()), "/portadas/1989.png", List.of(artistas.get(21).getId()));
        albumes.add(album);
        //22
        album = new AlbumDTO(new ObjectId().toHexString(), "24K Magic", LocalDate.of(2016, Month.NOVEMBER, 18),
                List.of(generos.get(1).getId(), generos.get(12).getId(), generos.get(14).getId()), "/portadas/24kmagic.png", List.of(artistas.get(22).getId()));
        albumes.add(album);
        //23
        album = new AlbumDTO(new ObjectId().toHexString(), "The fame", LocalDate.of(2008, Month.AUGUST, 19),
                List.of(generos.get(1).getId()), "/portadas/thefame.png", List.of(artistas.get(23).getId()));
        albumes.add(album);
        //24
        album = new AlbumDTO(new ObjectId().toHexString(), "Abbey Road", LocalDate.of(2001, Month.MARCH, 12),
                List.of(generos.get(0).getId(), generos.get(9).getId()), "/portadas/abbeyroad.png", List.of(artistas.get(24).getId()));
        albumes.add(album);

        //25
        album = new AlbumDTO(new ObjectId().toHexString(), "Evolve", LocalDate.of(2017, Month.JUNE, 23),
                List.of(generos.get(1).getId(), generos.get(16).getId()), "/portadas/Evolve.png", List.of(artistas.get(25).getId()));
        albumes.add(album);

        // 26
        album = new AlbumDTO(new ObjectId().toHexString(), "Hybrid Theory", LocalDate.of(2000, Month.OCTOBER, 24),
                List.of(generos.get(17).getId(), generos.get(23).getId()), "/portadas/hybridTheory.png", List.of(artistas.get(26).getId()));
        albumes.add(album);

        //27
        album = new AlbumDTO(new ObjectId().toHexString(), "Is This It", LocalDate.of(2001, Month.JULY, 30),
                List.of(generos.get(18).getId(), generos.get(19).getId()), "/portadas/isThisIt.png", List.of(artistas.get(27).getId()));
        albumes.add(album);

        //8
        album = new AlbumDTO(new ObjectId().toHexString(), "Riot!", LocalDate.of(2007, Month.JUNE, 12),
                List.of(generos.get(20).getId(), generos.get(23).getId()), "/portadas/riot.png", List.of(artistas.get(28).getId()));
        albumes.add(album);

        //29
        album = new AlbumDTO(new ObjectId().toHexString(), "Californication", LocalDate.of(1999, Month.JUNE, 8),
                List.of(generos.get(22).getId(), generos.get(23).getId()), "/portadas/californication.png", List.of(artistas.get(29).getId()));
        albumes.add(album);
        
        
        
        //ALBUMES INSERTADOS POR ang3lfco
        AlbumDTO leftyAlbum = new AlbumDTO(new ObjectId().toHexString(), "Avión de Papel", LocalDate.of(2023, Month.MAY, 15),
                List.of(generos.get(24).getId()), "/portadas/aviondepapel.png", List.of(artistas.get(30).getId()));
        albumes.add(leftyAlbum);
        
        AlbumDTO ckanAlbum = new AlbumDTO(
                new ObjectId().toHexString(), 
                "Voy Por El Sueño De Muchos", 
                LocalDate.of(2012, Month.JULY, 5),
                List.of(generos.get(24).getId()), 
                "/portadas/voyporelsuenodemuchos.png", 
                List.of(artistas.get(31).getId())
        );
        albumes.add(ckanAlbum);

        AlbumDTO geraAlbum = new AlbumDTO(
                new ObjectId().toHexString(), 
                "Los Niños Grandes No Juegan", 
                LocalDate.of(2018, Month.FEBRUARY, 19),
                List.of(generos.get(24).getId()), 
                "/portadas/losninosgrandesnojuegan.png", 
                List.of(artistas.get(32).getId())
        );
        albumes.add(geraAlbum);

        AlbumDTO yossAlbum = new AlbumDTO(
                new ObjectId().toHexString(), 
                "Bones", 
                LocalDate.of(2022, Month.SEPTEMBER, 9),
                List.of(generos.get(24).getId()), 
                "/portadas/bones.png", 
                List.of(artistas.get(33).getId())
        );
        albumes.add(yossAlbum);

        AlbumDTO dhariusAlbum = new AlbumDTO(
                new ObjectId().toHexString(), 
                "Directo Hasta Arriba", 
                LocalDate.of(2014, Month.DECEMBER, 9),
                List.of(generos.get(24).getId()), 
                "/portadas/directohastaarriba.png", 
                List.of(artistas.get(34).getId())
        );
        albumes.add(dhariusAlbum);

        AlbumDTO davoAlbum = new AlbumDTO(
                new ObjectId().toHexString(), 
                "Las Dos Caras", 
                LocalDate.of(2017, Month.SEPTEMBER, 8),
                List.of(generos.get(24).getId()), 
                "/portadas/mcdavoalbum.png", 
                List.of(artistas.get(35).getId())
        );
        albumes.add(davoAlbum);

        AlbumDTO cartelAlbum = new AlbumDTO(
                new ObjectId().toHexString(), 
                "Golpe Avisa", 
                LocalDate.of(2014, Month.AUGUST, 5),
                List.of(generos.get(24).getId()), 
                "/portadas/golpeavisa.png", 
                List.of(artistas.get(36).getId())
        );
        albumes.add(cartelAlbum);

        AlbumDTO emeAlbum = new AlbumDTO(
                new ObjectId().toHexString(), 
                "Santos", 
                LocalDate.of(2024, Month.JUNE, 26),
                List.of(generos.get(24).getId()), 
                "/portadas/santos.png", 
                List.of(artistas.get(37).getId())
        );
        albumes.add(emeAlbum);

        AlbumDTO alemanAlbum = new AlbumDTO(
                new ObjectId().toHexString(), 
                "Rich Mafia Vol. 1", 
                LocalDate.of(2024, Month.NOVEMBER, 1),
                List.of(generos.get(24).getId()), 
                "/portadas/richmafiavol1.png", 
                List.of(artistas.get(38).getId(), artistas.get(32).getId())
        );
        albumes.add(alemanAlbum);

        AlbumDTO maraAlbum = new AlbumDTO(
                new ObjectId().toHexString(), 
                "Alambre de Puas", 
                LocalDate.of(2018, Month.JANUARY, 1),
                List.of(generos.get(24).getId()), 
                "/portadas/alambredepuas.png", 
                List.of(artistas.get(39).getId())
        );
        albumes.add(maraAlbum);


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

        //15
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Bohemian  Rhapsody", albumes.get(15).getId(), List.of(generos.get(9).getId()), List.of(artistas.get(15).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Love of My life", albumes.get(15).getId(), List.of(generos.get(9).getId()), List.of(artistas.get(15).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Youre My Best Friend", albumes.get(15).getId(), List.of(generos.get(9).getId()), List.of(artistas.get(15).getId()));
        canciones.add(cancion);
        //16
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Rolling in the deep", albumes.get(16).getId(), List.of(generos.get(10).getId()), List.of(artistas.get(16).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Someone Like You", albumes.get(16).getId(), List.of(generos.get(10).getId()), List.of(artistas.get(16).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Set Fire to the Rain", albumes.get(16).getId(), List.of(generos.get(10).getId()), List.of(artistas.get(16).getId()));
        canciones.add(cancion);
        //17
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Shape of You", albumes.get(17).getId(), List.of(generos.get(9).getId()), List.of(artistas.get(17).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Perfect", albumes.get(17).getId(), List.of(generos.get(9).getId()), List.of(artistas.get(17).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Castle to the Hill", albumes.get(17).getId(), List.of(generos.get(9).getId()), List.of(artistas.get(17).getId()));
        canciones.add(cancion);
        //18
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Formation", albumes.get(18).getId(), List.of(generos.get(12).getId()), List.of(artistas.get(18).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Sorry", albumes.get(18).getId(), List.of(generos.get(12).getId()), List.of(artistas.get(18).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Hold Up", albumes.get(18).getId(), List.of(generos.get(12).getId()), List.of(artistas.get(18).getId()));
        canciones.add(cancion);
        //19
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Izzo (H.O.V.A.)", albumes.get(19).getId(), List.of(generos.get(11).getId()), List.of(artistas.get(19).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Renegade", albumes.get(19).getId(), List.of(generos.get(11).getId()), List.of(artistas.get(19).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Girls, Girls, Girls", albumes.get(19).getId(), List.of(generos.get(11).getId()), List.of(artistas.get(19).getId()));
        canciones.add(cancion);
        //20
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "HUMBLE", albumes.get(20).getId(), List.of(generos.get(11).getId()), List.of(artistas.get(20).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "DNA.", albumes.get(20).getId(), List.of(generos.get(11).getId()), List.of(artistas.get(20).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "LOVE.", albumes.get(20).getId(), List.of(generos.get(11).getId()), List.of(artistas.get(20).getId()));
        canciones.add(cancion);
        //21
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Blank Space", albumes.get(21).getId(), List.of(generos.get(11).getId()), List.of(artistas.get(21).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Shake it Off..", albumes.get(21).getId(), List.of(generos.get(11).getId()), List.of(artistas.get(21).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Style..", albumes.get(21).getId(), List.of(generos.get(11).getId()), List.of(artistas.get(21).getId()));
        canciones.add(cancion);
        //22
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "24k Magic", albumes.get(22).getId(), List.of(generos.get(1).getId(), generos.get(14).getId()), List.of(artistas.get(22).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Thats What i Like.", albumes.get(22).getId(), List.of(generos.get(1).getId(), generos.get(14).getId()), List.of(artistas.get(22).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Finesse.", albumes.get(22).getId(), List.of(generos.get(1).getId(), generos.get(14).getId()), List.of(artistas.get(22).getId()));
        canciones.add(cancion);
        //23
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Just Dance", albumes.get(23).getId(), List.of(generos.get(12).getId()), List.of(artistas.get(23).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Poker Face", albumes.get(23).getId(), List.of(generos.get(12).getId()), List.of(artistas.get(23).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "LoveGame", albumes.get(23).getId(), List.of(generos.get(12).getId()), List.of(artistas.get(23).getId()));
        canciones.add(cancion);
        //24
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Come Together", albumes.get(24).getId(), List.of(generos.get(9).getId()), List.of(artistas.get(24).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Something", albumes.get(24).getId(), List.of(generos.get(9).getId()), List.of(artistas.get(24).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Here Comes The Sun", albumes.get(24).getId(), List.of(generos.get(9).getId()), List.of(artistas.get(24).getId()));
        canciones.add(cancion);
        //25
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Radioactive", albumes.get(25).getId(), List.of(generos.get(15).getId()), List.of(artistas.get(25).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Demons", albumes.get(25).getId(), List.of(generos.get(15).getId()), List.of(artistas.get(25).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "It's Time", albumes.get(25).getId(), List.of(generos.get(16).getId()), List.of(artistas.get(25).getId()));
        canciones.add(cancion);
        //26
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "In the End", albumes.get(26).getId(), List.of(generos.get(10).getId()), List.of(artistas.get(26).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "One Step Closer", albumes.get(26).getId(), List.of(generos.get(10).getId()), List.of(artistas.get(26).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Crawling", albumes.get(26).getId(), List.of(generos.get(3).getId()), List.of(artistas.get(26).getId()));
        canciones.add(cancion);
        //27
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Last Nite", albumes.get(27).getId(), List.of(generos.get(14).getId()), List.of(artistas.get(27).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Someday", albumes.get(27).getId(), List.of(generos.get(14).getId()), List.of(artistas.get(27).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Hard to Explain", albumes.get(27).getId(), List.of(generos.get(7).getId()), List.of(artistas.get(27).getId()));
        canciones.add(cancion);
        //28
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Misery Business", albumes.get(28).getId(), List.of(generos.get(12).getId()), List.of(artistas.get(28).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Crushcrushcrush", albumes.get(28).getId(), List.of(generos.get(12).getId()), List.of(artistas.get(28).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "That's What You Get", albumes.get(28).getId(), List.of(generos.get(4).getId()), List.of(artistas.get(28).getId()));
        canciones.add(cancion);
        //29
        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Californication", albumes.get(29).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(29).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Scar Tissue", albumes.get(29).getId(), List.of(generos.get(9).getId()), List.of(artistas.get(29).getId()));
        canciones.add(cancion);

        cancion = new CancionDTO(new ObjectId().toHexString(),
                "Otherside", albumes.get(29).getId(), List.of(generos.get(1).getId()), List.of(artistas.get(29).getId()));
        canciones.add(cancion);
        
        //CANCIONES INSERTADAS POR ang3lfco
        // Avión de Papel - Lefty SM
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Vuelo Libre", albumes.get(30).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(30).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Al Fin", albumes.get(30).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(30).getId())));

        // Voy Por El Sueño De Muchos - C-Kan
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Voy Por El Sueño De Muchos", albumes.get(31).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(31).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "La Vida No La Tienes Comprada", albumes.get(31).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(31).getId())));

        // Los Niños Grandes No Juegan - Gera MX
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Los Niños Grandes No Juegan", albumes.get(32).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(32).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "No Me Llames", albumes.get(32).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(32).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Nunca Te Pude Alcanzar", albumes.get(32).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(32).getId())));

        // Bones - Yoss Bones
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Eres Mi Aire", albumes.get(33).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(33).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "No Escucho tu Voz", albumes.get(33).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(33).getId())));

        // Directo Hasta Arriba - Dharius
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Estilo Malandro", albumes.get(34).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(34).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "La Raja", albumes.get(34).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(34).getId())));

        // Las Dos Caras - MC Davo
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Round 3", albumes.get(35).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(35).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Despertar Contigo", albumes.get(35).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(35).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Verde, Blanco y Rojo", albumes.get(35).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(35).getId())));

        // Golpe Avisa - Cartel de Santa
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Si Te Vienen a Contar", albumes.get(36).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(36).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Los Mensajes del WhatsApp", albumes.get(36).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(36).getId())));

        // Santos - Eme Malafe
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Que Chingón", albumes.get(37).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(37).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Salí Recio", albumes.get(37).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(37).getId())));

        // Rich Mafia Vol. 1 - Alemán (ft. Gera MX)
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Como Pacman", albumes.get(38).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(38).getId(), artistas.get(32).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Abran Paso", albumes.get(38).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(38).getId(), artistas.get(32).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Cabo Girl", albumes.get(38).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(38).getId(), artistas.get(32).getId())));

        // Alambre de Púas - El Mara
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "Tienes que alejarte", albumes.get(39).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(39).getId())));
        canciones.add(new CancionDTO(new ObjectId().toHexString(), "King Kong", albumes.get(39).getId(), List.of(generos.get(24).getId()), List.of(artistas.get(39).getId())));


        cancionNegocio.insertarCanciones(canciones);
    }
}
