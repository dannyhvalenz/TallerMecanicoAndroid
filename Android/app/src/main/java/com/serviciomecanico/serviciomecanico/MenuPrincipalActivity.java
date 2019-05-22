package com.serviciomecanico.serviciomecanico;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Adaptadores.CitaAdapter;
import com.serviciomecanico.serviciomecanico.Adaptadores.ClienteAdapter;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.Consultar.ConsultarClienteActivity;
import com.serviciomecanico.serviciomecanico.Modelo.Cita;
import com.serviciomecanico.serviciomecanico.Modelo.Cliente;
import com.serviciomecanico.serviciomecanico.Registrar.RegisterAdministradorActivity;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarCitaActivity;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarClienteActivity;

public class MenuPrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Conexion conexion = new Conexion();
    DatabaseReference firebase;

    RecyclerView rcv_visualizar_clientes;
    FloatingActionButton btn_float_registrar_clientes;
    ProgressBar progressBar_visualizar_clientes;

    //Adapter
    FirebaseRecyclerAdapter<Cliente, ClienteAdapter.ViewHolder> adapter;
    FirebaseRecyclerAdapter<Cita, CitaAdapter.ViewHolder> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        firebase = conexion.conexion();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        rcv_visualizar_clientes = findViewById(R.id.rcv_visualizar_clientes);
        btn_float_registrar_clientes = findViewById(R.id.btn_float_registrar_clientes);
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
                        Intent intent = new Intent(MenuPrincipalActivity.this, ConsultarClienteActivity.class);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_registrar_administrador) {
            Intent intent = new Intent(MenuPrincipalActivity.this, RegisterAdministradorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_clientes) {
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
                            Intent intent = new Intent(MenuPrincipalActivity.this, ConsultarClienteActivity.class);
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
        } else if (id == R.id.nav_citas) {
            //FirebaseUI definido para llamar de nuestro firebase la lista
            adapter2 = new FirebaseRecyclerAdapter<Cita, CitaAdapter.ViewHolder>(
                    /*Clase que utilizaremos*/Cita.class,
                    /*La interfaz grafica*/R.layout.cita,
                    /*ViewHolder archivo ClienteAdapter*/CitaAdapter.ViewHolder.class,
                    /*La referencia de firebase donde buscara*/firebase.child("Cita")
            ) {
                @Override
                protected void populateViewHolder(final CitaAdapter.ViewHolder viewHolder, Cita model,final int position) {
                    viewHolder.edt_fecha.setText(model.getFecha());
                    viewHolder.edt_hora.setText(model.getHora());
                    viewHolder.txv_nombre.setText(model.getCliente());
                    viewHolder.txv_descripcion.setText(model.getDescripcion());
                    viewHolder.cdv_cita.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });

                    progressBar_visualizar_clientes.setVisibility(View.INVISIBLE);
                }
            };

            rcv_visualizar_clientes.setAdapter(adapter2);

            //Scroll funcion boton
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

            btn_float_registrar_clientes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MenuPrincipalActivity.this, RegistrarCitaActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else if (id == R.id.nav_inventario) {

        } else if (id == R.id.nav_herramientas) {

        } else if (id == R.id.nav_cerrar_sesion) {
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void btn_float_registrar_clientes (View view){
        Intent intent = new Intent(MenuPrincipalActivity.this, RegistrarClienteActivity.class);
        startActivity(intent);
    }
}
