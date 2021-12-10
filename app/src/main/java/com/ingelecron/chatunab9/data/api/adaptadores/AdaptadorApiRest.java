package com.ingelecron.chatunab9.data.api.adaptadores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ingelecron.chatunab9.data.api.ConstantesApiRest;
import com.ingelecron.chatunab9.data.api.IEndPointsApiRest;
import com.ingelecron.chatunab9.data.api.deserializadores.DeserializarContactoResponseApiRest;
import com.ingelecron.chatunab9.data.api.modelos.ContactoResponseApiRest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdaptadorApiRest {

    public IEndPointsApiRest conexionApiRest(){

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(ConstantesApiRest.URL)
                .addConverterFactory(GsonConverterFactory.create(construirGsonDeserializadorMedia()))
                .build();

        return retrofit.create(IEndPointsApiRest.class);
    }

    private Gson construirGsonDeserializadorMedia() {

        GsonBuilder gsonBuilder= new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ContactoResponseApiRest.class, new DeserializarContactoResponseApiRest());

        return gsonBuilder.create();
    }
}
