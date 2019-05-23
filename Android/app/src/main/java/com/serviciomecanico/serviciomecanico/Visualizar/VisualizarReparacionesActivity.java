package com.serviciomecanico.serviciomecanico.Visualizar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Adaptadores.AutomovilAdapter;
import com.serviciomecanico.serviciomecanico.Adaptadores.ReparacionAdapter;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.Consultar.ConsultarAutomovilActivity;
import com.serviciomecanico.serviciomecanico.Consultar.ConsultarReparacionActivity;
import com.serviciomecanico.serviciomecanico.MenuPrincipalActivity;
import com.serviciomecanico.serviciomecanico.Modelo.Automovil;
import com.serviciomecanico.serviciomecanico.Modelo.Reparacion;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarAutomovilActivity;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarReparacionActivity;

public class VisualizarReparacionesActivity extends AppCompatActivity {

    Conexion conexion = new Conexion();
    DatabaseReference firebase;

    RecyclerView rcv_visualizar_reparaciones;
    FloatingActionButton btn_float_registrar_reparaciones;
    ProgressBar progressBar_visualizar_reparaciones;

    String placa, nombre;

    //Adapter
    FirebaseRecyclerAdapter<Reparacion, ReparacionAdapter.ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_reparaciones);
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lista de reparaciones");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rcv_visualizar_reparaciones = findViewById(R.id.rcv_visualizar_reparaciones);
        btn_float_registrar_reparaciones = findViewById(R.id.btn_float_registrar_reparaciones);
        progressBar_visualizar_reparaciones = findViewById(R.id.progressBar_visualizar_reparaciones);

        progressBar_visualizar_reparaciones.setVisibility(View.VISIBLE);

        placa = getIntent().getStringExtra("placa");
        nombre = getIntent().getStringExtra("nombre");
        Toast.makeText(getApplicationContext(),nombre,Toast.LENGTH_SHORT).show();

        //Setear al linerlayaour manager nuestro reciclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_visualizar_reparaciones.setLayoutManager(linearLayoutManager);

        //FirebaseUI definido para llamar de nuestro firebase la lista
        adapter = new FirebaseRecyclerAdapter<Reparacion, ReparacionAdapter.ViewHolder>(
                /*Clase que utilizaremos*/Reparacion.class,
                /*La interfaz grafica*/R.layout.reparacion,
                /*ViewHolder archivo ClienteAdapter*/ReparacionAdapter.ViewHolder.class,
                /*La referencia de firebase donde buscara*/firebase.child("Cliente").child(nombre).child("Automovil").child(placa).child("Reparacion")
        ) {
            @Override
            protected void populateViewHolder(final ReparacionAdapter.ViewHolder viewHolder, Reparacion model, int position) {
                viewHolder.txv_reparacion_tipo.setText(model.getTipo());
                viewHolder.txv_reparacion_kilometraje.setText(model.getKilometraje());
                viewHolder.txv_reparacion_costo.setText(model.getCosto());
                String urlchida = model.getUrlImagenAReparacion();
                Glide.with(getApplicationContext())
                        .load(urlchida)
                        .into(viewHolder.imageView_reparacion);
                final String idReparacion = model.getKilometraje();
                final String descripcionFalla = model.getDescripcionFalla();
                final String descripcionMantenimiento = model.getDescripcionMantenimiento();
                final String urlimagen = model.getUrlImagenAReparacion();
                viewHolder.cdv_reparacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VisualizarReparacionesActivity.this, ConsultarReparacionActivity.class);
                        intent.putExtra("tipo",viewHolder.txv_reparacion_tipo.getText().toString());
                        intent.putExtra("kilometraje",viewHolder.txv_reparacion_kilometraje.getText().toString());
                        intent.putExtra("costo",viewHolder.txv_reparacion_costo.getText().toString());
                        intent.putExtra("descripcionFalla",descripcionFalla);
                        intent.putExtra("descripcionMantenimiento",descripcionMantenimiento);
                        intent.putExtra("urlimagen",urlimagen);
                        intent.putExtra("placa",placa);
                        intent.putExtra("nombre",nombre);
                        intent.putExtra("idReparacion",idReparacion);
                        startActivity(intent);
                    }
                });

                progressBar_visualizar_reparaciones.setVisibility(View.INVISIBLE);
            }
        };

        rcv_visualizar_reparaciones.setAdapter(adapter);

        //Scroll funcion boton
        rcv_visualizar_reparaciones.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    btn_float_registrar_reparaciones.show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0 || dy<0 && btn_float_registrar_reparaciones.isShown()){
                    btn_float_registrar_reparaciones.hide();
                }
            }
        });
    }

    public void btn_float_registrar_reparaciones (View view){
        Intent intent = new Intent(VisualizarReparacionesActivity.this, RegistrarReparacionActivity.class);
        intent.putExtra("placa",placa);
        intent.putExtra("nombre",nombre);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                Intent intent = new Intent(VisualizarReparacionesActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
