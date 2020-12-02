package com.example.webservices;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ValidaLogin extends AppCompatActivity {
    private RequestQueue request;
    private StringRequest stringr;
    private TextView txtMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valida_login);
        txtMensaje = (TextView) findViewById(R.id.lblMensaje);
        request = Volley.newRequestQueue(ValidaLogin.this);
        Bundle bundle = this.getIntent().getExtras();
        String URL = "http://uealecpeterson.net/ws/login.php?usr=" + bundle.getString("Usua") + "&pass=" + bundle.getString("Clave");

        stringr = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                txtMensaje.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.add(stringr);
    }
}
