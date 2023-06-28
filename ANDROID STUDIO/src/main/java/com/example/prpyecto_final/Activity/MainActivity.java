package com.example.prpyecto_final.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prpyecto_final.Conexion.ConnectionREST;
import com.example.prpyecto_final.R;
import com.example.prpyecto_final.Api.ServiceApi;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button Ingresar;
    private EditText Usuario;
    private EditText Clave;

    private ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ingresar = findViewById(R.id.ingresar);
        Usuario = findViewById(R.id.usuario);
        Clave = findViewById(R.id.clave);


        serviceApi = ConnectionREST.getConnection().create(ServiceApi.class);
        load(); // Obtener los datos de la API y configurar el adaptador después de obtener los datos

    }
    public void load()
    {
        Call<List<Usuario>> call = serviceApi.listUsuario();//Llamo a la lista de mi api
        call.enqueue(new Callback<List<com.example.prpyecto_final.Activity.Usuario>>() {
            @Override
            /*response es la solicitud de los datos */
            public void onResponse(Call<List<com.example.prpyecto_final.Activity.Usuario>> call, Response<List<com.example.prpyecto_final.Activity.Usuario>> response) {
                if(response.isSuccessful())
                {
                    List<Usuario> lst = response.body();//Carga de datos y es el arreglo con los datos*/

                        Ingresar.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                for(Usuario x:lst) {
                                    if (Usuario.getText().toString().equals(x.getUsername()) && Clave.getText().toString().equals(x.getContrasena()))
                                    {
                                        Intent intent = new Intent(getApplicationContext(), Vista_Cliente.class);
                                        startActivity(intent);

                                        Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Datos Incorrectos", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }

            }

            @Override
            public void onFailure(Call<List<com.example.prpyecto_final.Activity.Usuario>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error en el Api",Toast.LENGTH_SHORT).show();
            }
        });

    }
}