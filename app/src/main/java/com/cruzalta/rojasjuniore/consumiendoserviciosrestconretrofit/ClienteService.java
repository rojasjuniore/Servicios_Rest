package com.cruzalta.rojasjuniore.consumiendoserviciosrestconretrofit;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;


/**
 * Created by Junior on 04/09/2016.
 */
public interface ClienteService {

    @GET("/clientes")
    void getCliente(Callback<List<Cliente>> callback);
}
