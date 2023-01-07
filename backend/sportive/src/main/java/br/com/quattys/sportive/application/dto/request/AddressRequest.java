package br.com.quattys.sportive.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotBlank(message = "É necessário informar o nome da rua")
    private String street;
    private String complement;

    @NotNull(message = "É necessário informar o número da casa ou predio")
    private String number;

    @NotNull(message = "É necessário informar a cidade")
    private String city;

    @NotNull(message = "É necessário informar o estado")
    private String state;

    @NotNull(message = "É necessário informar o cep")
    private String zipCode;
}
