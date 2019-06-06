package com.tutorial.tutorialclientes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class ConsultarCliente extends AppCompatActivity {

    //Creando variable de referencia para la base de datos (Firebase)
    DatabaseReference firebase;
    //Llamando a la clase conexion para la creacion de la referencia
    Conexion conexion = new Conexion();

    TextView edt_nombre_consultar, edt_correo_consultar, edt_telefono_consultar, edt_direccion_consultar;
    String nombre, correo, telefono, direccion;

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
        edt_direccion_consultar = findViewById(R.id.edt_direccion_consultar);

        //Obtener variables del cardView

        correo = getIntent().getStringExtra("correo");
        telefono = getIntent().getStringExtra("telefono");
        direccion = getIntent().getStringExtra("direccion");
        nombre = getIntent().getStringExtra("nombre");

        edt_nombre_consultar.setText(getIntent().getStringExtra("nombre"));
        edt_correo_consultar.setText(getIntent().getStringExtra("correo"));
        edt_telefono_consultar.setText(getIntent().getStringExtra("telefono"));
        edt_direccion_consultar.setText(getIntent().getStringExtra("direccion"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(ConsultarCliente.this, VisualizarClientes.class);
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
                Intent intent2 = new Intent(ConsultarCliente.this, ActualizarCliente.class);
                intent2.putExtra("nombre",nombre);
                intent2.putExtra("correo",correo);
                intent2.putExtra("telefono",telefono);
                intent2.putExtra("direccion",direccion);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consultar_cliente, menu);
        return true;
    }
}
