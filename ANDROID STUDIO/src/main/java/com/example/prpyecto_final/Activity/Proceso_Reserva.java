package com.example.prpyecto_final.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import com.example.prpyecto_final.R;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Proceso_Reserva extends AppCompatActivity {

    private Button Menu;


    private Date fechaHoy;
    private Date fechaFuturo;
    @SuppressLint("MissingInflatedId")

    private TableLayout prestar;

    public static  List<Libros_Devueltos> devol = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_reserva);

        prestar = findViewById(R.id.prestamo);

        /*TRAER LOS DATOS DE LA VISTA RESERVA*/
        for(Libros_Prestamo libro:Reserva.librosPrestados)
        {
            /*FECHA HOY*/
            Calendar calendar = Calendar.getInstance();
            fechaHoy = calendar.getTime();
            libro.setFechaReserva(fechaHoy);

            /*FECHA 5 DIAS*/
            calendar.add(Calendar.DAY_OF_YEAR,5);
            fechaFuturo = calendar.getTime();
            libro.setFechaCaducidad(fechaFuturo);

            List<String> prest = new ArrayList<>();

            prest.add(Integer.toString(libro.getIdLibro()));
            prest.add(libro.getTitulo());
            String fechaReservaStr = ConvertiStringFecha(libro.getFechaReserva());
            String fechaFuture = ConvertiStringFecha(libro.getFechaCaducidad());
            prest.add(fechaReservaStr);
            prest.add(fechaFuture);
            agregarFila(prest);

        }


        if(prestar.getChildCount()>0)
        {
            Toast.makeText(Proceso_Reserva.this, "Libros Prestados Existosamente", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"No tiene ningun libro prestado",Toast.LENGTH_SHORT).show();
        }

        Menu = findViewById(R.id.menu);
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(v.getContext(),Vista_Cliente.class);
                v.getContext().startActivity(intent);
            }
        });

    }
    /*CONVERTIR LA FECHA EN STRING*/
    public String ConvertiStringFecha(Date date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha deseado
        return dateFormat.format(date);
    }
    public void agregarFila(List<String> pres)
    {
        TableRow fila = new TableRow(this);
        TableRow.LayoutParams buttonLayoutParams = new TableRow.LayoutParams();
        /*Indice de la tabla*/
        final int filaIndex = prestar.getChildCount();
        /*Agregar los datos a la tabla*/
        for(String dato:pres)
        {
            TextView textView = new TextView(this);
            textView.setText(dato);
            /*Diseño de letra*/
            textView.setPadding(5, 5, 5, 5);
            textView.setTextSize(13);

            fila.addView(textView);
            textView.setLayoutParams(buttonLayoutParams);
            buttonLayoutParams.gravity = Gravity.CENTER;

        }
        Button devolver = new Button(this);
        devolver.setText("Devolver");
        devolver.setBackgroundColor(Color.BLUE);
        devolver.setPadding(5,5,5,5);
        devolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(),"Libro Devuelto",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(),HistorialPrestamos.class);
                int seguimientoIndex = 0;

                /*El iterator sirve para recorrer y modificar una lista de manera segura, evitando errores
                de indice al quere hacer alguna accion*/

                Iterator<Libros_Prestamo> iterator = Reserva.librosPrestados.iterator();
                while (iterator.hasNext())
                {
                    Libros_Prestamo libro = iterator.next();
                    /*El seguimiento index me permite devolver el libro, de ese indice segun la columna que es*/
                    if (seguimientoIndex == filaIndex) {
                    Libros_Devueltos dev = new Libros_Devueltos();
                    dev.setIdLibro(libro.getIdLibro());
                    dev.setTitulo(libro.getTitulo());
                    dev.setFechaReserva(libro.getFechaReserva());
                    dev.setFechaCaducidad(libro.getFechaCaducidad());

                    intent.putExtra("idLibro", dev.getIdLibro());
                    intent.putExtra("titulo", dev.getTitulo());
                    intent.putExtra("fechaReserva", dev.getFechaReserva());
                    intent.putExtra("fechaCaducidad", dev.getFechaCaducidad());
                    devol.add(dev);

                    /*Eliminar los datos de la colmuna*/
                   iterator.remove();
                    break;

                }
                    /*CONTADOR DEL SEGUIMIENTO_INDICE*/
                    seguimientoIndex++;
                }
                /*ELIMINAR LA COLUMNTA DE LA TABLA*/
                prestar.removeView(fila);

            }
        });

        devolver.setLayoutParams(buttonLayoutParams);

        buttonLayoutParams.gravity = Gravity.CENTER;


        buttonLayoutParams.setMargins(10, 30, 10, 60); // Márgenes para los bordes

        fila.setBackgroundResource(R.drawable.row_border);

        fila.addView(devolver);
        prestar.addView(fila);

    }
}