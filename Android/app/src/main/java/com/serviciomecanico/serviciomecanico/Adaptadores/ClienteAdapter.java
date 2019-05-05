package com.serviciomecanico.serviciomecanico.Adaptadores;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.master.glideimageview.GlideImageView;
import com.serviciomecanico.serviciomecanico.Modelo.Cliente;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarClientesActivity;
import com.squareup.okhttp.HttpUrl;

import java.net.URI;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {

    List<Cliente> cliente;
    static Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cdv_cliente;
        public TextView txv_cliente_nombre, txv_cliente_correo, txv_cliente_telefono;
        public ImageView imageView_cliente;

        public ViewHolder(View view){
            super(view);
            cdv_cliente = view.findViewById(R.id.cdv_cliente);
            txv_cliente_nombre = view.findViewById(R.id.txv_cliente_nombre);
            txv_cliente_correo = view.findViewById(R.id.txv_cliente_correo);
            txv_cliente_telefono = view.findViewById(R.id.txv_cliente_telefono);
            imageView_cliente = view.findViewById(R.id.imageView_cliente);
        }
    }

    public ClienteAdapter(Context context,List<Cliente> cliente){
        this.context = context;
        this.cliente = cliente;
    }

    //Crecion del holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cliente,parent,false);
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
        return cliente.size();
    }
}
