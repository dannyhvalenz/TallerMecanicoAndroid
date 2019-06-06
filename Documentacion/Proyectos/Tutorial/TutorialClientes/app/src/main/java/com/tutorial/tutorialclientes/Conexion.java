package com.tutorial.tutorialclientes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Conexion {

    DatabaseReference databaseReference;

    public DatabaseReference conexion(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        return databaseReference;
    }
}
