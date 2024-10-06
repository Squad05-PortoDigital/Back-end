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
import org.squad05.chatbot.DTOs.ChatbotProcessoDTO;
import org.squad05.chatbot.DTOs.EmailDTO;
import org.squad05.chatbot.models.ChatbotProcesso;
import org.squad05.chatbot.service.ChatbotService;

@RestController
@RequestMapping("/processos")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    //Criar processo (CREATE)
    @PostMapping
    public ResponseEntity<ChatbotProcesso> criarProcesso(@RequestBody ChatbotProcessoDTO processo) {
        ChatbotProcesso novoProcesso = chatbotService.criarProcesso(processo);
        return ResponseEntity.ok(novoProcesso);
    }

    //Get processo by id (GETBYID)
    @GetMapping("/{id}")
    public ResponseEntity<ChatbotProcesso> buscarProcessoPorId(@PathVariable Long id) {
        ChatbotProcesso processo = chatbotService.buscarProcessoPorId(id);
        return ResponseEntity.ok(processo);
    }

    //Atualizar processo (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<ChatbotProcesso> atualizarProcesso(@PathVariable Long id, @RequestBody ChatbotProcessoDTO dadosAtualizados) {
        ChatbotProcesso processoAtualizado = chatbotService.atualizarProcesso(id, dadosAtualizados);
        return ResponseEntity.ok(processoAtualizado);
    }

    //Deletar processo (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcesso(@PathVariable Long id) {
        chatbotService.deletarProcesso(id);
        return ResponseEntity.noContent().build();
    }

    //Listar todos os processos (READ)
    @GetMapping
    public ResponseEntity<List<ChatbotProcesso>> listarTodosProcessos() {
        List<ChatbotProcesso> processos = chatbotService.listarTodosProcessos();
        return ResponseEntity.ok(processos);
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
