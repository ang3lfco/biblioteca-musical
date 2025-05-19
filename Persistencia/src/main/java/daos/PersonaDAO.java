/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import com.mongodb.client.MongoCollection;
import conexion.MongoConexion;
import entidades.Persona;
import interfaces.IPersonaDAO;
import java.util.List;

/**
 *
 * @author Oribiel
 */
public class PersonaDAO implements IPersonaDAO {

    private final MongoCollection<Persona> coleccion;

    public PersonaDAO() {
        this.coleccion = MongoConexion.getPersonaCollection();
    }
    
        public void insertarPersonas(List<Persona> personas) {
        if (personas != null && !personas.isEmpty()) {
            coleccion.insertMany(personas);
            System.out.println("Se insertaron " + personas.size() + " personas correctamente.");
        } else {
            System.out.println("No se inserto ningun personas.");
        }
    }

}
