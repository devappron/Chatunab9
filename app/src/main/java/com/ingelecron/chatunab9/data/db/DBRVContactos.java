package com.ingelecron.chatunab9.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ingelecron.chatunab9.data.db.modelos.Contacto;

import java.util.ArrayList;

public class DBRVContactos extends SQLiteOpenHelper {

    private Context contexto;

    public DBRVContactos(@Nullable Context context) {
        super(context, ConstantesDBRVContactos.NOMBRE_DB, null, ConstantesDBRVContactos.VERSION_DB);
        this.contexto= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCrearTablaContacto="CREATE TABLE "+ConstantesDBRVContactos.T_CONTACTO+"(" +
                ConstantesDBRVContactos.T_CONTACTO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesDBRVContactos.T_CONTACTO_FOTO+" TEXT," +
                ConstantesDBRVContactos.T_CONTACTO_NOMBRE+" TEXT," +
                ConstantesDBRVContactos.T_CONTACTO_ESPECIE+" TEXT " +
//                ConstantesDBRVContactos.T_CONTACTO_CORREO+" TEXT " +
                ")";

        db.execSQL(sqlCrearTablaContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ConstantesDBRVContactos.T_CONTACTO);
        onCreate(db);
    }

    public ArrayList<Contacto> leerContactos(){

        ArrayList<Contacto> contactoArrayList= new ArrayList<>();

        String sqlLeerContactos= "SELECT * FROM "+ConstantesDBRVContactos.T_CONTACTO;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor recorridoContactos= db.rawQuery(sqlLeerContactos, null);

        try {

            while (recorridoContactos.moveToNext()){

                Contacto contacto= new Contacto();
                contacto.setId(String.valueOf(recorridoContactos.getInt(0)));
                contacto.setFoto(recorridoContactos.getString(1));
                contacto.setNombre(recorridoContactos.getString(2));
                contacto.setEspecie(recorridoContactos.getString(3));
//                contacto.setCorreo(recorridoContactos.getString(4));

                contactoArrayList.add(contacto);
            }

            recorridoContactos.close();
            db.close();

        }catch (SQLException e){
            recorridoContactos.close();
            db.close();
        }

        return contactoArrayList;
    }

    public void crearContacto(ContentValues contentValues){
        SQLiteDatabase db= this.getWritableDatabase();
        db.insert(ConstantesDBRVContactos.T_CONTACTO, null, contentValues);
        db.close();
    }

    public void eliminarContactos(){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(ConstantesDBRVContactos.T_CONTACTO, null, null);
        db.close();
    }
}
