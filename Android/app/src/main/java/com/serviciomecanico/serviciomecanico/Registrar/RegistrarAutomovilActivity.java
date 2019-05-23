package com.serviciomecanico.serviciomecanico.Registrar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.serviciomecanico.serviciomecanico.Modelo.Automovil;
import com.serviciomecanico.serviciomecanico.R;

import java.util.ArrayList;

public class RegistrarAutomovilActivity extends AppCompatActivity {

    //Creando variable de referencia para la base de datos (Firebase)
    DatabaseReference firebase;
    //Llamando a la clase conexion para la creacion de la referencia
    Conexion conexion = new Conexion();

    String urlimagenAutomovil;
    String nombrecliente;

    int contador=0;

    //Componentes que ocupamos para la vista (Layout)
    EditText edt_placa_registrar, edt_marca_registrar, edt_linea_registrar, edt_modelo_registrar, edt_color_registrar;
    ArrayList<String> mImagesUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_automovil);
        //Una vez se inicia la actividad se manda a llamar a firebase
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registrar automovil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Inicializacion de los componentes que ocuparemos
        edt_placa_registrar = findViewById(R.id.edt_placa_registrar);
        edt_marca_registrar = findViewById(R.id.edt_marca_registrar);
        edt_linea_registrar = findViewById(R.id.edt_linea_registrar);
        edt_modelo_registrar = findViewById(R.id.edt_modelo_registrar);
        edt_color_registrar = findViewById(R.id.edt_color_registrar);

        nombrecliente = getIntent().getStringExtra("nombrecliente");

        getImages();
    }

    public void getImages(){
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarAutomovil%2Fautomovil1.png?alt=media&token=c64c8a48-4e81-47c8-99e5-c27265151b51");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarAutomovil%2Fautomovil8.png?alt=media&token=447b004d-caca-45fa-abd3-bcc65bc5e56b");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarAutomovil%2Fautomovil2.png?alt=media&token=80008190-d1ec-4a5c-b60d-f762da6452b7");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarAutomovil%2Fautomovil7.png?alt=media&token=0501ec9b-e507-4fb5-9b66-314b6159ce0d");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarAutomovil%2Fautomovil3.png?alt=media&token=dbac0685-47d4-425f-9ba3-9c133883eb59");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarAutomovil%2Fautomovil6.png?alt=media&token=42760f65-8cd4-446e-8814-cfac6c8ba178");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarAutomovil%2Fautomovil4.png?alt=media&token=fcdf85ee-7567-471e-aac2-8f56cfd4b227");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarAutomovil%2Fautomovil5.png?alt=media&token=a2eb5983-f260-4db9-97ce-bc66f7fe265a");
        initRecyclerView();
    }

    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.rcv_imagenes_automovil);
        recyclerView.setLayoutManager(layoutManager);
        ImagenAutomovilAdapter adapter = new ImagenAutomovilAdapter(this, mImagesUrls);
        recyclerView.setAdapter(adapter);
    }

    //Metodo para agregar un nuevo cliente a firebase (btn_agregar_cliente)
    public void btn_agregar_automovil(View view){

        firebase.child("Url").child("url").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                urlimagenAutomovil = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Obtencion de lo que escribe el usuario en la activity
        //TODO falta la validacion de los campos
        String placa = edt_placa_registrar.getText().toString();
        String marca = edt_marca_registrar.getText().toString();
        String linea = edt_linea_registrar.getText().toString();
        String modelo = edt_modelo_registrar.getText().toString();
        String color = edt_color_registrar.getText().toString();

        if(contador==0){
            Toast.makeText(getApplicationContext(), "Precione de nuevo para confirmar", Toast.LENGTH_SHORT).show();
            contador=1;
        }else if (TextUtils.isEmpty(placa)) {
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
            Automovil automovil = new Automovil(placa,marca,linea,modelo,color,urlimagenAutomovil);
            //nombreReferenciaFirebase.nodoHijo.nodoHijo.setValue(Valor)
            //Esto se guarda en la base de datos es decir decimos que en la referencia en firebase
            //Guarde en cliente un hijo llamado nombre con el valor de el cliente que estamos creando
            firebase.child("Cliente").child(nombrecliente).child("Automovil").child(placa).setValue(automovil);
            Toast.makeText(getApplicationContext(),"Automovil agregado correctamente",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                Intent intent = new Intent(RegistrarAutomovilActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
