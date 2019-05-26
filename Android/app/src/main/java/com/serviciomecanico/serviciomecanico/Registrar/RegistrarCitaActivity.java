package com.serviciomecanico.serviciomecanico.Registrar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.MenuPrincipalActivity;
import com.serviciomecanico.serviciomecanico.Modelo.Cita;
import com.serviciomecanico.serviciomecanico.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegistrarCitaActivity extends AppCompatActivity {

    CalendarView calendar;
    EditText edt_nombre_cita,edt_hora,edt_fecha,edt_descripcion_cita;
    private int dia,mes,ano,hora,minutos;
    DatabaseReference firebase;
    Conexion conexion = new Conexion();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cita);
        firebase = conexion.conexion();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registrar cita");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edt_nombre_cita = findViewById(R.id.edt_nombre_cita);
        edt_hora = findViewById(R.id.edt_hora);
        edt_fecha = findViewById(R.id.edt_fecha);
        edt_descripcion_cita = findViewById(R.id.edt_descripcion_cita);


    }

    public void btn_fecha(View view){
        final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        ano = c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edt_fecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        }
        ,dia,mes,ano);
        datePickerDialog.show();
    }

    public void btn_hora(View view){
        final Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minutos = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                edt_hora.setText(hourOfDay+":"+minute);
            }
        },hora,minutos,false);
        timePickerDialog.show();
    }

    public void btn_registrar_cita(View view){
        String hora = edt_hora.getText().toString();
        String fecha = edt_fecha.getText().toString();
        String descripcion = edt_descripcion_cita.getText().toString();
        String nombre = edt_nombre_cita.getText().toString();
        String id = nombre;

        if (TextUtils.isEmpty(hora)) {
            Toast.makeText(getApplicationContext(), "Ingresa la hora", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(fecha)) {
            Toast.makeText(getApplicationContext(), "Ingresa la fecha", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(descripcion)) {
            Toast.makeText(getApplicationContext(), "Ingresa una descripción", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getApplicationContext(), "Ingresa nombre del cliente", Toast.LENGTH_SHORT).show();
        }else if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(descripcion) && !TextUtils.isEmpty(fecha) && !TextUtils.isEmpty(hora)) {
            Cita cita = new Cita(hora,fecha,nombre,descripcion);
            firebase.child("Cita").child(id).setValue(cita);
            Toast.makeText(getApplicationContext(),"Cita agregada correctamente",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                Intent intent = new Intent(RegistrarCitaActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
