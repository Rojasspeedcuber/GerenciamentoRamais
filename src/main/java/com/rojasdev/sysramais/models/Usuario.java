package com.rojasdev.sysramais.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "usuarios")  // Define a coleção no MongoDB
public class Usuario {

    @Id
    private String id;  // MongoDB gera automaticamente um ID String

    private String nome;
    private List<String> ramais;  // Armazena apenas os números dos ramais associados

    public Usuario() {}

    public Usuario(String nome) {
        this.nome = nome;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getRamais() {
        return ramais;
    }

    public void setRamais(List<String> ramais) {
        this.ramais = ramais;
    }
}
