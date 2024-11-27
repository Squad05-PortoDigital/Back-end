package org.squad05.chatbot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "chatbot")
public class ChatbotProcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ocorrencia;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario id_funcionario;

    private String tipo_processo; //Solicitação de férias, justificativa, etc
    private LocalDate data_solicitacao; //Ex: dia em que o usuário solicitou a falta
    private LocalDate data_ocorrencia; //Ex: dia em que o usuário faltou
    private String status; //Pendente por padrão
    private String descricao;
    private String urgencia;
    private Long id_destinatario;
    //Opcionais
    private String hora_extra;
    private LocalDate inicio_ferias;
    private LocalDate fim_ferias;
    private String beneficio;
    private String nome_documento;
    private String nome_arquivo;

    
}
