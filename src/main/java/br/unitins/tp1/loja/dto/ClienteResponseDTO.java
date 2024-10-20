package br.unitins.tp1.loja.dto;

import java.time.LocalDate;
import br.unitins.tp1.loja.model.Cliente;

public record ClienteResponseDTO(Long id, String nome, String cpf, LocalDate dataNascimento, String username, 
String enderecoEmail, String senha) {
    public static ClienteResponseDTO valueOf(Cliente cliente){
        return new ClienteResponseDTO(
            cliente.getId(), 
            cliente.getNome(), 
            cliente.getCpf(),
            cliente.getDataNascimento(),
            cliente.getUsername(),
            cliente.getEnderecoEmail(),
            cliente.getSenha()
        );
    }
}
