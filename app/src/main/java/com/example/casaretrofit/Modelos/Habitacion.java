package com.example.casaretrofit.Modelos;

import com.example.casaretrofit.APIServices.Deserealizar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

public class Habitacion {

    @SerializedName("SpecificKey")
    private String SpecificKey;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("id")
    private String id;
    @SerializedName("imagen")
    private String imagen;
    @SerializedName("dispositivos")
    private List<Dispositivos> dispositivos;

    public Habitacion() {
    }

    public Habitacion(String SpecificKey, String nombre, String id, String imagen, List<Dispositivos> dispositivos) {
        this.SpecificKey = SpecificKey;
        this.nombre = nombre;
        this.id = id;
        this.imagen = imagen;
        this.dispositivos = dispositivos;
    }

    public String getSpecificKey() {
        return SpecificKey;
    }

    public void setSpecificKey(String specificKey) {
        SpecificKey = specificKey;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Dispositivos> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivos> dispositivos) {
        this.dispositivos = dispositivos;
    }

}
