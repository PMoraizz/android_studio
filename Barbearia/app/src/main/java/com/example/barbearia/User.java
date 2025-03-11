package com.example.barbearia;

public class User {
    private String email, nome, cpf, nascimento, telefone;

    public User(String Email, String Nome, String Cpf, String Nascimento, String Telefone) {
        setEmail(Email);
        setNome(Nome);
        setCpf(Cpf);
        setNascimento(Nascimento);
        setTelefone(Telefone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
