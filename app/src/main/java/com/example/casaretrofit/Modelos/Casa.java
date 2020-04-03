package com.example.casaretrofit.Modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Casa {

    @SerializedName("habitaciones")
    private List<Habitacion> habitaciones;

    public Casa() {
    }

    public Casa(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }


}
