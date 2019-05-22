package com.serviciomecanico.serviciomecanico.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.serviciomecanico.serviciomecanico.Modelo.Cita;
import com.serviciomecanico.serviciomecanico.Modelo.Cliente;
import com.serviciomecanico.serviciomecanico.R;

import java.util.List;

public class CitaAdapter extends RecyclerView.Adapter<CitaAdapter.ViewHolder> {

    List<Cita> cita;
    static Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cdv_cita;
        public TextView edt_hora, edt_fecha, txv_descripcion, txv_nombre;

        public ViewHolder(View view){
            super(view);
            cdv_cita = view.findViewById(R.id.cdv_cita);
            edt_hora = view.findViewById(R.id.edt_hora);
            edt_fecha = view.findViewById(R.id.edt_fecha);
            txv_descripcion = view.findViewById(R.id.txv_descripcion);
            txv_nombre = view.findViewById(R.id.txv_nombre);
        }
    }

    public CitaAdapter(Context context,List<Cita> cita){
        this.context = context;
        this.cita = cita;
    }

    //Crecion del holder
    @NonNull
    @Override
    public CitaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cita,parent,false);
        CitaAdapter.ViewHolder viewHolder = new CitaAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return cita.size();
    }
}
