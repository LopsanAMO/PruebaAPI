package app.genex.com.junostyapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import app.genex.com.junostyapi.API.ApiService;
import app.genex.com.junostyapi.models.Usuario;
import app.genex.com.junostyapi.models.UsuarioRespuesta;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private   Retrofit retrofit;
    private static final String TAG = "Usuario";

    private RecyclerView recyclerView;
    private ListaUsuarioAdapter listaUsuarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaUsuarioAdapter = new ListaUsuarioAdapter();
        recyclerView.setAdapter(listaUsuarioAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://52.39.36.176:8000/apiv1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();


    }
    private void obtenerDatos(){

        ApiService service = retrofit.create(ApiService.class);
        retrofit2.Call<UsuarioRespuesta> usuarioRespuestaCall = service.obtenerListaUsuario();

        usuarioRespuestaCall.enqueue(new Callback<UsuarioRespuesta>() {
                                         @Override
                                         public void onResponse(retrofit2.Call<UsuarioRespuesta> call, Response<UsuarioRespuesta> response) {
                                             if(response.isSuccessful()) {
                                                 UsuarioRespuesta usuarioRespuesta = response.body();
                                                 ArrayList<Usuario> listausuario = usuarioRespuesta.getResultado();
                                                 listaUsuarioAdapter.adicionarlsitaUsario(listausuario);


                                                 for (int i =0; i<listausuario.size(); i++){
                                                     Usuario u = listausuario.get(i);
                                                     Log.i(TAG, "Usuairo:"+ u.getUser());

                                                 }



                                             }


                                             else{
                                                 Log.e(TAG, "onResponse : "+ response.errorBody());

                                             }

                                         }

                                         @Override
                                         public void onFailure(retrofit2.Call<UsuarioRespuesta> call, Throwable t) {
                                             Log.e(TAG, "onFailure : "+ t.getMessage());
                                         }
                                     }
        );


    }
}
