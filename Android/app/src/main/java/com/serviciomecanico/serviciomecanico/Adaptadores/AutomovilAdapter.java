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

import com.serviciomecanico.serviciomecanico.Modelo.Automovil;
import com.serviciomecanico.serviciomecanico.Modelo.Cliente;
import com.serviciomecanico.serviciomecanico.R;

import java.util.List;

public class AutomovilAdapter extends RecyclerView.Adapter<AutomovilAdapter.ViewHolder> {

    List<Automovil> automovil;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cdv_automovil;
        public TextView txv_automovil_placa, txv_automovil_marca, txv_automovil_linea;
        public ImageView imageView_automovil;

        public ViewHolder(View view){
            super(view);
            cdv_automovil = view.findViewById(R.id.cdv_automovil);
            txv_automovil_placa = view.findViewById(R.id.txv_automovil_placa);
            txv_automovil_marca = view.findViewById(R.id.txv_automovil_marca);
            txv_automovil_linea = view.findViewById(R.id.txv_automovil_linea);
            imageView_automovil = view.findViewById(R.id.imageView_automovil);
        }
    }

    public AutomovilAdapter(Context context, List<Automovil> automovil){
        this.context = context;
        this.automovil = automovil;
    }

    //Crecion del holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.automovil,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //La vida del holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.txv_cliente_nombre.setText(cliente.get(position).nombre);
        //holder.txv_cliente_correo.setText(cliente.get(position).correo);
        //holder.txv_cliente_telefono.setText(cliente.get(position).telefono);
        //Toast.makeText(context,cliente.get(position).urlImagen,Toast.LENGTH_LONG).show();
        //holder.imageView_cliente.loadImageUrl(cliente.get(position).urlImagen);
    }

    @Override
    public int getItemCount() {
        return automovil.size();
    }
}
