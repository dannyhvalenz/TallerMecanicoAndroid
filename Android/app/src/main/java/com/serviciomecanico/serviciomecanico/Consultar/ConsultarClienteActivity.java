package com.serviciomecanico.serviciomecanico.Consultar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Actualizar.ActualizarClienteActivity;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.Mapas.MapsActivity2;
import com.serviciomecanico.serviciomecanico.MenuPrincipalActivity;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarAutomovilesActivity;

public class ConsultarClienteActivity extends AppCompatActivity {

    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    Conexion  conexion = new Conexion();
    DatabaseReference firebase;
    TextView edt_nombre_consultar, edt_correo_consultar, edt_telefono_consultar;
    String nombre, correo, telefono, urlimagen, latitud, longitud;
    ImageView img_avatar_consultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);
        firebase = conexion.conexion();



        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cliente");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edt_nombre_consultar = findViewById(R.id.edt_nombre_consultar);
        edt_correo_consultar = findViewById(R.id.edt_correo_consultar);
        edt_telefono_consultar = findViewById(R.id.edt_telefono_consultar);
        img_avatar_consultar = findViewById(R.id.img_avatar_consultar);

        //Obtener variables del cardView

        correo = getIntent().getStringExtra("correo");
        telefono = getIntent().getStringExtra("telefono");
        urlimagen = getIntent().getStringExtra("urlimagen");
        latitud = getIntent().getStringExtra("latitud");
        longitud = getIntent().getStringExtra("longitud");
        nombre = getIntent().getStringExtra("nombre");

        edt_nombre_consultar.setText(getIntent().getStringExtra("nombre"));
        edt_correo_consultar.setText(getIntent().getStringExtra("correo"));
        edt_telefono_consultar.setText(getIntent().getStringExtra("telefono"));

        edt_telefono_consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroTel = edt_telefono_consultar.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+numeroTel));
                startActivity(i);
            }
        });

        pedirPermiso();

        Glide.with(getApplicationContext())
                .load(urlimagen)
                .into(img_avatar_consultar);
    }

    public void pedirPermiso(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ConsultarClienteActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            return;
        }
    }

    public void btn_direccion_maps(View view){
        Intent intent = new Intent(ConsultarClienteActivity.this, MapsActivity2.class);
        intent.putExtra("latitud",latitud);
        intent.putExtra("longitud",longitud);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(ConsultarClienteActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_delete:
                AlertDialog.Builder eliminar = new AlertDialog.Builder(this);
                eliminar.setMessage("¿Desea eliminar este cliente?");
                eliminar.setTitle("Eliminar cliente");
                eliminar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child("Cliente").child(nombre).removeValue();
                        finish();
                    }
                });

                eliminar.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = eliminar.create();
                dialog.show();
                break;
            case R.id.action_edit:
                Intent intent2 = new Intent(ConsultarClienteActivity.this, ActualizarClienteActivity.class);
                intent2.putExtra("nombre",nombre);
                intent2.putExtra("correo",correo);
                intent2.putExtra("telefono",telefono);
                intent2.putExtra("urlimagen",urlimagen);
                intent2.putExtra("latitud",latitud);
                intent2.putExtra("longitud",longitud);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void btn_abajo_cliente(View view){
        Intent intent3 = new Intent(ConsultarClienteActivity.this, VisualizarAutomovilesActivity.class);
        intent3.putExtra("nombrecliente",nombre);
        startActivity(intent3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consultar_cliente, menu);
        return true;
    }




}
