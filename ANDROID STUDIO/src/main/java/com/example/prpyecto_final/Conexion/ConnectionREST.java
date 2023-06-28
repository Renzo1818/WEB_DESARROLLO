package com.example.prpyecto_final.Conexion;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionREST
{
    private static final String URL = "http://leandro2003-001-site1.htempurl.com/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getConnection()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}
