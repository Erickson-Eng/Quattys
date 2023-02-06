package br.com.quattys.manager.business.service;

import br.com.quattys.manager.application.dto.request.PracticableRequest;
import br.com.quattys.manager.application.dto.response.PracticableResponse;
import br.com.quattys.manager.application.dto.table.PracticableTableRequest;
import br.com.quattys.manager.application.dto.table.PracticableTableResponse;

import java.util.List;

public interface PracticableService {

    PracticableTableResponse savePracticableSports(PracticableTableRequest practicable);

    void excludePracticableSports(List<Long> idList);

    PracticableResponse updatePracticableInfo(Long id, PracticableRequest practicableRequest);
}
