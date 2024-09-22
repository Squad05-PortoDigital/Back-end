package org.squad05.chatbot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.squad05.chatbot.DTOs.ChatbotProcessoDTO;
import org.squad05.chatbot.models.ChatbotProcesso;
import org.squad05.chatbot.service.ChatbotService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    //Listar todos os funcionários (READ)
    @GetMapping
    public ResponseEntity<List<ChatbotProcesso>> listarTodosProcessos() {
        List<ChatbotProcesso> processos = chatbotService.listarTodosProcessos();
        return ResponseEntity.ok(processos);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("processoId") Long processoId) {
        try {
            return chatbotService.enviarArquivo(file, processoId);
        } catch (Exception e) {
            return "Erro ao enviar o arquivo: " + e.getMessage();
        }
    }

}
