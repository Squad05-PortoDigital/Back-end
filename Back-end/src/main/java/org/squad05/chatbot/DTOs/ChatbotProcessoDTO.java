package org.squad05.chatbot.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ChatbotProcessoDTO {
    private Long id_funcionario;
    private String tipo_processo;
    private LocalDate data_solicitacao;
    private LocalDate data_ocorrencia;
    private String status = "Pendente";
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
