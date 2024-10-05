package org.squad05.chatbot.DTOs;

import java.time.LocalDate;

public class ChatbotOcorrenciaDTO {
    //A CLASSE DTO É RESPONSÁVEL POR FORMATAR O JSON DO ENDPOINT.
    private Long id_funcionario;
    private String tipo_ocorrencia;
    private LocalDate data_solicitacao;
    private String status;
    private String descricao;
    private String urgencia;
    private Long id_destinatario;
    private String caminho_arquivo;

    //Getters e Setters
    public Long getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Long id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getTipo_ocorrencia() {
        return tipo_ocorrencia;
    }

    public void setTipo_ocorrencia(String tipo_ocorrencia) {
        this.tipo_ocorrencia = tipo_ocorrencia;
    }

    public LocalDate getData_solicitacao() {
        return data_solicitacao;
    }

    public void setData_solicitacao(LocalDate data_solicitacao) {
        this.data_solicitacao = data_solicitacao;
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

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(String urgencia) {
        this.urgencia = urgencia;
    }

    public Long getId_destinatario() {
        return id_destinatario;
    }

    public void setId_destinatario(Long id_destinatario) {
        this.id_destinatario = id_destinatario;
    }

    public String getCaminho_arquivo() {
        return caminho_arquivo;
    }

    public void setCaminho_arquivo(String caminho_arquivo) {
        this.caminho_arquivo = caminho_arquivo;
    }
}
