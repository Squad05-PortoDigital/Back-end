package org.squad05.chatbot.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "chatbot")
public class ChatbotProcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcesso;

    @ManyToOne
    @JoinColumn(name = "funcionario", nullable = false)
    private Funcionario funcionario;

    private String tipoProcesso; //Solicitação de férias, justificativa, etc
    private LocalDate dataSolicitacao;
    private String status;
    private String descricao;

    //Getters e setters

    public Long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getTipoProcesso() {
        return tipoProcesso;
    }

    public void setTipoProcesso(String tipoProcesso) {
        this.tipoProcesso = tipoProcesso;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
