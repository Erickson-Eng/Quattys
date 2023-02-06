package br.com.quattys.manager.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GymRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String rules;
    @NotBlank
    private String gymAccess;
}
