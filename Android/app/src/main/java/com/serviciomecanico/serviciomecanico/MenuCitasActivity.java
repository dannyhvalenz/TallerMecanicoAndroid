package com.serviciomecanico.serviciomecanico;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Adaptadores.ClienteAdapter;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.Modelo.Cliente;
import com.serviciomecanico.serviciomecanico.Registrar.RegisterAdministradorActivity;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarCitaActivity;

public class MenuCitasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        setContentView(R.layout.activity_menu_citas);
        firebase = conexion.conexion();

        btn_float_registrar_cliente = findViewById(R.id.btn_float_registrar_citas);
        btn_float_registrar_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuCitasActivity.this, RegistrarCitaActivity.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_citas);
        NavigationView navigationView = findViewById(R.id.nav_view_citas);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_citas);
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
            Intent intent = new Intent(MenuCitasActivity.this, RegisterAdministradorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_clientes) {
            Intent intent = new Intent(MenuCitasActivity.this, MenuPrincipalActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_citas) {
            Toast.makeText(getApplicationContext(),"Ya te encuestras en clientes",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_inventario) {
            Intent intent = new Intent(MenuCitasActivity.this, MenuInventarioActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_herramientas) {
            Intent intent = new Intent(MenuCitasActivity.this, MenuHerramientasActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_cerrar_sesion) {
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
