
package com.example.partidopolitico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaginaPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);

        Button botonIntroduccion = findViewById(R.id.botonIntroduccion);
        Button botonPoliticos = findViewById(R.id.botonPoliticos);
        Button botonPropuestaElectoral = findViewById(R.id.botonPropuesta);
        Button botonSistemaOperativo = findViewById(R.id.botonSO);


        // Accion boton introduccion
        botonIntroduccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaPrincipalActivity.this, IntroduccionActivity.class);
                startActivity(intent);
            }
        });

        // Accion boton politicos
        botonPoliticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaPrincipalActivity.this, PoliticosActivity.class);
                startActivity(intent);
            }
        });

        // Accion boton propuesta electoral
        botonPropuestaElectoral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaPrincipalActivity.this, PropuestaActivity.class);
                startActivity(intent);
            }
        });

        // Accuib boton sistema operativo
        botonSistemaOperativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaPrincipalActivity.this, SistemaOperativo.class);
                startActivity(intent);
            }
        });
    }
}