package com.example.partidopolitico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrerActivity extends AppCompatActivity {

    static Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);


        EditText correo = findViewById(R.id.editTextCorreoRegistrer);
        EditText contrasena = findViewById(R.id.editTextContraseñaRegistrer);
        Button botonRegistrarse = findViewById(R.id.buttonrRegistrarse);


        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MongoClient mongoClient = new MongoClient("localhost", 27017);
                MongoCollection<Document> collection = mongoClient.getDatabase("my_database").getCollection("usuarios");

                Document document = new Document();
                document.put("correo", correo.getText().toString());
                document.put("contrasena", contrasena.getText().toString());

                collection.insertOne(document);

                System.out.println("Los datos se han insertado correctamente ");
                Intent intent = new Intent(RegistrerActivity.this, MainActivity.class);
                startActivity(intent);

                mongoClient.close();
                
            }

        });


        }
}







