package com.serviciomecanico.serviciomecanico.Registrar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.serviciomecanico.serviciomecanico.Adaptadores.ImagenClienteAdapter;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.Mapas.MapsActivity;
import com.serviciomecanico.serviciomecanico.MenuPrincipalActivity;
import com.serviciomecanico.serviciomecanico.Modelo.Cliente;
import com.serviciomecanico.serviciomecanico.R;

import java.util.ArrayList;

public class RegistrarClienteActivity extends AppCompatActivity {

    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    //Creando variable de referencia para la base de datos (Firebase)
    DatabaseReference firebase;
    //Llamando a la clase conexion para la creacion de la referencia
    Conexion conexion = new Conexion();

    String urlimagen, latitud, longitud;

    //Componentes que ocupamos para la vista (Layout)
    EditText edt_nombre_registrar, edt_correo_registrar, edt_telefono_registrar;
    Button  edt_direccion_registrar;
    ArrayList<String> mImagesUrls = new ArrayList<>();

    private FusedLocationProviderClient mFusedLocationClient;

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
        Toast.makeText(getApplicationContext(),"Seleccione un avatar para el cliente",Toast.LENGTH_LONG).show();

        getImages();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        subirLatLogFirebase();
    }

    private void subirLatLogFirebase() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RegistrarClienteActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            return;
        }
    }

    public void getImages(){
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarCliente%2Fhipster.png?alt=media&token=7cfa1247-a55d-4532-9557-c5ddaaca3ca2");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarCliente%2Fwoman%20(1).png?alt=media&token=314f9684-6ca3-44b1-92b7-1931031fd9d4");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarCliente%2Fman%20(1).png?alt=media&token=6fa23afc-a711-4b19-a14b-6fcb96d08e49");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarCliente%2Fwoman%20(2).png?alt=media&token=14bb25de-0811-4e72-b096-cf095501a190");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarCliente%2Fman%20(2).png?alt=media&token=5b2b8f70-d2bd-4a40-8dbf-a990381a658d");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarCliente%2Fwoman%20(3).png?alt=media&token=8ae489b3-b7ab-443c-b121-ae5d12c5b847");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarCliente%2Fman.png?alt=media&token=ec4141fd-ff96-4946-8296-a7ab4176f588");
        mImagesUrls.add("https://firebasestorage.googleapis.com/v0/b/serviciomecanico-1998.appspot.com/o/AvatarCliente%2Fwoman.png?alt=media&token=49441e33-27f6-4edf-980a-63be2cf5e7dd");
        initRecyclerView();
    }

    public void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.rcv_imagenes_cliente);
        recyclerView.setLayoutManager(layoutManager);
        ImagenClienteAdapter adapter = new ImagenClienteAdapter(this, mImagesUrls);
        recyclerView.setAdapter(adapter);
    }

    //Metodo para agregar un nuevo cliente a firebase (btn_agregar_cliente)
    public void btn_agregar_cliente (View view){
        //Obtencion de lo que escribe el usuario en la activity
        //TODO falta la validacion de los campos
        String nombre = edt_nombre_registrar.getText().toString();
        String correo = edt_correo_registrar.getText().toString();
        String telefono = edt_telefono_registrar.getText().toString();

        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getApplicationContext(), "Ingresa el nombre", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(correo)) {
            Toast.makeText(getApplicationContext(), "Ingresa el correo electrónico", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(telefono)) {
            Toast.makeText(getApplicationContext(), "Ingresa el teléfono", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(longitud)) {
            Toast.makeText(getApplicationContext(), "Ingresa la dirección", Toast.LENGTH_SHORT).show();
        }else if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(correo) && !TextUtils.isEmpty(telefono)) {
            //Creacion de un nuevo cliente mediante los parametros obtenidos
            Cliente cliente = new Cliente(nombre,correo,telefono,urlimagen,latitud,longitud);
            //nombreReferenciaFirebase.nodoHijo.nodoHijo.setValue(Valor)
            //Esto se guarda en la base de datos es decir decimos que en la referencia en firebase
            //Guarde en cliente un hijo llamado nombre con el valor de el cliente que estamos creando
            firebase.child("Cliente").child(nombre).setValue(cliente);
            Toast.makeText(getApplicationContext(),"Cliente agregado correctamente",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void direccioncliente(View view){
        Intent intent = new Intent(RegistrarClienteActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        firebase.child("Url").child("url").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                urlimagen = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        firebase.child("Posicion").child("latitude").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                latitud = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        firebase.child("Posicion").child("longitude").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                longitud = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                Intent intent = new Intent(RegistrarClienteActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
