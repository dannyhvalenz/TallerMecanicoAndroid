package com.serviciomecanico.serviciomecanico.Registrar;

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
import com.serviciomecanico.serviciomecanico.MenuPrincipalActivity;
import com.serviciomecanico.serviciomecanico.Modelo.Herramienta;
import com.serviciomecanico.serviciomecanico.R;

public class RegistrarHerramientas extends AppCompatActivity {

    EditText edt_herramienta,edt_cantidad_herramienta,edt_marca_herramienta,edt_descripcion_herramienta;
    DatabaseReference firebase;
    Conexion conexion = new Conexion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_herramientas);

        firebase = conexion.conexion();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registrar herramienta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edt_herramienta = findViewById(R.id.edt_herramienta);
        edt_cantidad_herramienta = findViewById(R.id.edt_cantidad_herramienta);
        edt_marca_herramienta = findViewById(R.id.edt_marca_herramienta);
        edt_descripcion_herramienta = findViewById(R.id.edt_descripcion_herramienta);
    }

    public void btn_registrar_herramienta(View view){
        String nombre = edt_herramienta.getText().toString();
        String cantidad = edt_cantidad_herramienta.getText().toString();
        String marca = edt_marca_herramienta.getText().toString();
        String descripcion = edt_descripcion_herramienta.getText().toString();
        String id = nombre;

        if (TextUtils.isEmpty(marca)) {
            Toast.makeText(getApplicationContext(), "Ingresa la marca", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(cantidad)) {
            Toast.makeText(getApplicationContext(), "Ingresa la cantidad", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(descripcion)) {
            Toast.makeText(getApplicationContext(), "Ingresa una descripción", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getApplicationContext(), "Ingresa nombre de la herramienta", Toast.LENGTH_SHORT).show();
        }else if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(descripcion) && !TextUtils.isEmpty(marca) && !TextUtils.isEmpty(cantidad)) {
            Herramienta herramienta = new Herramienta(nombre,descripcion,marca,cantidad);
            firebase.child("Herramienta").child(id).setValue(herramienta);
            Toast.makeText(getApplicationContext(),"Cita agregada correctamente",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                Intent intent = new Intent(RegistrarHerramientas.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
