package com.mballem.demoparkapi.web.dto.mapper;

import com.mballem.demoparkapi.entity.Cliente;
import com.mballem.demoparkapi.web.dto.ClienteCreateDTO;
import com.mballem.demoparkapi.web.dto.ClienteResponseDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static Cliente toCliente(ClienteCreateDTO dto) {
        return new ModelMapper().map(dto, Cliente.class);
    }

    public static ClienteResponseDTO toDTO(Cliente cliente){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    public static List<ClienteResponseDTO> toListDTO(List<Cliente> clientes){
        return clientes.stream().map(cliente -> toDTO(cliente)).collect(Collectors.toList());
    }
}
