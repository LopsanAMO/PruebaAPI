package app.genex.com.junostyapi.API;

import app.genex.com.junostyapi.models.UsuarioRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Invitado1 on 19/11/2016.
 */

public interface ApiService {

    @GET("/horario")
    Call<UsuarioRespuesta> obtenerListaUsuario();
}
