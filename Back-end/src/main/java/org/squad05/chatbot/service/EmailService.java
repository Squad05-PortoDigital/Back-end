package org.squad05.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    @Value("${email.host}")
    private String host;

    @Value("${email.user}")
    private String user;

    @Value("${email.password}")
    private String password;

    @Value("${email.port}")
    private String port;

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

    // Envio de e-mail para o responsável
    public void enviarEmailResponsavel(String to, String nomeFuncionario, String nomeDestinatario, String tipoProcesso, long idOcorrencia) throws Exception {
        String subject = "Nova ocorrência registrada: " + tipoProcesso;
        String body = String.format(
                "Prezado(a) %s,\n\n" +
                        "Uma nova ocorrência do tipo '%s' foi registrada no sistema por %s.\n\n" +
                        "Código da ocorrência: %s\n\n" +
                        "Por favor, verifique os detalhes no sistema e tome as ações necessárias.\n\n" +
                        "Atenciosamente,\n" +
                        "Equipe de Suporte\n" +
                        "(Esta mensagem foi gerada automaticamente)",
                nomeFuncionario, tipoProcesso, nomeDestinatario, idOcorrencia
        );
        enviarEmail(to, subject, body);
    }

    // Envio de e-mail para o solicitante
    public void enviarEmailSolicitante(String to, String nomeSolicitante, String tipoProcesso, long idOcorrencia) throws Exception {
        String subject = "Confirmação de ocorrência registrada: " + tipoProcesso;
        String body = String.format(
                "Prezado(a) %s,\n\n" +
                        "Sua ocorrência do tipo '%s' foi registrada com sucesso.\n\n" +
                        "Código da ocorrência: %s\n\n" +
                        "A equipe responsável irá analisar e responder em breve.\n\n" +
                        "Atenciosamente,\n" +
                        "Equipe de Suporte\n" +
                        "(Esta mensagem foi gerada automaticamente)",
                nomeSolicitante, tipoProcesso, idOcorrencia
        );
        enviarEmail(to, subject, body);
    }
}
