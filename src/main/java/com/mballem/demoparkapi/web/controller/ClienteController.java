package com.mballem.demoparkapi.web.controller;

import com.mballem.demoparkapi.entity.Cliente;
import com.mballem.demoparkapi.entity.Usuario;
import com.mballem.demoparkapi.service.ClienteService;
import com.mballem.demoparkapi.web.dto.ClienteResponseDTO;
import com.mballem.demoparkapi.web.dto.ClienteCreateDTO;
import com.mballem.demoparkapi.web.dto.mapper.ClienteMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Contem todas as operações crud de clientes")
@RestController
@RequestMapping("api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    public static final String URI_CLIENTES = "api/v1/clientes";

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> getAll() {
        List<Cliente> listClientes = clienteService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(ClienteMapper.toListDTO(listClientes));
    }

    @Operation(summary = "Criar um novo cliente", description = "Recurso para criar um novo cliente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponseDTO.class))),
                    @ApiResponse(responseCode = "409", description = "Email já cadastrado no sistema",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
                    @ApiResponse(responseCode = "422", description = "Recurso não processado por dados inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))
            })
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> create(@Valid @RequestBody ClienteCreateDTO clienteCreateDTO) {

        Usuario usr = new Usuario();

        usr.setPassword(clienteCreateDTO.getPassword());
        usr.setUsername(clienteCreateDTO.getUsername());
        Usuario usr_salvo = clienteService.salvarUsuario(usr);
        Cliente cliente = ClienteMapper.toCliente(clienteCreateDTO);
        cliente.setUsuario(usr_salvo);
        Cliente cli = clienteService.create(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteMapper.toDTO(cli));
    }

    @Operation(summary = "Buscar cliente por id", description = "Recurso para buscar cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Long id) {
        Cliente cli = clienteService.buscarPorId(id);
        return ResponseEntity.ok(ClienteMapper.toDTO(cli));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
       clienteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
