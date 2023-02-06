package br.com.quattys.manager.application.dto.table;

import br.com.quattys.manager.application.dto.request.PracticableRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PracticableTableRequest {

    private List<PracticableRequest> practicableList;
}
