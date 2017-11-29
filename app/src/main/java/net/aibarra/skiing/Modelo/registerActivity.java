package net.aibarra.skiing.Modelo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.aibarra.skiing.R;

public class registerActivity extends AppCompatActivity {


    EditText nombreUsuario, apellidosUsuario, usuario, password;
    Button botonRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombreUsuario = findViewById(R.id.editTextNombreUsuario);
        apellidosUsuario = findViewById(R.id.editTextApellidosUsuario);
        usuario = findViewById(R.id.editTextUsuario);
        password = findViewById(R.id.editTextPassword);
        botonRegistro = findViewById(R.id.botonRegistrar);

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonRegistro();
            }
        });


    }


    public void recordarPassword(){

        String nombre = nombreUsuario.getText().toString();
        String password_string = password.getText().toString();

        SharedPreferences misPreferencias = getSharedPreferences("skiing", MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("nombre", nombre);
        editor.putString("password",password_string);
        editor.commit();

    }

    public void botonRegistro(){

        MyDatabase myDatabase = new MyDatabase(this);
        myDatabase.insertarDatos( nombreUsuario.getText().toString(),  apellidosUsuario.getText().toString(),  usuario.getText().toString(),  password.getText().toString() );

    }

}
