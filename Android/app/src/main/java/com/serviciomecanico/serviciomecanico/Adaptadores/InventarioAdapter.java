package com.serviciomecanico.serviciomecanico.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.serviciomecanico.serviciomecanico.Modelo.Cita;
import com.serviciomecanico.serviciomecanico.Modelo.Inventario;
import com.serviciomecanico.serviciomecanico.R;

import java.util.List;

public class InventarioAdapter extends RecyclerView.Adapter<InventarioAdapter.ViewHolder> {

    List<Inventario> inventario;
    static Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cdv_inventario;
        public TextView edt_costo_inventario, edt_cantidad_inventario, txv_nombre_inventario, txv_descripcion_inventario;

        public ViewHolder(View view){
            super(view);
            cdv_inventario = view.findViewById(R.id.cdv_inventario);
            edt_costo_inventario = view.findViewById(R.id.edt_costo_inventario);
            edt_cantidad_inventario = view.findViewById(R.id.edt_cantidad_inventario);
            txv_nombre_inventario = view.findViewById(R.id.txv_nombre_inventario);
            txv_descripcion_inventario = view.findViewById(R.id.txv_descripcion_inventario);
        }
    }

    public InventarioAdapter(Context context, List<Inventario> inventario){
        this.context = context;
        this.inventario = inventario;
    }

    //Crecion del holder
    @NonNull
    @Override
    public InventarioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventario,parent,false);
        InventarioAdapter.ViewHolder viewHolder = new InventarioAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return inventario.size();
    }
}
