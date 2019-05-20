package com.serviciomecanico.serviciomecanico.Sesion;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.serviciomecanico.serviciomecanico.Conexion.Conexion;
import com.serviciomecanico.serviciomecanico.MenuPrincipalActivity;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Visualizar.VisualizarClientesActivity;

public class IniciarSeionActivity extends AppCompatActivity {

    Conexion conexion = new Conexion();
    FirebaseAuth auth;
    EditText edt_correo_iniciar_sesion, edt_contrasena_iniciar_sesion;
    ProgressBar progressBar_iniciar_sesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        auth = conexion.autenticacion();

        edt_correo_iniciar_sesion = findViewById(R.id.edt_correo_iniciar_sesion);
        edt_contrasena_iniciar_sesion = findViewById(R.id.edt_contrasena_iniciar_sesion);
        progressBar_iniciar_sesion = findViewById(R.id.progressBar_iniciar_sesion);

        progressBar_iniciar_sesion.setVisibility(View.INVISIBLE);
    }


    public void btn_iniciar_sesion (View view){
        String correo = edt_correo_iniciar_sesion.getText().toString();
        String contrasena = edt_contrasena_iniciar_sesion.getText().toString();
        progressBar_iniciar_sesion.setVisibility(View.VISIBLE);
        progressBar_iniciar_sesion.setProgress(50);
        progressBar_iniciar_sesion.setMax(100);

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(getApplicationContext(), "Ingresa un correo", Toast.LENGTH_SHORT).show();
            progressBar_iniciar_sesion.setVisibility(View.INVISIBLE);
        }else if (TextUtils.isEmpty(contrasena)) {
            Toast.makeText(getApplicationContext(), "Ingresa un contraseña", Toast.LENGTH_SHORT).show();
            progressBar_iniciar_sesion.setVisibility(View.INVISIBLE);
        }else if (!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contrasena))
           auth.signInWithEmailAndPassword(correo,contrasena)
                   .addOnCompleteListener(IniciarSeionActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                                progressBar_iniciar_sesion.setVisibility(View.INVISIBLE);
                            }else {
                                Intent intent = new Intent(IniciarSeionActivity.this, MenuPrincipalActivity.class);
                                startActivity(intent);
                                progressBar_iniciar_sesion.setVisibility(View.INVISIBLE);
                            }
                       }
                   });
    }
}
