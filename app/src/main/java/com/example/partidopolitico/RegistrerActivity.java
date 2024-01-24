package com.example.partidopolitico;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;


public class RegistrerActivity extends AppCompatActivity {

    static Connection connection;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);


        EditText correo = findViewById(R.id.editTextCorreoRegistrer);
        EditText contrasena = findViewById(R.id.editTextContraseñaRegistrer);
        Button botonRegistrarse = findViewById(R.id.buttonrRegistrarse);
        db= new DatabaseHelper(this);


        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    db.insertarDatos(correo.getText().toString(), contrasena.getText().toString());

                    Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrerActivity.this, MainActivity.class);
                    startActivity(intent);




                
            }

        });


        }
}







