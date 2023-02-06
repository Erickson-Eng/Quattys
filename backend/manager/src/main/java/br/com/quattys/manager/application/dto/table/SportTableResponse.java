package br.com.quattys.manager.application.dto.table;

import br.com.quattys.manager.application.dto.response.SportResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportTableResponse {


    private List<SportResponse> sportList;
}
