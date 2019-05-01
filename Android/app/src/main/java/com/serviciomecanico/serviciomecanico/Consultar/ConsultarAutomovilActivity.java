package com.serviciomecanico.serviciomecanico.Consultar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Actualizar.ActualizarAutomovilActivity;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Registrar.RegistrarReparacionActivity;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarAutomovilesActivity;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarReparacionesActivity;

public class ConsultarAutomovilActivity extends AppCompatActivity {

    Conexion  conexion = new Conexion();
    DatabaseReference firebase;
    EditText edt_placa_consultar, edt_marca_consultar, edt_linea_consultar,edt_modelo_consultar,edt_color_consultar;
    String placa, marca, linea, modelo, color, urlimagen, nombre;
    ImageView img_avatar_consultar_automovil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_automovil);
        firebase = conexion.conexion();

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Automovil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edt_placa_consultar = findViewById(R.id.edt_placa_consultar);
        edt_marca_consultar = findViewById(R.id.edt_marca_consultar);
        edt_linea_consultar = findViewById(R.id.edt_linea_consultar);
        edt_modelo_consultar = findViewById(R.id.edt_modelo_consultar);
        edt_color_consultar = findViewById(R.id.edt_color_consultar);
        img_avatar_consultar_automovil = findViewById(R.id.img_avatar_consultar_automovil);

        //Obtener variables del cardView

        placa = getIntent().getStringExtra("placa");
        marca = getIntent().getStringExtra("marca");
        linea = getIntent().getStringExtra("linea");
        modelo = getIntent().getStringExtra("modelo");
        color = getIntent().getStringExtra("color");
        urlimagen = getIntent().getStringExtra("urlimagen");
        nombre = getIntent().getStringExtra("nombre");

        edt_placa_consultar.setText(getIntent().getStringExtra("placa"));
        edt_marca_consultar.setText(getIntent().getStringExtra("marca"));
        edt_linea_consultar.setText(getIntent().getStringExtra("linea"));
        edt_modelo_consultar.setText(getIntent().getStringExtra("modelo"));
        edt_color_consultar.setText(getIntent().getStringExtra("color"));

        Glide.with(getApplicationContext())
                .load(urlimagen)
                .into(img_avatar_consultar_automovil);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(ConsultarAutomovilActivity.this, VisualizarAutomovilesActivity.class);
                intent.putExtra("nombrecliente",nombre);
                startActivity(intent);
                finish();
                break;
            case R.id.action_delete:
                AlertDialog.Builder eliminar = new AlertDialog.Builder(this);
                eliminar.setMessage("¿Desea eliminar este automovil?");
                eliminar.setTitle("Eliminar automovil");
                eliminar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child("Automovil").child(nombre).child(placa).removeValue();
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
                Intent intent2 = new Intent(ConsultarAutomovilActivity.this, ActualizarAutomovilActivity.class);
                intent2.putExtra("nombre",nombre);
                intent2.putExtra("placa",placa);
                intent2.putExtra("marca",marca);
                intent2.putExtra("linea",linea);
                intent2.putExtra("modelo",modelo);
                intent2.putExtra("color",color);
                intent2.putExtra("urlimagen",urlimagen);
                startActivity(intent2);
                break;

            case R.id.action_repar:
                Intent intent3 = new Intent(ConsultarAutomovilActivity.this, VisualizarReparacionesActivity.class);
                intent3.putExtra("placa",placa);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consultar_automovil, menu);
        return true;
    }




}
