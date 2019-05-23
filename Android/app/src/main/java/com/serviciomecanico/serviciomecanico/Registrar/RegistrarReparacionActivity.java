package com.serviciomecanico.serviciomecanico.Registrar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.serviciomecanico.serviciomecanico.Adaptadores.ImagenAutomovilAdapter;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.MenuPrincipalActivity;
import com.serviciomecanico.serviciomecanico.Modelo.Reparacion;
import com.serviciomecanico.serviciomecanico.R;

import java.util.ArrayList;

public class RegistrarReparacionActivity extends AppCompatActivity {

    //Creando variable de referencia para la base de datos (Firebase)
    DatabaseReference firebase;
    //Llamando a la clase conexion para la creacion de la referencia
    Conexion conexion = new Conexion();

    String urlimagenReparacion, placa, idReparacion, nombre;

    int contador=0;

    //Componentes que ocupamos para la vista (Layout)
    EditText edt_tipo_registrar, edt_descripcionFalla_registrar, edt_descripcionMantenimiento_registrar, edt_kilometraje_registrar, edt_costo_registrar;
    ArrayList<String> mImagesUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_reparacion);
        //Una vez se inicia la actividad se manda a llamar a firebase
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registrar reparacion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Inicializacion de los componentes que ocuparemos
        edt_tipo_registrar = findViewById(R.id.edt_tipo_registrar);
        edt_descripcionFalla_registrar = findViewById(R.id.edt_descripcionFalla_registrar);
        edt_descripcionMantenimiento_registrar = findViewById(R.id.edt_descripcionMantenimiento_registrar);
        edt_kilometraje_registrar = findViewById(R.id.edt_kilometraje_registrar);
        edt_costo_registrar = findViewById(R.id.edt_costo_registrar);

        placa = getIntent().getStringExtra("placa");
        nombre = getIntent().getStringExtra("nombre");

        getImages();
    }

    public void getImages(){
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarReparacion%2Freparacion1.png?alt=media&token=2ea8f460-c6b5-4e7e-ab98-6ab3f76c6fe3");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarReparacion%2Freparacion2.png?alt=media&token=91cf8c3c-5ba2-45c6-b932-d994b790c63b");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarReparacion%2Freparacion3.png?alt=media&token=88a2d3a6-074e-451e-bf29-de5e3ebfe91c");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarReparacion%2Freparacion4.png?alt=media&token=f656f56f-e475-4581-9c8b-7fde129ba2b9");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarReparacion%2Freparacion5.png?alt=media&token=3fbb1b52-efda-4b7b-9b4f-4ca64221faa0");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarReparacion%2Freparacion6.png?alt=media&token=5330b73c-fea7-4e93-b61f-196c4d82d112");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarReparacion%2Freparacion7.png?alt=media&token=c7229ca5-8f34-4c40-8613-a26662271dd5");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarReparacion%2Freparacion8.png?alt=media&token=dc99443f-a4c5-417c-86d4-71bfab47d5a1");
        initRecyclerView();
    }

    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.rcv_imagenes_reparacion);
        recyclerView.setLayoutManager(layoutManager);
        ImagenAutomovilAdapter adapter = new ImagenAutomovilAdapter(this, mImagesUrls);
        recyclerView.setAdapter(adapter);
    }

    //Metodo para agregar un nuevo cliente a firebase (btn_agregar_cliente)
    public void btn_agregar_reparacion(View view){

        firebase.child("Url").child("url").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                urlimagenReparacion = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Obtencion de lo que escribe el usuario en la activity
        //TODO falta la validacion de los campos
        String tipo = edt_tipo_registrar.getText().toString();
        String descripcionFalla = edt_descripcionFalla_registrar.getText().toString();
        String descripcionMantenimiento = edt_descripcionMantenimiento_registrar.getText().toString();
        String kilometraje = edt_kilometraje_registrar.getText().toString();
        String costo = edt_costo_registrar.getText().toString();
        idReparacion = kilometraje;

        if(contador==0){
            Toast.makeText(getApplicationContext(), "Precione de nuevo para confirmar", Toast.LENGTH_SHORT).show();
            contador=1;
        }else if (TextUtils.isEmpty(tipo)) {
            Toast.makeText(getApplicationContext(), "Ingresar placa", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(descripcionFalla)) {
            Toast.makeText(getApplicationContext(), "Ingresar marca", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(descripcionMantenimiento)) {
            Toast.makeText(getApplicationContext(), "Ingresar linea", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(kilometraje)) {
            Toast.makeText(getApplicationContext(), "Ingresar modelo", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(costo)) {
            Toast.makeText(getApplicationContext(), "Ingresar color", Toast.LENGTH_SHORT).show();
        }else if (!TextUtils.isEmpty(tipo) && !TextUtils.isEmpty(descripcionFalla) && !TextUtils.isEmpty(descripcionMantenimiento) && !TextUtils.isEmpty(kilometraje) && !TextUtils.isEmpty(costo)) {
            //Creacion de un nuevo cliente mediante los parametros obtenidos
            Reparacion reparacion = new Reparacion(tipo,descripcionFalla,descripcionMantenimiento,kilometraje,costo,urlimagenReparacion);
            //nombreReferenciaFirebase.nodoHijo.nodoHijo.setValue(Valor)
            //Esto se guarda en la base de datos es decir decimos que en la referencia en firebase
            //Guarde en cliente un hijo llamado nombre con el valor de el cliente que estamos creando
            firebase.child("Cliente").child(nombre).child("Automovil").child(placa).child("Reparacion").child(idReparacion).setValue(reparacion);
            Toast.makeText(getApplicationContext(),"Reparacion agregado correctamente",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                Intent intent = new Intent(RegistrarReparacionActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
