package com.example.prpyecto_final.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prpyecto_final.R;

import java.util.ArrayList;
import java.util.List;

public class Reserva extends AppCompatActivity {

    private TableLayout table;
    private ImageButton Regresar;
    private Button procesar;

    public static List<Libros_Prestamo> librosPrestados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        table = findViewById(R.id.temporal);

        /*Traemos los datos del array donde se guarda los libros cuando se presiona reservar*/
        for (Libro libro : RecyclerViewAdapter.librosReservados)
        {
            List<String> libroData = new ArrayList<>();
            libroData.add(Integer.toString(libro.getIdLibro()));
            libroData.add(libro.getTitulo());
            libroData.add(libro.getAutor());

            agregarFila(libroData);

        }
        procesar = findViewById(R.id.reservar);

        procesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (table.getChildCount() > 0)
                {
                    Intent intent = new Intent(v.getContext(), Proceso_Reserva.class);

                    for (Libro libro : RecyclerViewAdapter.librosReservados) {
                        Libros_Prestamo libroPre = new Libros_Prestamo();
                        libroPre.setIdLibro(libro.getIdLibro());
                        libroPre.setTitulo(libro.getTitulo());

                        //Asignar otras propiedades de libro si es necesario
                        intent.putExtra("idLibro", libroPre.getIdLibro());
                        intent.putExtra("titulo", libroPre.getTitulo());
                        librosPrestados.add(libroPre);
                    }

                   procesar.setEnabled(true);

                    v.getContext().startActivity(intent);
                    RecyclerViewAdapter.librosReservados.clear();



                } else {
                    procesar.setEnabled(false);
                    Toast.makeText(Reserva.this, "Llene libros", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(table.getChildCount()<0)
        {
            Toast.makeText(this,"Carrito Vacio",Toast.LENGTH_SHORT).show();
        }

        Regresar = findViewById(R.id.regresar);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v)
            {

               finish();
            }

        });


    }

    private List<String> agregarFila(List<String> libro) {

        TableRow fila = new TableRow(this);
        TableRow nombres = new TableRow(this);
        /*Dimensiones del boton ELiminar*/
        int width = getResources().getDimensionPixelSize(R.dimen.button_width);
        int height = getResources().getDimensionPixelSize(R.dimen.button_height);
        TableRow.LayoutParams buttonLayoutParams = new TableRow.LayoutParams(width, height);



       // nom.setLayoutParams(buttonLayoutParams);

        final int filaIndex = table.getChildCount();

        for (String dato : libro)
        {
            /*Tamaño de la letra en la tabla */
            TextView textView = new TextView(this);
            textView.setText(dato);

            textView.setPadding(5, 5, 5, 5);
            textView.setTextSize(13);


            fila.addView(textView);
            textView.setLayoutParams(buttonLayoutParams);
            buttonLayoutParams.gravity = Gravity.CENTER;
        }
        /*Boton ELiminar*/
        Button Eliminar = new Button(this);
        Eliminar.setText("Eliminar");
        Eliminar.setBackgroundColor(Color.RED);
        Eliminar.setPadding(5,5,5,5);
        Eliminar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            table.removeView(fila);
            /*Eliminar el libro guardado el array del Recycle View*/
            RecyclerViewAdapter.librosReservados.remove(filaIndex);
        }

        });

        Eliminar.setLayoutParams(buttonLayoutParams);
        buttonLayoutParams.setMargins(30, 30, 40, 60); // Márgenes para los bordes
        /*Centrar el nombre del eiliminar*/
        buttonLayoutParams.gravity = Gravity.CENTER;

        fila.setBackgroundResource(R.drawable.row_border);
        /*Agregar el boton en la tabla*/
        fila.addView(Eliminar);

        table.addView(nombres);
        table.addView(fila);

        return libro;
    }



}