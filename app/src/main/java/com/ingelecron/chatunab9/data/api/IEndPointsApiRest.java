package com.ingelecron.chatunab9.data.api;

import com.ingelecron.chatunab9.data.api.modelos.ContactoResponseApiRest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IEndPointsApiRest {

    @GET(ConstantesApiRest.GET_MEDIA_ALL)
    Call<ContactoResponseApiRest> getMedia();
}
