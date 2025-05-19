/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.PersonaDTO;
import entidades.Persona;
import interfaces.IPersonaDAO;
import interfaces.IPersonaNegocio;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Oribiel
 */
public class PersonaNegocio implements IPersonaNegocio {

    private IPersonaDAO personaDAO;

    public PersonaNegocio(IPersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    @Override
    public void insertarPersonas(List<PersonaDTO> persona){
        List<Persona> lista = this.ConvertirListaDTOAListaEntidad(persona);
        this.personaDAO.insertarPersonas(lista);
    }
    
        private List<Persona> ConvertirListaDTOAListaEntidad(List<PersonaDTO> PersonaDTO) {
        List<Persona> resultados = new ArrayList<>();

        if (PersonaDTO != null) {

            for (PersonaDTO dto : PersonaDTO) {
                resultados.add(
                        this.convertirAEntidad(dto)
                );
            }
        }
        return resultados;
    }
            private Persona convertirAEntidad(PersonaDTO persona) {
        if (persona == null) {
            return null;
        }

        Persona entidad = new Persona(
                new ObjectId(persona.getId()),
                persona.getNombre(),
                persona.getApellido()
        );
        return entidad;
    }

}
