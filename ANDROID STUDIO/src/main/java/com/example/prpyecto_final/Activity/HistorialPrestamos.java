package com.example.prpyecto_final.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prpyecto_final.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistorialPrestamos extends AppCompatActivity
{

    private ImageButton Regresar;
    private TableLayout historial;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_prestamos);

        historial = findViewById(R.id.tbHistorial);
        Regresar = findViewById(R.id.volverMenu);


        /*TRAER LOS DATOS DEL PROCESO_RESERVA*/
        for(Libros_Devueltos devu :Proceso_Reserva.devol)
        {
            List<String> histo = new ArrayList<>();

            histo.add(Integer.toString(devu.getIdLibro()));
            histo.add(devu.getTitulo());
            String fechaR = formatDateToString(devu.getFechaReserva());
            histo.add(fechaR);
            String fechaC = formatDateToString(devu.getFechaCaducidad());
            histo.add(fechaC);

            agregarFila(histo);
        }


        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Vista_Cliente.class);
                v.getContext().startActivity(intent);
            }
        });

        if(historial.getChildCount()<0)
        {
            Toast.makeText(this,"Historial Vacia",Toast.LENGTH_SHORT).show();
        }

    }
    public String formatDateToString(Date date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha deseado
        return dateFormat.format(date);
    }

    public void agregarFila(List<String> devol)
    {
        TableRow fila = new TableRow(this);
        TableRow.LayoutParams buttonLayoutParams = new TableRow.LayoutParams();

        /*Agregar los datos a la tabla*/
        for(String dato: devol)
        {
            TextView textView = new TextView(this);
            textView.setText(dato);
            /*Diseño de letra*/
            textView.setPadding(5, 5, 10, 5);
            textView.setTextSize(16);

            /*Agregamos como columna*/
            fila.addView(textView);
            textView.setLayoutParams(buttonLayoutParams);
            buttonLayoutParams.gravity = Gravity.CENTER;

        }


        buttonLayoutParams.setMargins(10, 30, 10, 60); // Márgenes para los bordes

        fila.setBackgroundResource(R.drawable.row_border);

        /*Agregamos en la fila*/
        historial.addView(fila);
    }
}