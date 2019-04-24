package com.serviciomecanico.serviciomecanico.Registrar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.serviciomecanico.serviciomecanico.R;
import com.serviciomecanico.serviciomecanico.Sesion.IniciarSeionActivity;

public class RegisterAdministradorActivity extends AppCompatActivity {

    EditText edt_register_correo, edt_register_contrasena;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_administrador);
        auth = FirebaseAuth.getInstance();

        edt_register_correo = findViewById(R.id.edt_register_correo);
        edt_register_contrasena = findViewById(R.id.edt_register_contrasena);

    }

    public void btn_registrar_administrador(View view){
        String correo = edt_register_correo.getText().toString();
        String contrasena = edt_register_contrasena.getText().toString();

        if (TextUtils.isEmpty(correo))
            Toast.makeText(getApplicationContext(),"Ingresa un correo",Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(contrasena))
            Toast.makeText(getApplicationContext(),"Ingresa un contraseña",Toast.LENGTH_SHORT).show();
        if (!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contrasena))
            auth.createUserWithEmailAndPassword(correo,contrasena)
                    .addOnCompleteListener(RegisterAdministradorActivity.this, new OnCompleteListener<AuthResult>() {
                        //Lo que pasara a la hora de enviar el usuario a firebase
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Se tuvo un error al guardar el administrador", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getApplicationContext(),"Se ha creado correctamente el administrador",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterAdministradorActivity.this, IniciarSeionActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
    }
}
