package com.serviciomecanico.serviciomecanico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.Modelo.Cita;
import com.serviciomecanico.serviciomecanico.Modelo.Inventario;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarCitaActivity;

public class RegistrarInventario extends AppCompatActivity {

    //Creando variable de referencia para la base de datos (Firebase)
    DatabaseReference firebase;
    //Llamando a la clase conexion para la creacion de la referencia
    Conexion conexion = new Conexion();

    String urlimagenReparacion, placa, idReparacion, nombre;

    int contador=0;

    //Componentes que ocupamos para la vista (Layout)
    EditText edt_costo_inventario, edt_cantidad_inventario, edt_descripcion_inventario, edt_inventario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_inventario);

        firebase = conexion.conexion();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registrar inventario");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edt_costo_inventario = findViewById(R.id.edt_costo_inventario);
        edt_cantidad_inventario = findViewById(R.id.edt_cantidad_inventario);
        edt_descripcion_inventario = findViewById(R.id.edt_descripcion_inventario);
        edt_inventario = findViewById(R.id.edt_inventario);
    }

    public void btn_registrar_inventario(View view){
        String costo = edt_costo_inventario.getText().toString();
        String cantidad = edt_cantidad_inventario.getText().toString();
        String descripcion = edt_descripcion_inventario.getText().toString();
        String nombre = edt_inventario.getText().toString();
        String id = nombre;

        if (TextUtils.isEmpty(costo)) {
            Toast.makeText(getApplicationContext(), "Ingresa el costo", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(cantidad)) {
            Toast.makeText(getApplicationContext(), "Ingresa una cantidad", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(descripcion)) {
            Toast.makeText(getApplicationContext(), "Ingresa un descripción", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getApplicationContext(), "Ingresa un nombre", Toast.LENGTH_SHORT).show();
        }else if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(descripcion) && !TextUtils.isEmpty(costo) && !TextUtils.isEmpty(cantidad)) {
            Inventario inventario = new Inventario(nombre,descripcion,costo,cantidad);
            firebase.child("Inventario").child(id).setValue(inventario);
            Toast.makeText(getApplicationContext(),"Inventario agregado correctamente",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                Intent intent = new Intent(RegistrarInventario.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
