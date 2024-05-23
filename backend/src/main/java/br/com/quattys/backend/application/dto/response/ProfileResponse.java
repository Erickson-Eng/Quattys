package br.com.quattys.backend.application.dto.response;

public record ProfileResponse(Long id, String name,
                              String birthday, String gender,
                              String cpf, AddressResponse address) {
}
