package br.com.quattys.manager.business.service;

import br.com.quattys.manager.application.dto.request.SportRequest;
import br.com.quattys.manager.application.dto.response.SportResponse;
import br.com.quattys.manager.application.dto.table.SportTableResponse;

public interface SportService {

    SportResponse saveSport(SportRequest sportRequest);
    SportResponse updateSport(Long id, SportRequest sportRequest);
    void deleteSportById(Long id);
    SportResponse findSportById(Long id);
    SportTableResponse findSportByName(String name);
}
