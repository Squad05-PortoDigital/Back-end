package org.squad05.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.squad05.chatbot.DTOs.ChatbotProcessoDTO;
import org.squad05.chatbot.models.ChatbotProcesso;
import org.squad05.chatbot.models.Funcionario;
import org.squad05.chatbot.repositories.ChatbotRepository;

import java.util.List;

@Service
public class ChatbotService {
    @Autowired
    private ChatbotRepository chatbotRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    //Criar ou atualizar um processo
    public ChatbotProcesso criarProcesso(ChatbotProcessoDTO chatbotProcessoDTO) {
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(chatbotProcessoDTO.getFuncionarioId());
        Funcionario funcionarioResponsavel = funcionarioService.buscarFuncionarioPorId(chatbotProcessoDTO.getFuncionarioResponsavelId());

        ChatbotProcesso chatbotProcesso = new ChatbotProcesso();
        chatbotProcesso.setFuncionario(funcionario);
        chatbotProcesso.setTipoProcesso(chatbotProcessoDTO.getTipoProcesso());
        chatbotProcesso.setDataSolicitacao(chatbotProcessoDTO.getDataSolicitacao());
        chatbotProcesso.setStatus(chatbotProcessoDTO.getStatus());;
        chatbotProcesso.setDescricao(chatbotProcessoDTO.getDescricao());
        chatbotProcesso.setUrgencia(chatbotProcessoDTO.getUrgencia());
        chatbotProcesso.setArquivos(chatbotProcessoDTO.getArquivos());
        chatbotProcesso.setFuncionarioResponsavel(funcionarioResponsavel);

        return chatbotRepository.save(chatbotProcesso);
    }

    //Buscar processo por ID
    public ChatbotProcesso buscarProcessoPorId(Long id) {
        return chatbotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));
    }

    //Atualizar um processo
    public ChatbotProcesso atualizarProcesso(Long id, ChatbotProcessoDTO dadosAtualziados){
        ChatbotProcesso processo = buscarProcessoPorId(id);
        Funcionario funcionarioResponsavel = funcionarioService.buscarFuncionarioPorId(dadosAtualziados.getFuncionarioResponsavelId());

        processo.setTipoProcesso(dadosAtualziados.getTipoProcesso());
        processo.setDataSolicitacao(dadosAtualziados.getDataSolicitacao());
        processo.setStatus(dadosAtualziados.getStatus());
        processo.setDescricao(dadosAtualziados.getDescricao());
        processo.setUrgencia(dadosAtualziados.getUrgencia());
        processo.setArquivos(dadosAtualziados.getArquivos());
        processo.setFuncionarioResponsavel(funcionarioResponsavel);

        return chatbotRepository.save(processo);
    }

    //Deletar um processo
    public void deletarProcesso(Long id) {
        ChatbotProcesso processo = buscarProcessoPorId(id);
        chatbotRepository.delete(processo);
    }

    //Listar todos os processos
    public List<ChatbotProcesso> listarTodosProcessos() {
        return chatbotRepository.findAll();
    }
}
