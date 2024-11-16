package org.squad05.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.squad05.chatbot.models.Funcionario;
import org.squad05.chatbot.repositories.FuncionarioRepository;
import org.squad05.chatbot.service.exceptions.DataBaseException;
import org.squad05.chatbot.service.exceptions.ResourceNotFoundException;

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
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado: id" + id));
    }

    //Buscar funcionário por CPF
    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("CPF não encontrado: " + cpf));
    }

    //Atualizar um funcionário
    public Funcionario atualizarFuncionario(Long id, Funcionario dadosAtualizados) {
    	try {
    			if (!funcionarioRepository.existsById(id)) throw new ResourceNotFoundException(id);
    	        Funcionario funcionario = buscarFuncionarioPorId(id);
    	        funcionario.setNome(dadosAtualizados.getNome());
                funcionario.setCpf(dadosAtualizados.getCpf());
    	        funcionario.setEmail(dadosAtualizados.getEmail());
    	        funcionario.setCargo(dadosAtualizados.getCargo());
    	        return funcionarioRepository.save(funcionario);	
    	        
    	} catch (ResourceNotFoundException e) {
    		throw new ResourceNotFoundException(id);
    	}
    }

    //Deletar um funcionário
    public void deletarFuncionario(Long id){
    	try {
    		if (!funcionarioRepository.existsById(id)) throw new ResourceNotFoundException(id);
    		funcionarioRepository.deleteById(id);
    	} catch (ResourceNotFoundException e) {
    		throw new ResourceNotFoundException(id);
    	} catch (DataIntegrityViolationException e) {
	    	throw new DataBaseException(e.getMessage());
	    }   
    }

    //Listar todos os funcionários
    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

}
