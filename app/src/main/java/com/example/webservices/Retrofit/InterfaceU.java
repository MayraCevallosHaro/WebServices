package com.example.webservices.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface InterfaceU {
    @Headers("Public-Merchant-Id: 9cc81de79fe94712a000d23e51874619")
    @GET("bankList")
    Call<List<com.example.webservices.Retrofit.Bancos>> getBancos();
}
