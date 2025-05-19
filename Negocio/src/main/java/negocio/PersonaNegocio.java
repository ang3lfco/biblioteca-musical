/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import entidades.Persona;
import interfaces.IPersonaDAO;
import interfaces.IPersonaNegocio;
import java.util.List;

/**
 *
 * @author Oribiel
 */
public class PersonaNegocio implements IPersonaNegocio {

    private IPersonaDAO personaDAO;

    public PersonaNegocio(IPersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    public void insertarPersonas(List<Persona> personas) {
        personaDAO.insertarPersonas(personas);
    }

}
