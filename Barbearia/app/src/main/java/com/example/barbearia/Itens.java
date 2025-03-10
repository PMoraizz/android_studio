package com.example.barbearia;

public class Itens {
    private String titulo, descricao, preco, tempo;
    private int img;

    public Itens(String titulo, String descricao, String preco, String tempo, int img){
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.tempo = tempo;
        this.img = img;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
