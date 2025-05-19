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
import interfaces.IAlbumNegocio;
import interfaces.IArtistaNegocio;
import interfaces.ICancionNegocio;
import interfaces.IGeneroNegocio;
import interfaces.IPersonaNegocio;
import negocio.AlbumNegocio;
import negocio.ArtistaNegocio;
import negocio.CancionNegocio;
import negocio.GeneroNegocio;
import negocio.PersonaNegocio;

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
    }
}
