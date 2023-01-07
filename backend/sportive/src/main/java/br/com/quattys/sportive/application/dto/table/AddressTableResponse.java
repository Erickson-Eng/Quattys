package br.com.quattys.sportive.application.dto.table;

import br.com.quattys.sportive.application.dto.response.AddressResponse;
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
public class AddressTableResponse {

    @JsonProperty("addresses")
    private List<AddressResponse> addressResponses;
}
