package com.example.sepuede_final;

import java.util.Date;

public class Usuario {

    private int id_usuario;
    private String nombre_usuario;
    private String contraseña;
    private String rol;
    private Date fecha_registro;

    public Usuario(int id_usuario, String nombre_usuario, String contraseña, String rol, Date fecha_registro) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.fecha_registro = fecha_registro;
    }

    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombreUsuario() {
        return nombre_usuario;
    }

    public void setId_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFechaRegistro() {
        return this.fecha_registro;
    }

    public void setFechaRegistro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    public String toString() {
        return this.id_usuario + " - " + this.nombre_usuario + " - " + this.contraseña + " - " + this.rol + " - " + this.fecha_registro;
    }
}
