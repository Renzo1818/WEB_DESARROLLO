package com.example.prpyecto_final.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.prpyecto_final.Conexion.ConnectionREST;
import com.example.prpyecto_final.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView titulo, autor, categoria,año;
        private Button reserva;
        ImageView imglibro;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.Titulo);
            autor = itemView.findViewById(R.id.Autor);
            categoria = itemView.findViewById(R.id.Categoria);
            año = itemView.findViewById(R.id.Año);
            reserva = itemView.findViewById(R.id.Reservar);
            imglibro = itemView.findViewById(R.id.imgLibro);


        }
    }

    private List<Libro> librocate;

    public static List<Libro> librosReservados = new ArrayList<>();

    public RecyclerViewAdapter(List<Libro> librocate) {
        this.librocate = librocate;

    }


    /*LLAMAR AL ITEM_LIBRO YA GREAGRLO AL RECUCLY*/
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.titulo.setText(librocate.get(position).getTitulo());
        holder.categoria.setText(librocate.get(position).getCategoria());
        holder.autor.setText(librocate.get(position).getAutor());
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/aaaa");
        String date = dateFormat.format(librocate.get(position).getFechaPublicacion());
        holder.año.setText(date);*/
        holder.año.setText(librocate.get(position).getFechaPublicacion());
        holder.imglibro.setImageResource(librocate.get(position).getImagen());
        holder.reserva.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Libro libro = librocate.get(position);


                if(librosReservados.contains(libro))
                {
                    Toast.makeText(v.getContext(), "El libro ya está reservado", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(v.getContext(), Reserva.class);
                    intent.putExtra("idLibro", libro.getIdLibro());
                    intent.putExtra("titulo", libro.getTitulo());

                    intent.putExtra("autor", libro.getAutor());



                    librosReservados.add(libro);


                    v.getContext().startActivity(intent);
                }
            }
        });

    }

    public int getItemCount()
    {
        return librocate.size();
    }
}
