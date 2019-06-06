package com.tutorial.tutorialclientes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;

public class ActualizarCliente extends AppCompatActivity {

    Conexion conexion = new Conexion();
    DatabaseReference firebase;

    TextView edt_nombre_actualizar;
    EditText edt_correo_actualizar, edt_telefono_actualizar, edt_direccion_actualizar;
    String recibonombre;

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
        edt_direccion_actualizar = findViewById(R.id.edt_direccion_actualizar);
        edt_telefono_actualizar = findViewById(R.id.edt_telefono_actualizar);

        recibonombre = getIntent().getStringExtra("nombre");
        Toast.makeText(getApplicationContext(), recibonombre, Toast.LENGTH_SHORT).show();

        edt_nombre_actualizar.setText(getIntent().getStringExtra("nombre"));
        edt_correo_actualizar.setText(getIntent().getStringExtra("correo"));
        edt_telefono_actualizar.setText(getIntent().getStringExtra("telefono"));
        edt_direccion_actualizar.setText(getIntent().getStringExtra("direccion"));
    }

    public void btn_actualizar_cliente(View view) {
        String correo = edt_correo_actualizar.getText().toString();
        String telefono = edt_telefono_actualizar.getText().toString();
        String direccion = edt_direccion_actualizar.getText().toString();

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(getApplicationContext(), "Ingresar correo", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(telefono)) {
            Toast.makeText(getApplicationContext(), "Ingresar telefono", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(direccion)) {
            Toast.makeText(getApplicationContext(), "Ingresar direccion", Toast.LENGTH_SHORT).show();
        }else if (!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(telefono) && !TextUtils.isEmpty(direccion)) {
                firebase.child("Cliente").child(recibonombre).child("correo").setValue(correo);
                firebase.child("Cliente").child(recibonombre).child("telefono").setValue(telefono);
                firebase.child("Cliente").child(recibonombre).child("direccion").setValue(direccion);
                Toast.makeText(getApplicationContext(), "Cliente acutalizado correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActualizarCliente.this, VisualizarClientes.class);
                startActivity(intent);
                finish();
            }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                String nombre = edt_nombre_actualizar.getText().toString();
                String correo = edt_correo_actualizar.getText().toString();
                String telefono = edt_telefono_actualizar.getText().toString();
                String direccion = edt_direccion_actualizar.getText().toString();

                if (TextUtils.isEmpty(correo)) {
                    Toast.makeText(getApplicationContext(), "Ingresar correo", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(telefono)) {
                    Toast.makeText(getApplicationContext(), "Ingresar telefono", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(direccion)) {
                    Toast.makeText(getApplicationContext(), "Ingresar direccion", Toast.LENGTH_SHORT).show();
                } else if (!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(telefono) && !TextUtils.isEmpty(direccion)) {
                    firebase.child("Cliente").child(recibonombre).child("correo").setValue(correo);
                    firebase.child("Cliente").child(recibonombre).child("telefono").setValue(telefono);
                    firebase.child("Cliente").child(recibonombre).child("direccion").setValue(telefono);
                    Toast.makeText(getApplicationContext(), "Cliente acutalizado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActualizarCliente.this, VisualizarClientes.class);
                    startActivity(intent);
                    finish();
                }

                Intent intent = new Intent(ActualizarCliente.this, VisualizarClientes.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
