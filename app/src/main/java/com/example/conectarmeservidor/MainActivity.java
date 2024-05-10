package com.example.conectarmeservidor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarInformacion();
    }

    public void cargarInformacion() {
        String url="https://rickandmortyapi.com/api/character";

        StringRequest strRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    recibirRespuesta(new JSONObject(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en servidor", Toast.LENGTH_SHORT).show();
            }
        }
        );

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        rq.add(strRequest);
    }

    public void recibirRespuesta(JSONObject respuesta) {
        try {

            String nombre = respuesta.getJSONArray("results").getJSONObject(0).getString("name");
            Toast.makeText(getApplicationContext(), nombre, Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}