package com.tutorial.tutorialclientes;

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

public class RegistrarCliente extends AppCompatActivity {

    //Creando variable de referencia para la base de datos (Firebase)
    DatabaseReference firebase;
    //Llamando a la clase conexion para la creacion de la referencia
    Conexion conexion = new Conexion();

    EditText edt_nombre_registrar, edt_correo_registrar, edt_telefono_registrar, edt_direccion_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        //Una vez se inicia la actividad se manda a llamar a firebase
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registrar cliente");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Inicializacion de los componentes que ocuparemos
        edt_nombre_registrar = findViewById(R.id.edt_nombre_registrar);
        edt_correo_registrar = findViewById(R.id.edt_correo_registrar);
        edt_telefono_registrar = findViewById(R.id.edt_telefono_registrar);
        edt_direccion_registrar = findViewById(R.id.edt_direccion_registrar);
    }

    //Metodo para agregar un nuevo cliente a firebase (btn_agregar_cliente)
    public void btn_agregar_cliente (View view){
        //Obtencion de lo que escribe el usuario en la activity
        String nombre = edt_nombre_registrar.getText().toString();
        String correo = edt_correo_registrar.getText().toString();
        String telefono = edt_telefono_registrar.getText().toString();
        String direccion = edt_direccion_registrar.getText().toString();

        //Verificacion de cada uno de los campos
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getApplicationContext(), "Ingresa el nombre", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(correo)) {
            Toast.makeText(getApplicationContext(), "Ingresa el correo electrónico", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(telefono)) {
            Toast.makeText(getApplicationContext(), "Ingresa el teléfono", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(direccion)) {
            Toast.makeText(getApplicationContext(), "Ingresa la dirección", Toast.LENGTH_SHORT).show();
        }else if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(correo) && !TextUtils.isEmpty(telefono)) {
            //Creacion de un nuevo cliente mediante los parametros obtenidos
            Cliente cliente = new Cliente(nombre,correo,telefono,direccion);
            //nombreReferenciaFirebase.nodoHijo.nodoHijo.setValue(Valor)
            //Esto se guarda en la base de datos es decir decimos que en la referencia en firebase
            //Guarde en cliente un hijo llamado nombre con el valor de el cliente que estamos creando
            firebase.child("Cliente").child(nombre).setValue(cliente);
            Toast.makeText(getApplicationContext(),"Cliente agregado correctamente",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //Boton de regreso en el toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(RegistrarCliente.this, VisualizarClientes.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
