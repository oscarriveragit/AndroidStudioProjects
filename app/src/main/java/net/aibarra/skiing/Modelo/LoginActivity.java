package net.aibarra.skiing.Modelo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.aibarra.skiing.R;

import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {


    EditText nombreUsuario, password;
    TextView RessetPassword;
    Button RecordarPassword, botonLogin;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nombreUsuario = (EditText)findViewById(R.id.editTextUsuario);
        password = (EditText)findViewById(R.id.editTextPassword);

        RecordarPassword = (Button)findViewById(R.id.RecordarPassword);
        botonLogin =(Button)findViewById(R.id.botonLogin);

        RessetPassword = (TextView)findViewById(R.id.RessetPassword);






        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tratarLogin();

            }
        });

        RessetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ressetPassword();
            }
        });

        RecordarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordarPassword();
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

        public void ressetPassword (){

            Intent intent = new Intent(this, RessetPasswordActivity.class);
            startActivity(intent);

        }

        public void tratarLogin()  {

        String nombreUsuarioString, passwordString;

        nombreUsuarioString = nombreUsuario.getText().toString();
        passwordString=   password.getText().toString();


        MyDatabase mydatabase = new MyDatabase(this);


        try {
            mydatabase.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean hagologin = mydatabase.usuarioVAlido(nombreUsuarioString, passwordString);

        if (hagologin){
            Intent intent = new Intent(this, ListViewProfesoresActivity.class);
            startActivity(intent);
        }else {

        Toast.makeText(this,"Error al registrarte", Toast.LENGTH_LONG).show();
        }

    }
}

// TENGO QUE CREAR DOBLE VALIDACION PARA PROFESORES Y ALUMNOS

