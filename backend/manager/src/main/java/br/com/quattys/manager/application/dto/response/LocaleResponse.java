package br.com.quattys.manager.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocaleResponse {

    private Long id;
    private String street;
    private String complement;
    private String number;
    private String city;
    private String state;
    private String zipCode;
    private String externalId;
}
