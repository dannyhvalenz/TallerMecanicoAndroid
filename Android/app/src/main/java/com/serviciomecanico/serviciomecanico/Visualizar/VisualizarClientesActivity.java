package com.serviciomecanico.serviciomecanico.Visualizar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.serviciomecanico.serviciomecanico.Adaptadores.ClienteAdapter;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Adaptadores.ClienteAdapter;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.Consultar.ConsultarClienteActivity;
import com.serviciomecanico.serviciomecanico.Modelo.Cliente;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarClienteActivity;
import com.serviciomecanico.serviciomecanico.Sesion.IniciarSeionActivity;

public class VisualizarClientesActivity extends AppCompatActivity {

    Conexion conexion = new Conexion();
    DatabaseReference firebase;

    RecyclerView rcv_visualizar_clientes;
    FloatingActionButton btn_float_registrar_cliente;
    ProgressBar progressBar_visualizar_clientes;

    //Adapter
    FirebaseRecyclerAdapter<Cliente, ClienteAdapter.ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_clientes);
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lista de clientes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rcv_visualizar_clientes = findViewById(R.id.rcv_visualizar_clientes);
        btn_float_registrar_cliente = findViewById(R.id.btn_float_registrar_cliente);
        progressBar_visualizar_clientes = findViewById(R.id.progressBar_visualizar_clientes);

        progressBar_visualizar_clientes.setVisibility(View.VISIBLE);

        //Setear al linerlayaour manager nuestro reciclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_visualizar_clientes.setLayoutManager(linearLayoutManager);

        //FirebaseUI definido para llamar de nuestro firebase la lista
        adapter = new FirebaseRecyclerAdapter<Cliente, ClienteAdapter.ViewHolder>(
                /*Clase que utilizaremos*/Cliente.class,
                /*La interfaz grafica*/R.layout.cliente,
                /*ViewHolder archivo ClienteAdapter*/ClienteAdapter.ViewHolder.class,
                /*La referencia de firebase donde buscara*/firebase.child("Cliente")
        ) {
            @Override
            protected void populateViewHolder(final ClienteAdapter.ViewHolder viewHolder, Cliente model, final int position) {
                viewHolder.txv_cliente_nombre.setText(model.getNombre());
                viewHolder.txv_cliente_correo.setText(model.getCorreo());
                viewHolder.txv_cliente_telefono.setText(model.getTelefono());
                String urlchida = model.getUrlImagen();
                Glide.with(getApplicationContext())
                        .load(urlchida)
                        .into(viewHolder.imageView_cliente);
                final String urlimagen = model.getUrlImagen();
                final String latitud = model.getLatitud();
                final String longitud = model.getLongitud();
                viewHolder.cdv_cliente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VisualizarClientesActivity.this, ConsultarClienteActivity.class);
                        intent.putExtra("nombre",viewHolder.txv_cliente_nombre.getText().toString());
                        intent.putExtra("correo",viewHolder.txv_cliente_correo.getText().toString());
                        intent.putExtra("telefono",viewHolder.txv_cliente_telefono.getText().toString());
                        intent.putExtra("urlimagen",urlimagen);
                        intent.putExtra("latitud",latitud);
                        intent.putExtra("longitud",longitud);
                        startActivity(intent);
                    }
                });

                progressBar_visualizar_clientes.setVisibility(View.INVISIBLE);
            }
        };

        rcv_visualizar_clientes.setAdapter(adapter);

        //Scroll funcion boton
        rcv_visualizar_clientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    btn_float_registrar_cliente.show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0 || dy<0 && btn_float_registrar_cliente.isShown()){
                    btn_float_registrar_cliente.hide();
                }
            }
        });
    }

    public void btn_float_registrar_cliente (View view){
        Intent intent = new Intent(VisualizarClientesActivity.this, RegistrarClienteActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                Intent intent = new Intent(VisualizarClientesActivity.this, IniciarSeionActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
