package net.aibarra.skiing.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import net.aibarra.skiing.Objetos.Usuarios;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cice on 27/11/17.
 */

public class MyDatabase extends SQLiteOpenHelper {


    private static String DB_PATH = "/data/data/net.aibarra.skiing/databases/";
    private static String DB_NAME = "EsquiBBDD.db";
    private static int DATABASE_VERSION = 1;

    private final Context myContext;

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    public void open() throws SQLException {

        //Abre la base de datos
        try {
            createDataBase();
        } catch (IOException e) {
            throw new Error("Ha sido imposible crear la Base de Datos");
        }
    }

    // crea la base de datos y primero verificamos que no existe.

    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){
            this.getWritableDatabase();
        }else{
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

    }

    //  la conexion

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){
            //database does't exist yet.
        }

        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    // copiamos la base de datos

    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public List<Usuarios> getUsuarios(String tipoUsuario) {

        String query = "";

        if (tipoUsuario.equals("profesor")){

            query = "select idAlumno, NombreAlumno, ApellidosAlumno, Usuario, Password from Alumnos order by idAlumno";


        }else {

            query = "select idAlumno, NombreAlumno, ApellidosAlumno, Usuario, Password from Alumnos order by idAlumno";

        }

        List<Usuarios> resultList = new ArrayList<Usuarios>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        try {
            if (c.moveToFirst()) {
                do {
                    resultList.add(new Usuarios(c.getInt(0), c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
                } while(c.moveToNext());
            }

            c.moveToFirst();
        } finally {
            c.close();
        }

        return resultList;
    }

    public String getDireccion(String nombreSeleccionado) {
        String query = "select direccion from clientes where nombre = ?";

        String direccion = "";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, new String[]{nombreSeleccionado});

        try {
            if (c.moveToFirst()) {
                direccion = c.getString(0);
            }

            c.moveToFirst();
        } finally {
            c.close();
        }

        return direccion;
    }

    public boolean usuarioVAlido(String usuario, String password){

            String query= "";

        query = "select Usuario, Password from Alumnos where usuario= ? and password=?";
        String[] condicion = new String[]{usuario, password};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, condicion);

        return (c.moveToFirst());

    }

    public void insertarDatos(String usuario, String password, String nombreUsuario, String apellidosUsuario){


        String query= "";

        query = "select Usuario, Password from Alumnos where usuario= ? and password=?";
        String[] condicion = new String[]{usuario, password};
        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usuario", usuario);
        contentValues.put("password", password);
        contentValues.put("nombreUsuario", nombreUsuario);
        contentValues.put("ApellidosUsuario", apellidosUsuario);
        //contentValues.put("idUsuario", idUsuario);


        db.insert("Usuarios", null, contentValues);


    }

}
