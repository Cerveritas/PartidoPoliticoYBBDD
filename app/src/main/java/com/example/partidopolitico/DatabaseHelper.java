package com.example.partidopolitico;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Users.db";
    private static final int DATABASE_VERSION = 1;

    // Sentencia SQL para crear la tabla
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS usuarios (" +
                    "correo TEXT NOT NULL," +
                    "contrasena TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
    public void insertarDatos(String correo, String contrasena) {
        // Obtener una instancia de la base de datos para escribir
        SQLiteDatabase db = this.getWritableDatabase();

        // Crear un objeto ContentValues para insertar datos
        ContentValues values = new ContentValues();
        values.put("correo", correo);
        values.put("contrasena",contrasena);

        // Insertar datos en la tabla
        long newRowId = db.insert("usuarios", null, values);

        // Cierra la conexión a la base de datos
        //db.close();
    }
    public boolean verificarUsuario(String correo, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"correo"};
        String selection = "correo=? AND contrasena=?";
        String[] selectionArgs = {correo, contrasena};

        Cursor cursor = db.query("usuarios", columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();
        cursor.close();
        //db.close();

        return count > 0;
    }
}
