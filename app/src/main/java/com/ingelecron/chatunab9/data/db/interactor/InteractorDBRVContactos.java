package com.ingelecron.chatunab9.data.db.interactor;

import android.content.ContentValues;
import android.content.Context;

import com.ingelecron.chatunab9.data.db.DBRVContactos;
import com.ingelecron.chatunab9.data.db.modelos.Contacto;

import java.util.ArrayList;

public class InteractorDBRVContactos implements IInteractorDBRVContactos{

    private Context contexto;


    public InteractorDBRVContactos(Context contexto) {
        this.contexto = contexto;
    }


    @Override
    public ArrayList<Contacto> obtenerContactos() {
        DBRVContactos dbrvContactos= new DBRVContactos(contexto);
        return dbrvContactos.leerContactos();
    }

    @Override
    public void agregarContacto(ContentValues contentValues) {
        DBRVContactos dbrvContactos= new DBRVContactos(contexto);
        dbrvContactos.crearContacto(contentValues);
    }

    @Override
    public void borrarContactos() {
        DBRVContactos dbrvContactos= new DBRVContactos(contexto);
        dbrvContactos.eliminarContactos();
    }
}
