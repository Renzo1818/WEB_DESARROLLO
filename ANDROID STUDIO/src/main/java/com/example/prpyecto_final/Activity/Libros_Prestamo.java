package com.example.prpyecto_final.Activity;

import java.util.Date;

public class Libros_Prestamo
{
    private int idLibro;
    private String titulo;
    private Date fechaReserva;
    private Date fechaCaducidad;

    public Libros_Prestamo() {
    }

    public Libros_Prestamo(int idLibro, String titulo, Date fechaReserva, Date fechaCaducidad) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.fechaReserva = fechaReserva;
        this.fechaCaducidad = fechaCaducidad;
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

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
}
