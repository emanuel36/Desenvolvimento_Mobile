package com.example.firebase;

public class Contato {
    String nome;
    String numero;
    String id;

    @Override
    public String toString() {
        return "Nome: " + nome + '\n' + "Número: " + numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
