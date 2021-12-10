package com.ingelecron.chatunab9.presentadores;

import com.ingelecron.chatunab9.data.db.modelos.Contacto;

import java.util.ArrayList;

public interface IPreTab1Fragment {

    void eliminarContactosDB();

    void agregarContactosDB(ArrayList<Contacto> contactoArrayList);

    void obtenerContactosDB();

    void mostrarContactosRV(ArrayList<Contacto> contactoArrayList);
}
