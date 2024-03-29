package br.com.quattys.sportive.application.dto.table;

import br.com.quattys.sportive.application.dto.request.AddressRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressTableRequest {

    @JsonProperty("addresses")
    private List<AddressRequest> addressRequests;
}
