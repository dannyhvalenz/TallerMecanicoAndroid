package com.serviciomecanico.serviciomecanico.Consultar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Actualizar.ActualizarAutomovilActivity;
import com.serviciomecanico.serviciomecanico.Actualizar.ActualizarReparacionActivity;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarReparacionActivity;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarAutomovilesActivity;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarReparacionesActivity;

public class ConsultarReparacionActivity extends AppCompatActivity {

    Conexion  conexion = new Conexion();
    DatabaseReference firebase;
    TextView edt_tipo_consultar, edt_descripcionFalla_consultar, edt_descripcionMantenimiento_consultar,edt_kilometraje_consultar,edt_costo_consultar;
    String tipo, descripcionFalla, descripcionMantenimiento, kilometraje, costo, urlimagen, idReparacion, placa, nombre;
    ImageView img_avatar_consultar_reparacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_reparacion);
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reparación");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edt_tipo_consultar = findViewById(R.id.edt_tipo_consultar);
        edt_descripcionFalla_consultar = findViewById(R.id.edt_descripcionFalla_consultar);
        edt_descripcionMantenimiento_consultar = findViewById(R.id.edt_descripcionMantenimiento_consultar);
        edt_kilometraje_consultar = findViewById(R.id.edt_kilometraje_consultar);
        edt_costo_consultar = findViewById(R.id.edt_costo_consultar);
        img_avatar_consultar_reparacion = findViewById(R.id.img_avatar_consultar_reparacion);

        //Obtener variables del cardView

        tipo = getIntent().getStringExtra("tipo");
        descripcionFalla = getIntent().getStringExtra("descripcionFalla");
        descripcionMantenimiento = getIntent().getStringExtra("descripcionMantenimiento");
        kilometraje = getIntent().getStringExtra("kilometraje");
        costo = getIntent().getStringExtra("costo");
        urlimagen = getIntent().getStringExtra("urlimagen");

        nombre = getIntent().getStringExtra("nombre");
        placa = getIntent().getStringExtra("placa");
        idReparacion = getIntent().getStringExtra("idReparacion");

        edt_tipo_consultar.setText(getIntent().getStringExtra("tipo"));
        edt_descripcionFalla_consultar.setText(getIntent().getStringExtra("descripcionFalla"));
        edt_descripcionMantenimiento_consultar.setText(getIntent().getStringExtra("descripcionMantenimiento"));
        edt_kilometraje_consultar.setText(getIntent().getStringExtra("kilometraje"));
        edt_costo_consultar.setText(getIntent().getStringExtra("costo"));

        Glide.with(getApplicationContext())
                .load(urlimagen)
                .into(img_avatar_consultar_reparacion);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_delete:
                AlertDialog.Builder eliminar = new AlertDialog.Builder(this);
                eliminar.setMessage("¿Desea eliminar esta reparación?");
                eliminar.setTitle("Eliminar reparación");
                eliminar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child("Cliente").child(nombre).child("Automovil").child(placa).child("Reparacion").child(idReparacion).removeValue();
                        finish();
                    }
                });

                eliminar.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = eliminar.create();
                dialog.show();
                break;
            case R.id.action_edit:
                Intent intent2 = new Intent(ConsultarReparacionActivity.this, ActualizarReparacionActivity.class);
                intent2.putExtra("tipo",tipo);
                intent2.putExtra("descripcionFalla",descripcionFalla);
                intent2.putExtra("descripcionMantenimiento",descripcionMantenimiento);
                intent2.putExtra("kilometraje",kilometraje);
                intent2.putExtra("costo",costo);
                intent2.putExtra("placa",placa);
                intent2.putExtra("idReparacion",idReparacion);
                intent2.putExtra("nombre",nombre);
                intent2.putExtra("urlimagen",urlimagen);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consultar_reparacion, menu);
        return true;
    }




}
