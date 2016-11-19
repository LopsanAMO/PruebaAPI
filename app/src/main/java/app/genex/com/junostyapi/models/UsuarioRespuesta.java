package app.genex.com.junostyapi.models;

import java.util.ArrayList;

/**
 * Created by Invitado1 on 19/11/2016.
 */

public class UsuarioRespuesta {
    private ArrayList<Usuario> user;

    public ArrayList<Usuario> getResultado() {
        return user;
    }

    public void setResultado(ArrayList<Usuario> resultado) {
        this.user = resultado;
    }
}
