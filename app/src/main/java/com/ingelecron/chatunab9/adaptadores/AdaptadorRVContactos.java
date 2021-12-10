package com.ingelecron.chatunab9.adaptadores;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ingelecron.chatunab9.R;
import com.ingelecron.chatunab9.data.db.modelos.Contacto;
import com.ingelecron.chatunab9.vistas.activity.DetalleActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorRVContactos extends RecyclerView.Adapter<AdaptadorRVContactos.RVContactosViewHolder> {

    private Activity contexto;
    private ArrayList<Contacto> contactoArrayList;

    public AdaptadorRVContactos(Activity contexto, ArrayList<Contacto> contactoArrayList) {
        this.contexto = contexto;
        this.contactoArrayList = contactoArrayList;
    }

    @NonNull
    @Override
    public RVContactosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_rvcontactos, parent, false);

        RVContactosViewHolder inicioRvViewHolder= new RVContactosViewHolder(itemView);

        return inicioRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RVContactosViewHolder holder, int position) {

        final Contacto contacto =  contactoArrayList.get(position);

        Picasso.get().load(contacto.getFoto()).placeholder(R.drawable.ic_logo).error(R.drawable.ic_logo).into(holder.iv_foto);

//        holder.iv_foto.setImageResource(Integer.parseInt(contacto.getFoto()));

        holder.iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(contexto, "enviar nombre", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(contexto, DetalleActivity.class);
                intent.putExtra("nombre", contacto.getNombre());

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){

                    Explode explode=new Explode();
                    explode.setDuration(1000);
                    contexto.getWindow().setExitTransition(explode);
                    contexto.startActivity(intent,
                            ActivityOptions.makeSceneTransitionAnimation(contexto, view, "animacion").toBundle());

                }else {
                    contexto.startActivity(intent);
                }
            }
        });

        holder.tv_nombre.setText(contacto.getNombre());
        holder.tv_especie.setText(contacto.getEspecie());

    }

    @Override
    public int getItemCount() {
        return contactoArrayList.size();
    }

    public class RVContactosViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_foto;
        private TextView tv_nombre, tv_especie;

        public RVContactosViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_foto= itemView.findViewById(R.id.iv_foto);
            tv_nombre= itemView.findViewById(R.id.tv_nombre);
            tv_especie= itemView.findViewById(R.id.tv_especie);
        }
    }
}
