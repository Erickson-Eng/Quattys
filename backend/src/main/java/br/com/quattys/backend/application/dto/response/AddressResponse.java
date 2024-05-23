package br.com.quattys.backend.application.dto.response;

public record AddressResponse(Long id, String street, String city,
                              String addrState, String zipCode) {
}
