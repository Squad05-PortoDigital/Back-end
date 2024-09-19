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

    private String tipoprocesso; //Solicitação de férias, justificativa, etc
    private LocalDate datasolicitacao;
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

    public String getTipoprocesso() {
        return tipoprocesso;
    }

    public void setTipoprocesso(String tipoprocesso) {
        this.tipoprocesso = tipoprocesso;
    }

    public LocalDate getDatasolicitacao() {
        return datasolicitacao;
    }

    public void setDatasolicitacao(LocalDate datasolicitacao) {
        this.datasolicitacao = datasolicitacao;
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
