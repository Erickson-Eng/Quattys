package br.com.quattys.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

public record ProfileRequest(

        @NotBlank(message = "Name is mandatory")
        String name,

        String birthday,

        String gender,

        @CPF
        @NotBlank(message = "CPF is mandatory")
        String cpf,

        @NotNull
        @Validated
        AddressRequest homeAddress
) implements Serializable {
}
