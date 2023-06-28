package com.example.prpyecto_final.Api;

import com.example.prpyecto_final.Activity.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi
{
    @GET("usuario")
    public abstract Call<List<Usuario>> listUsuario();

}
