package com.tutorial.tutorialclientes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdaptadorCliente extends RecyclerView.Adapter<AdaptadorCliente.ViewHolder> {

    List<Cliente> cliente;
    static Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cdv_cliente;
        public TextView txv_cliente_nombre, txv_cliente_correo, txv_cliente_direccion, txv_cliente_telefono;

        public ViewHolder(View view){
            super(view);
            cdv_cliente = view.findViewById(R.id.cdv_cliente);
            txv_cliente_nombre = view.findViewById(R.id.txv_cliente_nombre);
            txv_cliente_correo = view.findViewById(R.id.txv_cliente_correo);
            txv_cliente_direccion = view.findViewById(R.id.txv_cliente_direccion);
            txv_cliente_telefono = view.findViewById(R.id.txv_cliente_telefono);
        }
    }

    public AdaptadorCliente(Context context,List<Cliente> cliente){
        this.context = context;
        this.cliente = cliente;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cliente,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return cliente.size();
    }
}
