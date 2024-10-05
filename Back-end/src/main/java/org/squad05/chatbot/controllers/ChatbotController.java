package org.squad05.chatbot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.squad05.chatbot.DTOs.ChatbotOcorrenciaDTO;
import org.squad05.chatbot.DTOs.EmailDTO;
import org.squad05.chatbot.models.ChatbotOcorrencia;
import org.squad05.chatbot.service.ChatbotService;

@RestController
@RequestMapping("/ocorrencias") //ENDPOINT DAS OCORRENCIAS
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    //Criar processo (POST)
    @PostMapping
    public ResponseEntity<ChatbotOcorrencia> criarProcesso(@RequestBody ChatbotOcorrenciaDTO processo) {
        ChatbotOcorrencia novoProcesso = chatbotService.criarProcesso(processo);
        return ResponseEntity.ok(novoProcesso);
    }

    //Buscar processo by id (GETBYID)
    @GetMapping("/{id}")
    public ResponseEntity<ChatbotOcorrencia> buscarProcessoPorId(@PathVariable Long id) {
        ChatbotOcorrencia processo = chatbotService.buscarProcessoPorId(id);
        return ResponseEntity.ok(processo);
    }

    //Atualizar processo (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<ChatbotOcorrencia> atualizarProcesso(@PathVariable Long id, @RequestBody ChatbotOcorrenciaDTO dadosAtualizados) {
        ChatbotOcorrencia processoAtualizado = chatbotService.atualizarProcesso(id, dadosAtualizados);
        return ResponseEntity.ok(processoAtualizado);
    }

    //Deletar processo (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcesso(@PathVariable Long id) {
        chatbotService.deletarProcesso(id);
        return ResponseEntity.noContent().build();
    }

    //Listar todos os processos (GET)
    @GetMapping
    public ResponseEntity<List<ChatbotOcorrencia>> listarTodosProcessos() {
        List<ChatbotOcorrencia> processos = chatbotService.listarTodosProcessos();
        return ResponseEntity.ok(processos);
    }

    //Enviar arquivo - NÃO FINALIZADA.
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("processoId") Long processoId) {
        try {
            return chatbotService.enviarArquivo(file, processoId);
        } catch (Exception e) {
            return "Erro ao enviar o arquivo: " + e.getMessage();
        }
    }

    //Enviar e-mail
    @PostMapping("/send-email")
    public ResponseEntity<String> enviarEmail(@RequestBody EmailDTO email) {
        try {
            chatbotService.enviarEmail(email.getDestinatario(), email.getAssunto(), email.getMensagem());
            return ResponseEntity.ok("Email enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao enviar o email: " + e.getMessage());
        }
    }

}
