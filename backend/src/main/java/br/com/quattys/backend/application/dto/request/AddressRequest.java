package br.com.quattys.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link br.com.quattys.backend.domain.entity.Address}
 */
public record AddressRequest(@NotBlank String street, String addrComplement,
                             String city, String addrState,
                             @NotBlank String zipCode) implements Serializable {
}