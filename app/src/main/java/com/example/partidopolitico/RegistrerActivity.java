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


        Button botonRegistrarse = findViewById(R.id.buttonrRegistrarse);


        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextCorreo = findViewById(R.id.editTextCorreoRegistrer);
                EditText editTextContrasena = findViewById(R.id.editTextContraseñaRegistrer);

                try {
                    // Configura los datos de conexión a la base de datos
                    String host = "localhost";
                    String port = "3306";
                    String username = "root";
                    String password = "admin";
                    String database = "mi_base_de_datos";

                    // Establece la conexión con la base de datos
                    connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);


                    // Comprobamos si la conexion se ha establecido correctamente
                    if (connection.isClosed()) {
                        // La conexión no se ha establecido correctamente
                        Toast.makeText(getApplicationContext(), "No se ha podido conectar con la base de datos", Toast.LENGTH_SHORT).show();
                    } else {
                        // La conexión se ha establecido correctamente
                        Toast.makeText(getApplicationContext(), "Se ha establecido conexion con la base de datos", Toast.LENGTH_SHORT).show();

                    }




                    // Obtén los valores de los campos de texto
                    String correo = editTextCorreo.getText().toString().trim();
                    String contrasena = editTextContrasena.getText().toString().trim();

                    // Verifica que el correo electrónico no esté ya registrado
                    String query = "SELECT * FROM usuarios WHERE correo = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, correo);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        // El correo electrónico ya está registrado, no se inserta en la base de datos
                        Toast.makeText(getApplicationContext(), "El correo electrónico ya está registrado", Toast.LENGTH_SHORT).show();
                    } else {
                        // El correo electrónico no está registrado, se inserta en la base de datos
                        query = "INSERT INTO usuarios (correo, contraseña) VALUES (?, ?);";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, correo);
                        statement.setString(2, contrasena);

                        // Verifica que la consulta SQL se ejecutó correctamente
                        int filasAfectadas = statement.executeUpdate();

                        if (filasAfectadas == 1) {
                            // La consulta SQL se ejecutó correctamente, se inserta el usuario en la base de datos
                            Toast.makeText(getApplicationContext(), "Registro completado con exito !!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            // La consulta SQL no se ejecutó correctamente, se produjo un error
                            Toast.makeText(getApplicationContext(), "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();

                    // En caso de que ya este ese correo y contraseña en la base de datos
                    // Nos saldra un mensaje de usuario ya registrado y tendremos que volver a rellenar los campos de diferente manera

                    if (e.getErrorCode() == 1062) {
                        Toast.makeText(getApplicationContext(), "El correo o contraseña ya están registrados", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
   }
}







