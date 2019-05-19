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

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Adaptadores.AutomovilAdapter;
import com.serviciomecanico.serviciomecanico.Adaptadores.ClienteAdapter;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.Consultar.ConsultarAutomovilActivity;
import com.serviciomecanico.serviciomecanico.Consultar.ConsultarClienteActivity;
import com.serviciomecanico.serviciomecanico.Modelo.Automovil;
import com.serviciomecanico.serviciomecanico.Modelo.Cliente;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarAutomovilActivity;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarClienteActivity;
import com.serviciomecanico.serviciomecanico.Sesion.IniciarSeionActivity;

public class VisualizarAutomovilesActivity extends AppCompatActivity {

    Conexion conexion = new Conexion();
    DatabaseReference firebase;

    RecyclerView rcv_visualizar_automoviles;
    FloatingActionButton btn_float_registrar_automoviles;
    ProgressBar progressBar_visualizar_automoviles;

    String nombrecliente;

    //Adapter
    FirebaseRecyclerAdapter<Automovil, AutomovilAdapter.ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_automoviles);
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lista de automoviles");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rcv_visualizar_automoviles = findViewById(R.id.rcv_visualizar_automoviles);
        btn_float_registrar_automoviles = findViewById(R.id.btn_float_registrar_automoviles);
        progressBar_visualizar_automoviles = findViewById(R.id.progressBar_visualizar_automoviles);

        progressBar_visualizar_automoviles.setVisibility(View.VISIBLE);

        nombrecliente = getIntent().getStringExtra("nombrecliente");

        //Setear al linerlayaour manager nuestro reciclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_visualizar_automoviles.setLayoutManager(linearLayoutManager);

        //FirebaseUI definido para llamar de nuestro firebase la lista
        adapter = new FirebaseRecyclerAdapter<Automovil, AutomovilAdapter.ViewHolder>(
                /*Clase que utilizaremos*/Automovil.class,
                /*La interfaz grafica*/R.layout.automovil,
                /*ViewHolder archivo ClienteAdapter*/AutomovilAdapter.ViewHolder.class,
                /*La referencia de firebase donde buscara*/firebase.child("Cliente").child(nombrecliente).child("Automovil")
        ) {
            @Override
            protected void populateViewHolder(final AutomovilAdapter.ViewHolder viewHolder, Automovil model, int position) {
                viewHolder.txv_automovil_placa.setText(model.getPlaca());
                viewHolder.txv_automovil_marca.setText(model.getMarca());
                viewHolder.txv_automovil_linea.setText(model.getLinea());
                String urlchida = model.getUrlImagenAutomovil();
                Glide.with(getApplicationContext())
                        .load(urlchida)
                        .into(viewHolder.imageView_automovil);
                final String modelo = model.getModelo();
                final String color = model.getColor();
                final String urlimagen = model.getUrlImagenAutomovil();
                viewHolder.cdv_automovil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VisualizarAutomovilesActivity.this, ConsultarAutomovilActivity.class);
                        intent.putExtra("placa",viewHolder.txv_automovil_placa.getText().toString());
                        intent.putExtra("marca",viewHolder.txv_automovil_marca.getText().toString());
                        intent.putExtra("linea",viewHolder.txv_automovil_linea.getText().toString());
                        intent.putExtra("modelo",modelo);
                        intent.putExtra("color",color);
                        intent.putExtra("urlimagen",urlimagen);
                        intent.putExtra("nombre",nombrecliente);
                        startActivity(intent);
                    }
                });

                progressBar_visualizar_automoviles.setVisibility(View.INVISIBLE);
            }
        };

        rcv_visualizar_automoviles.setAdapter(adapter);

        //Scroll funcion boton
        rcv_visualizar_automoviles.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    btn_float_registrar_automoviles.show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0 || dy<0 && btn_float_registrar_automoviles.isShown()){
                    btn_float_registrar_automoviles.hide();
                }
            }
        });
    }

    public void btn_float_registrar_automoviles (View view){
        Intent intent = new Intent(VisualizarAutomovilesActivity.this, RegistrarAutomovilActivity.class);
        intent.putExtra("nombrecliente",nombrecliente);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                Intent intent = new Intent(VisualizarAutomovilesActivity.this, VisualizarClientesActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
