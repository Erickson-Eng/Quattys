package br.com.quattys.sportive.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AthleteResponse {

    private Long id;
    private String fullName;
    private String socialName;
    private String birthDate;
    private String cpf;
    private String externalId;
    @JsonProperty("address")
    private AddressResponse addressResponse;
    private Double weight;
    private Double height;
    private Double bicepsMeasurement;
    private Double forearmMeasurement;
    private Double chestMeasurement;
    private Double thighMeasurement;
    private Double calfMeasurement;
}
