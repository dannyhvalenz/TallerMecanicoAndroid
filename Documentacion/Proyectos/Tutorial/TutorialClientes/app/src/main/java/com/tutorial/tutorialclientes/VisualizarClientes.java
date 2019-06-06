package com.tutorial.tutorialclientes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

public class VisualizarClientes extends AppCompatActivity {

    //Creando variable de referencia para la base de datos (Firebase)
    Conexion conexion = new Conexion();
    //Llamando a la clase conexion para la creacion de la referencia
    DatabaseReference firebase;

    RecyclerView rcv_visualizar_clientes;
    FloatingActionButton btn_float_registrar_clientes;
    FirebaseRecyclerAdapter<Cliente, AdaptadorCliente.ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_clientes);
        //Conexion a firebase
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lista de clientes");

        rcv_visualizar_clientes = findViewById(R.id.rcv_visualizar_clientes);
        btn_float_registrar_clientes = findViewById(R.id.btn_float_registrar_clientes);

        //Setear al linerlayaour manager nuestro reciclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_visualizar_clientes.setLayoutManager(linearLayoutManager);

        //FirebaseUI definido para llamar de nuestro firebase la lista
        adapter = new FirebaseRecyclerAdapter<Cliente, AdaptadorCliente.ViewHolder>(
                /*Clase que utilizaremos*/Cliente.class,
                /*La interfaz grafica*/R.layout.cliente,
                /*ViewHolder archivo ClienteAdapter*/AdaptadorCliente.ViewHolder.class,
                /*La referencia de firebase donde buscara*/firebase.child("Cliente")
        ) {
            @Override
            protected void populateViewHolder(final AdaptadorCliente.ViewHolder viewHolder, Cliente model, final int position) {
                viewHolder.txv_cliente_nombre.setText(model.getNombre());
                viewHolder.txv_cliente_correo.setText(model.getCorreo());
                viewHolder.txv_cliente_telefono.setText(model.getTelefono());
                viewHolder.txv_cliente_direccion.setText(model.getDireccion());
                viewHolder.cdv_cliente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VisualizarClientes.this, ConsultarCliente.class);
                        intent.putExtra("nombre",viewHolder.txv_cliente_nombre.getText().toString());
                        intent.putExtra("correo",viewHolder.txv_cliente_correo.getText().toString());
                        intent.putExtra("telefono",viewHolder.txv_cliente_telefono.getText().toString());
                        intent.putExtra("direccion",viewHolder.txv_cliente_direccion.getText().toString());
                        startActivity(intent);
                    }
                });
            }
        };

        rcv_visualizar_clientes.setAdapter(adapter);

        //Scroll para la funcion del boton flotante
        rcv_visualizar_clientes.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    btn_float_registrar_clientes.show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0 || dy<0 && btn_float_registrar_clientes.isShown()){
                    btn_float_registrar_clientes.hide();
                }
            }
        });
    }

    //Funcion del boton flotante
    public void btn_float_registrar_clientes (View view){
        Intent intent = new Intent(VisualizarClientes.this, RegistrarCliente.class);
        startActivity(intent);
    }
}
