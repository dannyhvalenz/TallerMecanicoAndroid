package com.serviciomecanico.serviciomecanico.Conexion;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Conexion {

    DatabaseReference databaseReference;
    FirebaseAuth auth;

    public DatabaseReference conexion(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        return databaseReference;
    }

    public FirebaseAuth autenticacion(){
        auth = FirebaseAuth.getInstance();
        return auth;
    }
}
