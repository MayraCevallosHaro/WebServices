package com.example.webservices;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webservices.Retrofit.Bancos;
import com.example.webservices.Retrofit.InterfaceU;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView txtListBanco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtListBanco = findViewById(R.id.txtBancoLista);
        getBanco();
    }

    private void getBanco() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceU interfaceU = retrofit.create(InterfaceU.class);

        Call<List<Bancos>> call = interfaceU.getBancos();
        call.enqueue(new Callback<List<Bancos>>() {
            @Override
            public void onResponse(Call<List<Bancos>> call, Response<List<Bancos>> response) {

                if(!response.isSuccessful()){
                    txtListBanco.setText("Codigo: "+response.code());
                    return;
                }
                List<Bancos> bancosList = response.body();
                String content = "";
                for(Bancos post: bancosList){
                    content=content+post.getName() +"\n";
                }
                txtListBanco.setText(content);

            }

            @Override
            public void onFailure(Call<List<Bancos>> call, Throwable t) {
                txtListBanco.setText(t.getMessage());

            }
        });

    }


    public void btnEnviar(View view) {


        EditText txtNombre = (EditText) findViewById(R.id.txtUsuario);
        EditText txtClave = (EditText) findViewById(R.id.txtClave);

        if(txtNombre.getText().toString().length()==0 || txtClave.getText().toString().length()==0)
        {
            Toast.makeText(this, "Campos vacios" , Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, ValidaLogin.class);
            Bundle b = new Bundle();
            b.putString("Usua", txtNombre.getText().toString());
            b.putString("Clave", txtClave.getText().toString());
            intent.putExtras(b);
            startActivity(intent);
        }


    }
}