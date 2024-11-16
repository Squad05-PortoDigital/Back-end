package org.squad05.chatbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad05.chatbot.models.Funcionario;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
