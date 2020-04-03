package com.example.casaretrofit.APIServices;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.casaretrofit.Modelos.Casa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Base64;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class API {

    public static final String BASE_URL = "http://www.cerouno.com.ar/";
    private static Retrofit retrofit = null;

    public static Retrofit getApi() {
        if (retrofit == null) {

            GsonBuilder builder = new GsonBuilder().setLenient();
            builder.registerTypeAdapter(Casa.class, new Deserealizar());
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return retrofit;
    }
}
