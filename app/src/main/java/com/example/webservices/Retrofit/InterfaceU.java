package com.example.webservices.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface InterfaceU {
    @Headers("Public-Merchant-Id: 881fac1dd6c844deada1450313ea7d69")
    @GET("bankList")
    Call<List<com.example.webservices.Retrofit.Bancos>> getBancos();
}
