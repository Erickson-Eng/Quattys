package br.com.quattys.manager.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PracticableRequest {


    @NotNull
    private Long sportId;
    @NotNull
    private Long gymId;
    @NotNull
    private String availabilityStatus;
}
