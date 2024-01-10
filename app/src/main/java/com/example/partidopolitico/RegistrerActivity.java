package com.example.partidopolitico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrerActivity extends AppCompatActivity {

    static Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);


        Button botonRegistrarse = findViewById(R.id.buttonrRegistrarse);


        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText editTextCorreo = findViewById(R.id.editTextCorreoRegistrer);
                EditText editTextContrasena = findViewById(R.id.editTextContraseñaRegistrer);




                // CUANDO SE PULSE EL BOTON DE REGISTRO SUCEDERAN UNA SERIE DE SUCESOS

                // 1º - Comprobara el correo y contraseña para meterlo en la base de datos
                    /* Si se ha metido con exito pasara lo siguiente */


                // Obtener los datos del usuario
                String correo = editTextCorreo.getText().toString().trim();
                String contrasena = editTextContrasena.getText().toString().trim();

                // Conectarse a la base de datos
                try {

                    conectarBD();

                   // Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mi_base_de_datos","root", "Myandroidop5");

                    // Ejecutar la consulta SQL
                    Statement st = con.createStatement();
                    st.executeUpdate("INSERT INTO usuarios (correo, contraseña) VALUES ('" + correo + "', '" + contrasena + "');");

                    // Cerrar la conexión
                    st.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }














                // 2º - Mensaje en pantalla de registro completado
                Toast.makeText(getApplicationContext(), "Registro completado con exito !!!!", Toast.LENGTH_SHORT).show();

                // 3º - Redirigira al MainActivity
                Intent intent = new Intent(RegistrerActivity.this, MainActivity.class);
                startActivity(intent);









                /* En caso de que ya este ese correo y contraseña en la base de datos */

                // Nos saldra un mensaje de usuario ya registrado y tendremos que volver a rellenar los campos de diferente manera





            }

            public  void conectarBD() throws SQLException {
                String url="jdbc:mysql://localhost:3306/";
                String user= "root";
                String pwd="Myandroidop5";
                con = DriverManager.getConnection(url,user,pwd);
                System.out.println("Conexión establecida...");
            }
        });






    }
}