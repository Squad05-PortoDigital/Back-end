package org.squad05.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.squad05.chatbot.models.Funcionario;
import org.squad05.chatbot.repositories.FuncionarioRepository;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    //Criar funcionário
    public Funcionario criarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    //Buscar funcionário por ID
    public Funcionario buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    //Atualizar um funcionário
    public Funcionario atualizarFuncionario(Long id, Funcionario dadosAtualizados) {
        Funcionario funcionario = buscarFuncionarioPorId(id);
        funcionario.setNome(dadosAtualizados.getNome());
        funcionario.setEmail(dadosAtualizados.getEmail());
        funcionario.setSetor(dadosAtualizados.getSetor());
        funcionario.setCargo(dadosAtualizados.getCargo());

        return funcionarioRepository.save(funcionario);
    }

    //Deletar um funcionário
    public void deletarFuncionario(Long id){
        Funcionario funcionario = buscarFuncionarioPorId(id);
        funcionarioRepository.delete(funcionario);
    }

    //Listar todos os funcionários
    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

}
