package com.mballem.demoparkapi.service;

import com.mballem.demoparkapi.entity.Cliente;
import com.mballem.demoparkapi.entity.Usuario;
import com.mballem.demoparkapi.exception.CPFUniqueViolationException;
import com.mballem.demoparkapi.exception.EntityNotFoundException;
import com.mballem.demoparkapi.repository.ClienteRepository;
import com.mballem.demoparkapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public List<Cliente> buscarTodos() {return clienteRepository.findAll();}

    @Transactional
    public Cliente create(Cliente cliente){
        try{
            return clienteRepository.save(cliente);
        }catch (DataIntegrityViolationException exception){
            throw new CPFUniqueViolationException(String.format("CPF {%s}já cadastrado", cliente.getCpf()));
        }
    }

    public void deletarPorId(Long id) {
        clienteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente de id #%s não encontrado.", id))
        );
    }

    @Transactional
    public Usuario salvarUsuario(Usuario usr){
        return usuarioRepository.save(usr);
    }
}
