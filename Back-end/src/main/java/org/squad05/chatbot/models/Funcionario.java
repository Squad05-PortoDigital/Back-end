package org.squad05.chatbot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}
