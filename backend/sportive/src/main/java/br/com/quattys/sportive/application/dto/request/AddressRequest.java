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

    @NotBlank
    private String street;
    private String complement;

    @NotNull
    private String number;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String zipCode;
}
