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
import com.serviciomecanico.serviciomecanico.Modelo.Reparacion;
import com.serviciomecanico.serviciomecanico.R;

import java.util.List;

public class ReparacionAdapter extends RecyclerView.Adapter<ReparacionAdapter.ViewHolder> {

    List<Reparacion> reparacion;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cdv_reparacion;
        public TextView txv_reparacion_tipo, txv_reparacion_kilometraje, txv_reparacion_costo;
        public ImageView imageView_reparacion;

        public ViewHolder(View view){
            super(view);
            cdv_reparacion = view.findViewById(R.id.cdv_reparacion);
            txv_reparacion_tipo = view.findViewById(R.id.txv_reparacion_tipo);
            txv_reparacion_kilometraje = view.findViewById(R.id.txv_reparacion_kilometraje);
            txv_reparacion_costo = view.findViewById(R.id.txv_reparacion_costo);
            imageView_reparacion = view.findViewById(R.id.imageView_reparacion);
        }
    }

    public ReparacionAdapter(Context context, List<Reparacion> reparacion){
        this.context = context;
        this.reparacion = reparacion;
    }

    //Crecion del holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reparacion,parent,false);
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
        return reparacion.size();
    }
}
