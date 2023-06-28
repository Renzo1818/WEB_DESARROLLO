package com.example.prpyecto_final.Activity;

public class Usuario
{
    private int idUsuario;
    private String username;
    private String contrasena;
    private boolean estado;

    public Usuario() {
    }

    public Usuario(int idUsuario, String username, String contrasena, boolean estado) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
