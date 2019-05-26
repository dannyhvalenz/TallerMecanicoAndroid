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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.Modelo.Automovil;
import com.serviciomecanico.serviciomecanico.Modelo.Reparacion;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarAutomovilesActivity;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarReparacionesActivity;

public class ActualizarReparacionActivity extends AppCompatActivity {

    Conexion conexion = new Conexion();
    DatabaseReference firebase;

    EditText edt_tipo_actualizar, edt_descripcionFalla_actualizar, edt_descripcionoMantenimiento_actualizar,edt_costo_actualizar;
    TextView edt_kilometraje_actualizar;
    String urlimagen, placaAntigua, idReparacion, nombre;
    ImageView img_avatar_actualizar_reparacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_reparacion);
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Actualizar reparación");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edt_tipo_actualizar = findViewById(R.id.edt_tipo_actualizar);
        edt_descripcionFalla_actualizar = findViewById(R.id.edt_descripcionFalla_actualizar);
        edt_descripcionoMantenimiento_actualizar = findViewById(R.id.edt_descripcionMantenimiento_actualizar);
        edt_kilometraje_actualizar = findViewById(R.id.edt_kilometraje_actualizar);
        edt_costo_actualizar = findViewById(R.id.edt_costo_actualizar);
        img_avatar_actualizar_reparacion = findViewById(R.id.img_avatar_actualizar_reparacion);

        nombre = getIntent().getStringExtra("nombre");
        placaAntigua = getIntent().getStringExtra("placa");
        idReparacion = getIntent().getStringExtra("idReparacion");

        edt_tipo_actualizar.setText(getIntent().getStringExtra("tipo"));
        edt_descripcionFalla_actualizar.setText(getIntent().getStringExtra("descripcionFalla"));
        edt_descripcionoMantenimiento_actualizar.setText(getIntent().getStringExtra("descripcionMantenimiento"));
        edt_kilometraje_actualizar.setText(getIntent().getStringExtra("kilometraje"));
        edt_costo_actualizar.setText(getIntent().getStringExtra("costo"));
        urlimagen = getIntent().getStringExtra("urlimagen");

        Glide.with(getApplicationContext())
                .load(urlimagen)
                .into(img_avatar_actualizar_reparacion);
    }

    public void btn_actualizar_reparacion(View view){
        String tipo = edt_tipo_actualizar.getText().toString();
        String descripcionFalla = edt_descripcionFalla_actualizar.getText().toString();
        String descripcionMantenimiento = edt_descripcionoMantenimiento_actualizar.getText().toString();
        String kilometraje = edt_kilometraje_actualizar.getText().toString();
        String costo = edt_costo_actualizar.getText().toString();

        if (TextUtils.isEmpty(tipo)) {
            Toast.makeText(getApplicationContext(), "Ingresar tipo", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(descripcionFalla)) {
            Toast.makeText(getApplicationContext(), "Ingresar descripcion falla", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(descripcionMantenimiento)) {
            Toast.makeText(getApplicationContext(), "Ingresar descripcion mantenimiento", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(kilometraje)) {
            Toast.makeText(getApplicationContext(), "Ingresar kilometraje", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(costo)) {
            Toast.makeText(getApplicationContext(), "Ingresar costo", Toast.LENGTH_SHORT).show();
        }else if (!TextUtils.isEmpty(tipo) && !TextUtils.isEmpty(descripcionFalla) && !TextUtils.isEmpty(descripcionMantenimiento) && !TextUtils.isEmpty(kilometraje) && !TextUtils.isEmpty(costo)) {
            //Creacion de un nuevo cliente mediante los parametros obtenidos
            Reparacion reparacion = new Reparacion(tipo,descripcionFalla,descripcionMantenimiento,kilometraje,costo,urlimagen);
            //nombreReferenciaFirebase.nodoHijo.nodoHijo.setValue(Valor)
            //Esto se guarda en la base de datos es decir decimos que en la referencia en firebase
            //Guarde en cliente un hijo llamado nombre con el valor de el cliente que estamos creando
            firebase.child("Cliente").child(nombre).child("Automovil").child(placaAntigua).child("Reparacion").child(idReparacion).child("costo").setValue(costo);
            firebase.child("Cliente").child(nombre).child("Automovil").child(placaAntigua).child("Reparacion").child(idReparacion).child("descripcionFalla").setValue(descripcionFalla);
            firebase.child("Cliente").child(nombre).child("Automovil").child(placaAntigua).child("Reparacion").child(idReparacion).child("descripcionMantenimiento").setValue(descripcionMantenimiento);
            firebase.child("Cliente").child(nombre).child("Automovil").child(placaAntigua).child("Reparacion").child(idReparacion).child("kilometraje").setValue(kilometraje);
            firebase.child("Cliente").child(nombre).child("Automovil").child(placaAntigua).child("Reparacion").child(idReparacion).child("tipo").setValue(tipo);
            Toast.makeText(getApplicationContext(),"Automovil agregado correctamente",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ActualizarReparacionActivity.this, VisualizarReparacionesActivity.class);
            intent.putExtra("placa",placaAntigua);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                    finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
