package br.com.quattys.manager.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportResponse {
    private Long id;
    private String name;
    private String externalId;
}
