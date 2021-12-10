package com.ingelecron.chatunab9.data.db.interactor;

import android.content.ContentValues;

import com.ingelecron.chatunab9.data.db.modelos.Contacto;

import java.util.ArrayList;

public interface IInteractorDBRVContactos {

    ArrayList<Contacto> obtenerContactos();

    void agregarContacto(ContentValues contentValues);

    void borrarContactos();
}
