package org.squad05.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.squad05.chatbot.DTOs.ChatbotProcessoDTO;
import org.squad05.chatbot.models.ChatbotProcesso;
import org.squad05.chatbot.models.Funcionario;
import org.squad05.chatbot.repositories.ChatbotRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ChatbotService {
    @Autowired
    private ChatbotRepository chatbotRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar o diretório de arquivos.");
        }
    }

    //Criar um processo
    public ChatbotProcesso criarProcesso(ChatbotProcessoDTO chatbotProcessoDTO) {
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(chatbotProcessoDTO.getFuncionarioId());

        ChatbotProcesso chatbotProcesso = new ChatbotProcesso();
        chatbotProcesso.setFuncionario(funcionario);
        chatbotProcesso.setTipoProcesso(chatbotProcessoDTO.getTipoProcesso());
        chatbotProcesso.setDataSolicitacao(chatbotProcessoDTO.getDataSolicitacao());
        chatbotProcesso.setStatus(chatbotProcessoDTO.getStatus());;
        chatbotProcesso.setDescricao(chatbotProcessoDTO.getDescricao());
        chatbotProcesso.setUrgencia(chatbotProcessoDTO.getUrgencia());
        chatbotProcesso.setResponsavelId(chatbotProcessoDTO.getResponsavelId());

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

        processo.setTipoProcesso(dadosAtualziados.getTipoProcesso());
        processo.setDataSolicitacao(dadosAtualziados.getDataSolicitacao());
        processo.setStatus(dadosAtualziados.getStatus());
        processo.setDescricao(dadosAtualziados.getDescricao());
        processo.setUrgencia(dadosAtualziados.getUrgencia());
        processo.setResponsavelId(dadosAtualziados.getResponsavelId());

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

    //Upload de arquvios
    public String enviarArquivo(MultipartFile file, Long processoId) throws Exception {

        //Cria o diretório se não existir
        Files.createDirectories(Paths.get(uploadDir));
        //Salvando o arquivo no sistema
        Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
        Files.write(filePath, file.getBytes());

        ChatbotProcesso processo = buscarProcessoPorId(processoId);
        processo.setCaminhoArquivo(filePath.toString());
        chatbotRepository.save(processo);

        return "Arquivo enviado com sucesso: " + filePath.toString();
    }
}
