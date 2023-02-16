package br.com.quattys.manager.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PracticableResponse {

    private Long id;
    private Long sportId;
    private Long gymId;
    private String sportName;
    private String availabilityStatus;
    private String externalId;
}
