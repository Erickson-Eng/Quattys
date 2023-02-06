package br.com.quattys.manager.application.dto.response;



import br.com.quattys.manager.business.entity.Practicable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GymResponse {

    private Long id;
    private String name;
    private String rules;
    private String gymAccess;
    private String externalId;

    private List<Practicable> sports;

}
