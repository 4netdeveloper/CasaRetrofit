package com.example.casaretrofit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.example.casaretrofit.APIServices.API;
import com.example.casaretrofit.APIServices.Deserealizar;
import com.example.casaretrofit.APIServices.Manejador;
import com.example.casaretrofit.Modelos.Casa;
import com.example.casaretrofit.Modelos.Habitacion;
import com.example.casaretrofit.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Casa casa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv1);

        Manejador manejador = API.getApi().create(Manejador.class);

        //objetos Call desde interfaz, SIN ENCRIPTAR
        Call<String> loginCall = manejador.getLogIn("7890", "login", "marcosolivera@gmail.com", "159357");
        Call<Casa> casaCall = manejador.CasaInfo("7890", "Info", "3");

        //datos para encriptar
        String dataLogin = setDataLogin("7890", "login", "marcosolivera@gmail.com", "159357");
        String casaData = setCasaInfo("7890", "info", "3");

        //datos encriptados
        String dataLogin64 = codificar64(dataLogin);
        String casaData64 = codificar64(casaData);

        //llamado para logueo
        Call<String> base64Call = manejador.casaBase64(dataLogin64);
        base64Call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String mensaje = response.body();
                Log.d("respuestaBruta:", mensaje);
                String vuelta = decodificar64(mensaje);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Fallo64->", t.getMessage());
            }
        });

        //llamado para informacion de la casa
        Call<String> casa64 = manejador.casa64(casaData64);
        casa64.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String devolucion = decodificar64(response.body());
                casa = deserialize(devolucion);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Fallo->", t.getMessage());
            }
        });

       /* loginCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String mensaje = decodificar64(response.body());
                    textView.setText(mensaje);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Fallo->", t.getMessage());
            }
        });
*/
        /*casaCall.enqueue(new Callback<Casa>() {
            @Override
            public void onResponse(Call<Casa> call, Response<Casa> response) {
                casa = response.body();
                String mensaje = response.body().getHabitaciones().get(0).getDispositivos().get(0).getNombre();

            }

            @Override
            public void onFailure(Call<Casa> call, Throwable t) {
                Log.d("Fallo-", "->", t);
            }
        });*/

    }

    //metodo para tomar los datos para las solicitudes de LOGIN
    private String setDataLogin(String key, String usr, String nombreUsuario, String password) {
        String mensaje = "key=" + key + "&cmd=" + usr + "&p1=" + nombreUsuario + "&p2=" + password;
        return mensaje;
    }

    //metodo para tomar los datos para las solicitudes de DATOS DE LA CASA
    private String setCasaInfo(String key, String cmd, String p1) {
        return "key=" + key + "&cmd=" + cmd + "&p1=" + p1;
    }

    //metodo para codificar en BASE64
    private String codificar64(String data) {
        byte[] data64 = data.getBytes();
        String base64 = Base64.encodeToString(data64, Base64.DEFAULT);
        return base64;
    }

    //metodo para decodificar en BASE64
    private String decodificar64(String data) {
        byte[] byte64 = Base64.decode(data, Base64.DEFAULT);
        return new String(byte64);
    }

    //metodo para deserealizar el string con la info de la casa
    private Casa deserialize(String respose) {
        Gson gson = new Gson();
        JsonElement json;
        json = gson.fromJson(respose, JsonElement.class);
        Type casaType = new TypeToken<ArrayList<Habitacion>>() {
        }.getType();
        ArrayList<Habitacion> habitacionArrayList = gson.fromJson(json, casaType);
        Casa casa = new Casa(habitacionArrayList);

        return casa;
    }
}
