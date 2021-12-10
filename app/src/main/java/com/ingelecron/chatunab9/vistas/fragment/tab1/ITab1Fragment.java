package com.ingelecron.chatunab9.vistas.fragment.tab1;

import com.ingelecron.chatunab9.adaptadores.AdaptadorRVContactos;
import com.ingelecron.chatunab9.data.db.modelos.Contacto;

import java.util.ArrayList;

public interface ITab1Fragment {

    AdaptadorRVContactos crearAdaptadorRVContactos(ArrayList<Contacto> contactoArrayList);

    void inicializadorAdaptadorRVContactos(AdaptadorRVContactos adaptadorRVContactos);

    void generarLinearLayoutManger();
}
