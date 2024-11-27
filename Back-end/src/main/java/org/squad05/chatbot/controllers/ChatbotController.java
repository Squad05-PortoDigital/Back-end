package org.squad05.chatbot.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import org.squad05.chatbot.models.ChatbotProcesso;
import org.squad05.chatbot.service.ChatbotService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/processos")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    //Criar processo (POST)
    @PostMapping
    public ResponseEntity<ChatbotProcesso> criarProcesso(@RequestBody ChatbotProcessoDTO processo) {
        ChatbotProcesso novoProcesso = chatbotService.criarProcesso(processo);
        return ResponseEntity.ok(novoProcesso);
    }

    //Buscar processo por ID (GETBYID)
    @GetMapping("/{id}")
    public ResponseEntity<ChatbotProcesso> buscarProcessoPorId(@PathVariable Long id) {
        ChatbotProcesso processo = chatbotService.buscarProcessoPorId(id);
        return ResponseEntity.ok(processo);
    }

    //Atualizar processo (PUT)
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

    //Listar todos os processos (GET)
    @GetMapping
    public ResponseEntity<List<ChatbotProcesso>> listarTodosProcessos() {
        List<ChatbotProcesso> processos = chatbotService.listarTodosProcessos();
        return ResponseEntity.ok(processos);
    }

    //Upload de arquivo (POST)
    @PostMapping("/upload")
    public ResponseEntity<String> enviarArquivo(@RequestParam("file") MultipartFile file,
            @RequestParam("ocorrenciaId") Long ocorrenciaId) {
        String downloadUri = chatbotService.enviarArquivo(file, ocorrenciaId);
        return ResponseEntity.ok("Upload realizado! Download link: " + downloadUri);
    }

    //Download de arquivo (GET)
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> baixarArquivo(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = chatbotService.baixarArquivo(fileName);

        //Determina o tipo de arquivo
        String contentType;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    //Listar arquivos disponíveis (GET)
    @GetMapping("/files-list")
    public ResponseEntity<List<String>> listarArquivos() throws IOException {
        List<String> fileNames = chatbotService.listarArquivos();
        return ResponseEntity.ok(fileNames);
    }
}
