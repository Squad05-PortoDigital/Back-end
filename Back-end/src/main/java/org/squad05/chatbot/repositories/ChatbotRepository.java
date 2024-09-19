package org.squad05.chatbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad05.chatbot.models.ChatbotProcesso;

public interface ChatbotRepository extends JpaRepository<ChatbotProcesso, Long> {
}
