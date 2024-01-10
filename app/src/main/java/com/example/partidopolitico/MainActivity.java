package com.example.partidopolitico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        Button botonLogin = findViewById(R.id.button);
        Button botonRegistrer = findViewById(R.id.buttonRegistrer);


        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correoEsperado = "espana@gmail.com";
                String contraseñaEsperada = "espana";

                String correoIngresado = editTextEmail.getText().toString().trim(); //EL TRIM ES PARA OMITIR LINEAS EN BLANCO
                String contraseñaIngresada = editTextPassword.getText().toString().trim();


                if (correoIngresado.equals(correoEsperado) && contraseñaIngresada.equals(contraseñaEsperada)){
                    Toast.makeText(getApplicationContext(), "Acceso Permitido", Toast.LENGTH_SHORT).show();

                    // Crear un Intent para abrir la nueva actividad (PaginaPrincipalActivity) en este caso
                    Intent intent = new Intent(MainActivity.this, PaginaPrincipalActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Acceso Denegado", Toast.LENGTH_SHORT).show();
                }
            }
        });


        botonRegistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRegistrerActivity = new Intent(MainActivity.this, RegistrerActivity.class);
                startActivity(intentRegistrerActivity);


            }
        });





    }
}