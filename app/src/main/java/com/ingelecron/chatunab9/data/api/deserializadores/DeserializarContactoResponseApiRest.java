package com.ingelecron.chatunab9.data.api.deserializadores;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.ingelecron.chatunab9.data.api.modelos.ContactoResponseApiRest;
import com.ingelecron.chatunab9.data.db.modelos.Contacto;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DeserializarContactoResponseApiRest implements JsonDeserializer<ContactoResponseApiRest> {


    @Override
    public ContactoResponseApiRest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonArray jsonArray= json.getAsJsonObject().getAsJsonArray(JsonKeysApiRest.MEDIA_ARRAY);

        Gson gson= new Gson();
        ContactoResponseApiRest contactoResponseApiRest= gson.fromJson(json, ContactoResponseApiRest.class);
        contactoResponseApiRest.setContactoArrayList(deserializarJsonContacto(jsonArray));

        return contactoResponseApiRest;
    }

    private ArrayList<Contacto> deserializarJsonContacto(JsonArray jsonArray) {

        ArrayList<Contacto> contactoArrayList= new ArrayList<>();

        for (int i=0; i < jsonArray.size(); i++){

            JsonObject jsonObject= jsonArray.get(i).getAsJsonObject();

            String id= jsonObject.get(JsonKeysApiRest.MEDIA_ARRAY_ID).getAsString();
            String imagen= jsonObject.get(JsonKeysApiRest.MEDIA_ARRAY_IMAGEN).getAsString();
            String nombre= jsonObject.get(JsonKeysApiRest.MEDIA_ARRAY_NOMBRE).getAsString();
            String especie= jsonObject.get(JsonKeysApiRest.MEDIA_ARRAY_ESPECIES).getAsString();

            Contacto contacto= new Contacto();
            contacto.setId(id);
            contacto.setFoto(imagen);
            contacto.setNombre(nombre);
            contacto.setEspecie(especie);

            contactoArrayList.add(contacto);
        }

        return contactoArrayList;
    }
}
