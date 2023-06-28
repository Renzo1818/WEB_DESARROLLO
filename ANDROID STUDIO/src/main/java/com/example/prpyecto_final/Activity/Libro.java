package com.example.prpyecto_final.Activity;

import android.os.Parcelable;

import java.util.Date;

public class Libro
{
    private int idLibro;
    private String titulo;
    private  int Imagen;
    private String categoria;
    private String autor;
    private String fechaPublicacion;


    public Libro() {
    }

    public Libro(int idLibro, String titulo, int imagen, String categoria, String autor, String fechaPublicacion) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        Imagen = imagen;
        this.categoria = categoria;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Libro(int idLibro, String titulo) {
        this.idLibro = idLibro;
        this.titulo = titulo;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

}
