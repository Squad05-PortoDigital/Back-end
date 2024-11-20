package org.squad05.chatbot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_funcionario;

    @NotNull
    @Size(min = 11, max = 11) //Garante o tamanho do cpf - mas não formata
    @Column
    private String cpf;

    private String nome;
    private String email;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
}
