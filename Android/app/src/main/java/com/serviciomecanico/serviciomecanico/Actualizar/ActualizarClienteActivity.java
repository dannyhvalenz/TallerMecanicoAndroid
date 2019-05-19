package com.serviciomecanico.serviciomecanico.Actualizar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.serviciomecanico.serviciomecanico.Modelo.Cliente;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Sesion.IniciarSeionActivity;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarClientesActivity;

public class ActualizarClienteActivity extends AppCompatActivity {

    Conexion conexion = new Conexion();
    DatabaseReference firebase;

    EditText edt_nombre_actualizar, edt_correo_actualizar, edt_telefono_actualizar;
    String recibonombre, urlimagen, latitud, longitud;
    ImageView img_avatar_actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cliente);
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Actualizar cliente");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edt_nombre_actualizar = findViewById(R.id.edt_nombre_actualizar);
        edt_correo_actualizar = findViewById(R.id.edt_correo_actualizar);
        edt_telefono_actualizar = findViewById(R.id.edt_telefono_actualizar);
        img_avatar_actualizar = findViewById(R.id.img_avatar_actualizar);

        recibonombre = getIntent().getStringExtra("nombre");
        Toast.makeText(getApplicationContext(),recibonombre,Toast.LENGTH_SHORT).show();

        edt_nombre_actualizar.setText(getIntent().getStringExtra("nombre"));
        edt_correo_actualizar.setText(getIntent().getStringExtra("correo"));
        edt_telefono_actualizar.setText(getIntent().getStringExtra("telefono"));

        urlimagen = getIntent().getStringExtra("urlimagen");
        latitud = getIntent().getStringExtra("latitud");
        longitud = getIntent().getStringExtra("longitud");

        Glide.with(getApplicationContext())
                .load(urlimagen)
                .into(img_avatar_actualizar);

        //firebase.child("Cliente").child(recibonombre).removeValue();
    }

    public void btn_actualizar_cliente(View view){
        String correo = edt_correo_actualizar.getText().toString();
        String telefono = edt_telefono_actualizar.getText().toString();

         if (TextUtils.isEmpty(correo)) {
            Toast.makeText(getApplicationContext(), "Ingresar correo", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(telefono)) {
            Toast.makeText(getApplicationContext(), "Ingresar telefono", Toast.LENGTH_SHORT).show();
        }else if (!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(telefono)) {
            firebase.child("Cliente").child(recibonombre).child("correo").setValue(correo);
            firebase.child("Cliente").child(recibonombre).child("telefono").setValue(telefono);
            Toast.makeText(getApplicationContext(), "Cliente acutalizado correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ActualizarClienteActivity.this, VisualizarClientesActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                String nombre = edt_nombre_actualizar.getText().toString();
                String correo = edt_correo_actualizar.getText().toString();
                String telefono = edt_telefono_actualizar.getText().toString();

                if (TextUtils.isEmpty(nombre)) {
                    Toast.makeText(getApplicationContext(), "Ingresar nombre", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(correo)) {
                    Toast.makeText(getApplicationContext(), "Ingresar correo", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(telefono)) {
                    Toast.makeText(getApplicationContext(), "Ingresar telefono", Toast.LENGTH_SHORT).show();
                }else if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(correo) && !TextUtils.isEmpty(telefono)) {
                    Cliente cliente = new Cliente(nombre, correo, telefono, urlimagen, latitud, longitud);
                    firebase.child("Cliente").child(nombre).setValue(cliente);
                    Intent intent = new Intent(ActualizarClienteActivity.this, VisualizarClientesActivity.class);
                    startActivity(intent);
                    finish();
                }

                Intent intent = new Intent(ActualizarClienteActivity.this, VisualizarClientesActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
