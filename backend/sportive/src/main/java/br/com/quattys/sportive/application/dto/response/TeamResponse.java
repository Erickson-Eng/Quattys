package br.com.quattys.sportive.application.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {

    private Long id;
    private String name;
    private String description;
    private String externalId;

    private Long ownerId;
    private String ownerSocialName;
}
