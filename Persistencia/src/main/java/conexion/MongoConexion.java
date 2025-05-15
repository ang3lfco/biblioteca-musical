/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entidades.Album;
import entidades.Artista;
import entidades.Cancion;
import entidades.Genero;
import entidades.Persona;
import entidades.Usuario;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author ang3lfco
 */
public class MongoConexion {
    private static final String stringConn_BD = "mongodb://localhost:27017";
    private static final String nombre_BD = "bibliotecaMusical4";
    private static final MongoClient mongoClient;
    private static final MongoDatabase mongoDatabase;

    static {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            pojoCodecRegistry
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(stringConn_BD))
                .codecRegistry(codecRegistry)
                .build();

        mongoClient = MongoClients.create(settings);
        mongoDatabase = mongoClient.getDatabase(nombre_BD);
    }

    public static MongoDatabase getDatabase() {
        return mongoDatabase;
    }

    public static MongoCollection<Album> getAlbumCollection() {
        return mongoDatabase.getCollection("albumes", Album.class);
    }
    public static MongoCollection<Cancion> getCancionCollection() {
        return mongoDatabase.getCollection("canciones", Cancion.class);
    }
    public static MongoCollection<Artista> getArtistaCollection() {
        return mongoDatabase.getCollection("artistas", Artista.class);
    }
    public static MongoCollection<Usuario> getUsuarioCollection() {
        return mongoDatabase.getCollection("usuarios", Usuario.class);
    }
    public static MongoCollection<Genero> getGeneroCollection() {
        return mongoDatabase.getCollection("generos", Genero.class);
    }
    public static MongoCollection<Persona> getPersonaCollection() {
        return mongoDatabase.getCollection("personas", Persona.class);
    }
}
