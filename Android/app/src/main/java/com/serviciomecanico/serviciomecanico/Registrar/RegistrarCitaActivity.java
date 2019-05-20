package com.serviciomecanico.serviciomecanico.Registrar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.serviciomecanico.serviciomecanico.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrarCitaActivity extends AppCompatActivity {

    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cita);

        calendar = findViewById(R.id.calendarView2);


    }

    public void btn_registrar_cita(View view){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selectedDate = sdf.format(new Date(calendar.getDate()));
        Toast.makeText(getApplicationContext(),selectedDate,Toast.LENGTH_SHORT).show();
    }
}
