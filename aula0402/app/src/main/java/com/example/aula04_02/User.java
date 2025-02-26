package com.example.aula04_02;

public class User {
    private String email, nome, cpf, nascimento, sexo, telefone, endereco;

    public User(String Email, String Nome, String Cpf, String Nascimento, String Sexo, String Telefone, String Endereco){
        setEmail(Email);
        setNome(Nome);
        setCpf(Cpf);
        setNascimento(Nascimento);
        setSexo(Sexo);
        setTelefone(Telefone);
        setEndereco(Endereco);
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
