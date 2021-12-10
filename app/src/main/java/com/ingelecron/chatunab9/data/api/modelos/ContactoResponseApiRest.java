package com.ingelecron.chatunab9.data.api.modelos;

import com.ingelecron.chatunab9.data.db.modelos.Contacto;

import java.util.ArrayList;

public class ContactoResponseApiRest {

    private ArrayList<Contacto> contactoArrayList;

    public ArrayList<Contacto> getContactoArrayList() {
        return contactoArrayList;
    }

    public void setContactoArrayList(ArrayList<Contacto> contactoArrayList) {
        this.contactoArrayList = contactoArrayList;
    }
}
