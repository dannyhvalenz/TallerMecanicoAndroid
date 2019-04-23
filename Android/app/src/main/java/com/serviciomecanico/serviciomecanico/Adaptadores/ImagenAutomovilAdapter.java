package com.serviciomecanico.serviciomecanico.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImagenAutomovilAdapter extends RecyclerView.Adapter<ImagenAutomovilAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    //Creando variable de referencia para la base de datos (Firebase)
    DatabaseReference firebase;
    //Llamando a la clase conexion para la creacion de la referencia
    Conexion conexion = new Conexion();

    //vars
    private ArrayList<String> mImageUrls;
    private Context mContext;

    public ImagenAutomovilAdapter(Context context, ArrayList<String> imageUrls) {
        mImageUrls = imageUrls;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Una vez se inicia la actividad se manda a llamar a firebase
        firebase = conexion.conexion();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_avatar_automovil, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(position))
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Avatar guardado",Toast.LENGTH_LONG).show();
                //Guarde en cliente un hijo llamado nombre con el valor de el cliente que estamos creando
                firebase.child("Url").child("url").setValue(mImageUrls.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageAutomovil);
        }
    }
}
