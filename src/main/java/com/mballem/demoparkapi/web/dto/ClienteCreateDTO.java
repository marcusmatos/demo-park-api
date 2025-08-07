package com.mballem.demoparkapi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateDTO {

    @Email(message = "Formato do e-mail inválido", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotBlank(message = "E-mail é um campo obrigatório")
    private String username;
    @NotBlank(message = "Senha é obrigatório")
    @Size(min = 6, max = 20, message = "Deve ter 6 caracteres")
    private String password;
    public String nome;
    public String cpf;
}
