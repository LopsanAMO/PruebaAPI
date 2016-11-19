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
import java.util.List;

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
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listaUsuarioAdapter);



        retrofit = new Retrofit.Builder()
                .baseUrl("http://52.39.36.176:8000/apiv1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();


    }
    private void obtenerDatos(){

        ApiService service = retrofit.create(ApiService.class);
        retrofit2.Call<List<UsuarioRespuesta>> usuarioRespuestaCall = service.obtenerListaUsuario();

        usuarioRespuestaCall.enqueue(new Callback<List<UsuarioRespuesta>>() {
                                         @Override
                                         public void onResponse(retrofit2.Call<List<UsuarioRespuesta>> call, Response<List<UsuarioRespuesta>>response) {
                                             if(response.isSuccessful()) {
                                                     List<UsuarioRespuesta> usuarioRespuesta = response.body();
//                                                 Usuario usuario = usuarioRespuesta.get(0).getUser();

                                                 listaUsuarioAdapter.adicionarlsitaUsario((ArrayList<UsuarioRespuesta>) usuarioRespuesta);


                                                 /*for (int i =0; i<listausuario.size(); i++){
                                                     Usuario u = listausuario.get(i);
                                                     Log.i(TAG, "Usuairo:"+ u.getUsername());
                                                 }*/
//                                                 Log.i(TAG, "Usuario: "+ usuario.getUsername());



                                             }


                                             else{
                                                 Log.e(TAG, "onResponse : "+ response.errorBody());

                                             }

                                         }

                                         @Override
                                         public void onFailure(retrofit2.Call<List<UsuarioRespuesta>>call, Throwable t) {
                                             Log.e(TAG, "onFailure : "+ t.getMessage());
                                         }
                                     }
        );


    }
}
