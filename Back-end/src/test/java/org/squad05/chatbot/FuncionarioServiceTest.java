package org.squad05.chatbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.squad05.chatbot.models.Funcionario;
import org.squad05.chatbot.repositories.FuncionarioRepository;
import org.squad05.chatbot.service.FuncionarioService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FuncionarioServiceTest {
    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void criarFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Lucas");
        funcionario.setCpf("11122233345");

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        Funcionario criado = funcionarioService.criarFuncionario(funcionario);

        assertNotNull(criado);
        assertEquals("Lucas", criado.getNome());
        assertEquals("11122233345", criado.getCpf());
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    public void testBuscarFuncionarioPorId() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId_funcionario(1L);

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        Funcionario encontrado = funcionarioService.buscarFuncionarioPorId(1L);

        assertNotNull(encontrado);
        assertEquals(1L, encontrado.getId_funcionario());
        verify(funcionarioRepository, times(1)).findById(1L);
    }
    @Test
    public void testDeletarFuncionario() {
        when(funcionarioRepository.existsById(1L)).thenReturn(true);

        funcionarioService.deletarFuncionario(1L);

        verify(funcionarioRepository, times(1)).deleteById(1L);
    }

}
