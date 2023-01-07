package br.com.quattys.sportive.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AthleteRequest {

    @NotBlank
    @Size(min = 3)
    private String fullName;

    @NotBlank
    private String socialName;
    @NotBlank
    private String birthDate;

    @NotBlank(message = "não pode ser nulo ou em branco")
    private String cpf;
    private Double weight;
    private Double height;
    private Long addressId;
    private Double bicepsMeasurement;
    private Double forearmMeasurement;
    private Double chestMeasurement;
    private Double thighMeasurement;
    private Double calfMeasurement;

    public AthleteRequest(String fullName, String socialName, String birthDate, String cpf) {
        this.fullName = fullName;
        this.socialName = socialName;
        this.birthDate = birthDate;
        this.cpf = cpf;
    }
}
