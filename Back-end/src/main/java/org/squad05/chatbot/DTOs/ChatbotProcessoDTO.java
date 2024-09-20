package org.squad05.chatbot.DTOs;

import java.time.LocalDate;
import java.util.List;

public class ChatbotProcessoDTO {
    private Long funcionarioId;
    private String tipoProcesso;
    private LocalDate dataSolicitacao;
    private String status;
    private String descricao;
    private String urgencia;
    private List<String> arquivos;
    private Long funcionarioResponsavelId;

    //Getters e Setters

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
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

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(String urgencia) {
        this.urgencia = urgencia;
    }

    public List<String> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<String> arquivos) {
        this.arquivos = arquivos;
    }

    public Long getFuncionarioResponsavelId() {
        return funcionarioResponsavelId;
    }

    public void setFuncionarioResponsavelId(Long funcionarioResponsavelId) {
        this.funcionarioResponsavelId = funcionarioResponsavelId;
    }
}
