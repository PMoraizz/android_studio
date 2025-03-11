package com.example.barbearia;

public class Agendamento {
    private String titulo, profissional, hora, data;

    public Agendamento(String titulo, String profissional, String hora, String data){
        this.titulo = titulo;
        this.profissional = profissional;
        this.hora = hora;
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProfissional() {
        return profissional;
    }

    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }



}
