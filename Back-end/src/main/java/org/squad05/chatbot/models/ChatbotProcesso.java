package org.squad05.chatbot.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "chatbot")
public class ChatbotProcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionarioid", nullable = false)
    private Funcionario funcionarioid;

    private String tipoprocesso; //Solicitação de férias, justificativa, etc
    private LocalDate datasolicitacao;
    private String status;
    private String descricao;

    //Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionarioid() {
        return funcionarioid;
    }

    public void setFuncionarioid(Funcionario funcionarioid) {
        this.funcionarioid = funcionarioid;
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
