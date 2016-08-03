package com.example.maxwellvj.basedatossqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private EditText nombre;
    private EditText email;
    private Button insertar;
    private Button actualizar;
    private Button eliminar;
    private Button consultar;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre=(EditText)findViewById(R.id.txtVal);
        email=(EditText)findViewById(R.id.txtReg);
        resultado=(TextView)findViewById(R.id.txtResultado);
        insertar=(Button)findViewById(R.id.btnInsertar);
        actualizar=(Button)findViewById(R.id.btnActualixar);
        eliminar=(Button)findViewById(R.id.btnEliminar);
        consultar=(Button)findViewById(R.id.btnConsultar);

        UsuariosSQLITEHelper usdbh= new UsuariosSQLITEHelper(this,"DBUsuarios",null,1);
        db=usdbh.getWritableDatabase();

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo= email.getText().toString();
                String nom= nombre.getText().toString();

                ContentValues nuevoRegistro= new ContentValues();
                nuevoRegistro.put("nombre",nom);
                nuevoRegistro.put("email",correo);
                db.insert("Usuarios",null,nuevoRegistro);
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo= email.getText().toString();
                String nom= nombre.getText().toString();

                ContentValues valores= new ContentValues();
                valores.put("nombre",nom);
                db.update("Usuario",valores,"email="+correo,null);
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo= email.getText().toString();

                db.delete("Usuarios","email"+correo,null);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c= db.rawQuery("SELECT email,nombre FROM Usuarios",null);
                resultado.setText("");
                if(c.moveToFirst())
                {
                do {
                    String nom=c.getString(0);
                    String correo=c.getString(1);

                    resultado.append(" "+nom+" - "+correo+"\n");

                }while (c.moveToNext());
                }
            }
        });
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }*/
}
