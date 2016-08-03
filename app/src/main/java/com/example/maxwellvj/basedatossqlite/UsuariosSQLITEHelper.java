package com.example.maxwellvj.basedatossqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Maxwell V J on 31/07/2016.
 */
public class UsuariosSQLITEHelper extends SQLiteOpenHelper {

    String sqlCreate= "CREATE TABLE Usuarios (nombre TEXT, email TEXT)";

    public UsuariosSQLITEHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bdUsuarios) {
        bdUsuarios.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bdUsuarios, int versionAnterior, int versionSiguiente) {
        bdUsuarios.execSQL("DROP TABLE IF EXISTS Usuarios");

        bdUsuarios.execSQL(sqlCreate);

    }
}
