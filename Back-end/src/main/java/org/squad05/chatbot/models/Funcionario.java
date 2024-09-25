package org.squad05.chatbot.models;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_funcionario;
    private String nome;
    private String email;
    private String setor;
    private String cargo;

    //Getters e setters

    public Long getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Long id) {
        this.id_funcionario = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
