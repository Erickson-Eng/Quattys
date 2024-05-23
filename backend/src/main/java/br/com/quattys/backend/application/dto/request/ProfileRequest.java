package br.com.quattys.backend.application.dto.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

public record ProfileRequest(@NotNull String name,
                             String birthday,
                             String gender,
                             @CPF String cpf,
                             @NotNull AddressRequest homeAddress) implements Serializable {
}
