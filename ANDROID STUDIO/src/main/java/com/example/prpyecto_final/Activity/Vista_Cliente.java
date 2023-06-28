package com.example.prpyecto_final.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.prpyecto_final.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Vista_Cliente extends AppCompatActivity {


    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    //private TextView welcome;
    private ImageButton histo;
    private ImageButton reserva;

    private ImageButton prest;

    private Libro lib = new Libro();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_cliente);


        histo = findViewById(R.id.historial);

        prest = findViewById(R.id.btnPrestamo);

        recyclerView = findViewById(R.id.listarlibro);
        /*MOSTRAR LOS DATOS UNO ENCIMA DE OTRA*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Inicializar la lista de libros creada
        adapter = new RecyclerViewAdapter(getLibro());
        recyclerView.setAdapter(adapter);

        /*VER CARRITO*/
        reserva = findViewById(R.id.verReserva);
        reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(),Reserva.class);
                v.getContext().startActivity(intent);
            }
        });

        /*VER HISTORIAL*/
        histo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),HistorialPrestamos.class);
                v.getContext().startActivity(intent);
            }
        });

        /*VER PRESTAMO*/
        prest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Proceso_Reserva.class);
                v.getContext().startActivity(intent);
            }
        });
    }
    public List<Libro> getLibro()
    {
        List<Libro> libro = new ArrayList<>();

        libro.add(new Libro(1,"Ciudad y los Perros",R.drawable.ciudad_perros,"Accion","Mario Vargas Llosa","12/05/1990"));
        libro.add(new Libro(2,"Caballero Carmelo",R.drawable.caballero_carmelo,"Accion","Abraham Valdelomar","01/05/1987"));
        libro.add(new Libro(3,"Harry Potter",R.drawable.harry,"Fantasia","J.K Rowling","06/03/2001"));
        libro.add(new Libro(4,"Ventajas de ser invisible",R.drawable.ventajas_invisible,"Cine Romantico","Stephen Chowsky","05/01/2003"));
        return libro;
    }
}