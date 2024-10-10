package org.squad05.chatbot.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.squad05.chatbot.DTOs.ChatbotProcessoDTO;
import org.squad05.chatbot.configurations.FileStorageProperties;
import org.squad05.chatbot.models.ChatbotProcesso;
import org.squad05.chatbot.models.Funcionario;
import org.squad05.chatbot.repositories.ChatbotRepository;
import org.squad05.chatbot.service.exceptions.DataBaseException;
import org.squad05.chatbot.service.exceptions.ResourceNotFoundException;

@Service
public class ChatbotService {

    @Autowired
    private ChatbotRepository chatbotRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Value("${email.host}")
    private String host;

    @Value("${email.user}")
    private String user;

    @Value("${email.password}")
    private String password;

    @Value("${email.port}")
    private String port;

    private final Path fileStorageLocation;

    //Construtor de arquivos
    @Autowired
    public ChatbotService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
    }

    //Método para mapear o DTO para ChatbotProcesso
    private void mapearProcesso(ChatbotProcesso processo, ChatbotProcessoDTO dto) {
        processo.setTipo_processo(dto.getTipo_processo());
        processo.setData_solicitacao(dto.getData_solicitacao());
        processo.setStatus(dto.getStatus());
        processo.setDescricao(dto.getDescricao());
        processo.setUrgencia(dto.getUrgencia());
        processo.setId_destinatario(dto.getId_destinatario());
    }

    //Criar um processo
    public ChatbotProcesso criarProcesso(ChatbotProcessoDTO chatbotProcessoDTO) {
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(chatbotProcessoDTO.getId_funcionario());

        ChatbotProcesso chatbotProcesso = new ChatbotProcesso();
        chatbotProcesso.setId_funcionario(funcionario);
        mapearProcesso(chatbotProcesso, chatbotProcessoDTO);

        return chatbotRepository.save(chatbotProcesso);
    }

    //Buscar processo por ID
    public ChatbotProcesso buscarProcessoPorId(Long id) {
        return chatbotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //Atualizar um processo
    public ChatbotProcesso atualizarProcesso(Long id, ChatbotProcessoDTO dadosAtualziados) {
        try {
            if (!chatbotRepository.existsById(id)) throw new ResourceNotFoundException(id);
            ChatbotProcesso chatbotProcesso = buscarProcessoPorId(id);
            mapearProcesso(chatbotProcesso, dadosAtualziados);

            return chatbotRepository.save(chatbotProcesso);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    //Deletar um processo
    public void deletarProcesso(Long id) {
    	try {
    		if (!chatbotRepository.existsById(id)) throw new ResourceNotFoundException(id);
    		ChatbotProcesso processo = buscarProcessoPorId(id);
    		chatbotRepository.delete(processo);   		
    	} catch (ResourceNotFoundException e) {
    		throw new ResourceNotFoundException(id);
    	} catch (DataIntegrityViolationException e) {
	    	throw new DataBaseException(e.getMessage());
	    } 
    }

    //Listar todos os processos
    public List<ChatbotProcesso> listarTodosProcessos() {
        return chatbotRepository.findAll();
    }

    //Envio de e-mail
    public void enviarEmail(String to, String subject, String body) throws Exception {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.starttls.enable", "true");

        // Criação da sessão de e-mail
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            // Criação da mensagem de e-mail
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            // Envio da mensagem
            Transport.send(message);

            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    //Upload de arquivos
    public String enviarArquivo(MultipartFile file, Long ocorrenciaId) {
        String nomeArquivo = StringUtils.cleanPath(file.getOriginalFilename());

        //Verificando o tipo de arquivo:
        String contentType = file.getContentType();
        if (!isAllowedFileType(contentType)) {
            throw new RuntimeException("Tipo de arquivo não permitido. Apenas JPEG, PNG e PDF são aceitos.");
        }

        try {
            //Salvando o arquivo fisicamente
            Path targetLocation = fileStorageLocation.resolve(nomeArquivo);
            file.transferTo(targetLocation);

            ChatbotProcesso processo = buscarProcessoPorId(ocorrenciaId);

            processo.setCaminho_arquivo(nomeArquivo);//Associa o arquivo ao processo
            chatbotRepository.save(processo);

            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/processos/download/")
                    .path(nomeArquivo)
                    .toUriString();

        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível armazenar o arquivo " +
                    nomeArquivo + ". Por favor, tente novamente!", ex);
        }
    }

    //Verifica se o tipo de arquivo é permitido
    private boolean isAllowedFileType(String contentType) {
        return contentType.equals("image/jpeg") ||
                contentType.equals("image/png") ||
                contentType.equals("application/pdf");
    }

    //Download de arquivos
    public Resource baixarArquivo(String fileName) {
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Arquivo não encontrado" + fileName);
            }

        } catch (MalformedURLException ex) {
            throw new RuntimeException("Arquivo não encontrado " + fileName, ex);
        }
    }

    //Listar todos os arquivos presentes
    public List<String> listarArquivos() throws IOException {
        return Files.list(fileStorageLocation)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());

    }
}
