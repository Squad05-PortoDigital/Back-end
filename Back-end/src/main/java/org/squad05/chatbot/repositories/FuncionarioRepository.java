package org.squad05.chatbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad05.chatbot.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
