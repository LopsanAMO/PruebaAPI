package app.genex.com.junostyapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.genex.com.junostyapi.models.Usuario;
import app.genex.com.junostyapi.models.UsuarioRespuesta;

/**
 * Created by Invitado1 on 19/11/2016.
 */

public class ListaUsuarioAdapter extends RecyclerView.Adapter<ListaUsuarioAdapter.ViewHolder>{

    private ArrayList<UsuarioRespuesta> dataset;

    public ListaUsuarioAdapter(){
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario,parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UsuarioRespuesta u = dataset.get(position);
        Usuario usuario =  u.getUser();
        holder.nombreTextView.setText(usuario.getUsername());
        

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void adicionarlsitaUsario(ArrayList<UsuarioRespuesta> listausuario) {
        dataset.addAll(listausuario);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);


        }
    }
}
