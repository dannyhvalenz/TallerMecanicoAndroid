package com.serviciomecanico.serviciomecanico.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.serviciomecanico.serviciomecanico.Modelo.Herramienta;
import com.serviciomecanico.serviciomecanico.Modelo.Inventario;
import com.serviciomecanico.serviciomecanico.R;

import java.util.List;

public class HerramientaAdapter extends RecyclerView.Adapter<HerramientaAdapter.ViewHolder> {

    List<Herramienta> herramienta;
    static Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cdv_herramienta;
        public TextView edt_marca_herramienta, edt_cantidad_herramienta, txv_nombre_herramienta, txv_descripcion_herramienta;

        public ViewHolder(View view){
            super(view);
            cdv_herramienta = view.findViewById(R.id.cdv_herramienta);
            edt_marca_herramienta = view.findViewById(R.id.edt_marca_herramienta);
            edt_cantidad_herramienta = view.findViewById(R.id.edt_cantidad_herramienta);
            txv_nombre_herramienta = view.findViewById(R.id.txv_nombre_herramienta);
            txv_descripcion_herramienta = view.findViewById(R.id.txv_descripcion_herramienta);
        }
    }

    public HerramientaAdapter(Context context, List<Herramienta> herramienta){
        this.context = context;
        this.herramienta = herramienta;
    }

    //Crecion del holder
    @NonNull
    @Override
    public HerramientaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.herramienta,parent,false);
        HerramientaAdapter.ViewHolder viewHolder = new HerramientaAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return herramienta.size();
    }
}
