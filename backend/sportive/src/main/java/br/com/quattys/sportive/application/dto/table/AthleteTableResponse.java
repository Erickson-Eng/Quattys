package br.com.quattys.sportive.application.dto.table;

import br.com.quattys.sportive.application.dto.response.AthleteResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AthleteTableResponse {

    @JsonProperty("athletes")
    private List<AthleteResponse> athleteResponseList;
}
