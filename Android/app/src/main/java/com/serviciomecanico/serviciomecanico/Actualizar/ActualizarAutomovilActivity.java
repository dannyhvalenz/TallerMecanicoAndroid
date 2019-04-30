package com.serviciomecanico.serviciomecanico.Actualizar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.Modelo.Automovil;
import com.serviciomecanico.serviciomecanico.Modelo.Cliente;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarAutomovilesActivity;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarClientesActivity;

public class ActualizarAutomovilActivity extends AppCompatActivity {

    Conexion conexion = new Conexion();
    DatabaseReference firebase;

    EditText edt_placa_actualizar, edt_marca_actualizar, edt_linea_actualizar,edt_modelo_actualizar,edt_color_actualizar;
    String recibonombre, urlimagen, placaAntigua;
    ImageView img_avatar_actualizar_automovil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_automovil);
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Actualizar cliente");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edt_placa_actualizar = findViewById(R.id.edt_placa_actualizar);
        edt_marca_actualizar = findViewById(R.id.edt_marca_actualizar);
        edt_linea_actualizar = findViewById(R.id.edt_linea_actualizar);
        edt_modelo_actualizar = findViewById(R.id.edt_modelo_actualizar);
        edt_color_actualizar = findViewById(R.id.edt_color_actualizar);
        img_avatar_actualizar_automovil = findViewById(R.id.img_avatar_actualizar_automovil);

        recibonombre = getIntent().getStringExtra("nombre");
        placaAntigua = getIntent().getStringExtra("placa");

        edt_placa_actualizar.setText(getIntent().getStringExtra("placa"));
        edt_marca_actualizar.setText(getIntent().getStringExtra("marca"));
        edt_linea_actualizar.setText(getIntent().getStringExtra("linea"));
        edt_modelo_actualizar.setText(getIntent().getStringExtra("modelo"));
        edt_color_actualizar.setText(getIntent().getStringExtra("color"));

        urlimagen = getIntent().getStringExtra("urlimagen");

        Glide.with(getApplicationContext())
                .load(urlimagen)
                .into(img_avatar_actualizar_automovil);

        firebase.child("Automovil").child(recibonombre).child(placaAntigua).removeValue();
    }

    public void btn_actualizar_automovil(View view){
        String placa = edt_placa_actualizar.getText().toString();
        String marca = edt_marca_actualizar.getText().toString();
        String linea = edt_linea_actualizar.getText().toString();
        String modelo = edt_modelo_actualizar.getText().toString();
        String color = edt_color_actualizar.getText().toString();

        if (TextUtils.isEmpty(placa)) {
            Toast.makeText(getApplicationContext(), "Ingresar placa", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(marca)) {
            Toast.makeText(getApplicationContext(), "Ingresar marca", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(linea)) {
            Toast.makeText(getApplicationContext(), "Ingresar linea", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(modelo)) {
            Toast.makeText(getApplicationContext(), "Ingresar modelo", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(color)) {
            Toast.makeText(getApplicationContext(), "Ingresar color", Toast.LENGTH_SHORT).show();
        }else if (!TextUtils.isEmpty(placa) && !TextUtils.isEmpty(marca) && !TextUtils.isEmpty(linea) && !TextUtils.isEmpty(modelo) && !TextUtils.isEmpty(color)) {
            //Creacion de un nuevo cliente mediante los parametros obtenidos
            Automovil automovil = new Automovil(placa,marca,linea,modelo,color,urlimagen);
            //nombreReferenciaFirebase.nodoHijo.nodoHijo.setValue(Valor)
            //Esto se guarda en la base de datos es decir decimos que en la referencia en firebase
            //Guarde en cliente un hijo llamado nombre con el valor de el cliente que estamos creando
            firebase.child("Automovil").child(recibonombre).child(placa).setValue(automovil);
            Toast.makeText(getApplicationContext(),"Automovil agregado correctamente",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ActualizarAutomovilActivity.this, VisualizarAutomovilesActivity.class);
            intent.putExtra("nombrecliente",recibonombre);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                String placa = edt_placa_actualizar.getText().toString();
                String marca = edt_marca_actualizar.getText().toString();
                String linea = edt_linea_actualizar.getText().toString();
                String modelo = edt_modelo_actualizar.getText().toString();
                String color = edt_color_actualizar.getText().toString();

                if (TextUtils.isEmpty(placa)) {
                    Toast.makeText(getApplicationContext(), "Ingresar placa", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(marca)) {
                    Toast.makeText(getApplicationContext(), "Ingresar marca", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(linea)) {
                    Toast.makeText(getApplicationContext(), "Ingresar linea", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(modelo)) {
                    Toast.makeText(getApplicationContext(), "Ingresar modelo", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(color)) {
                    Toast.makeText(getApplicationContext(), "Ingresar color", Toast.LENGTH_SHORT).show();
                }else if (!TextUtils.isEmpty(placa) && !TextUtils.isEmpty(marca) && !TextUtils.isEmpty(linea) && !TextUtils.isEmpty(modelo) && !TextUtils.isEmpty(color)) {
                    //Creacion de un nuevo cliente mediante los parametros obtenidos
                    Automovil automovil = new Automovil(placa,marca,linea,modelo,color,urlimagen);
                    //nombreReferenciaFirebase.nodoHijo.nodoHijo.setValue(Valor)
                    //Esto se guarda en la base de datos es decir decimos que en la referencia en firebase
                    //Guarde en cliente un hijo llamado nombre con el valor de el cliente que estamos creando
                    firebase.child("Automovil").child(recibonombre).child(placa).setValue(automovil);
                    Intent intent = new Intent(ActualizarAutomovilActivity.this, VisualizarAutomovilesActivity.class);
                    intent.putExtra("nombrecliente",recibonombre);
                    startActivity(intent);
                    finish();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
