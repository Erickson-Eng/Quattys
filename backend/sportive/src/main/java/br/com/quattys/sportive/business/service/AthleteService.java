package br.com.quattys.sportive.business.service;

import br.com.quattys.sportive.application.dto.request.AthleteRequest;
import br.com.quattys.sportive.application.dto.response.AthleteResponse;
import br.com.quattys.sportive.application.dto.table.AthleteTableResponse;
import org.springframework.stereotype.Service;

@Service
public interface AthleteService {

    AthleteResponse save(AthleteRequest athleteRequest);
    AthleteResponse update(Long id, AthleteRequest athleteRequest);
    AthleteResponse getAthleteById(Long id);
    AthleteTableResponse getAthleteBySocialName(String socialName);

    AthleteTableResponse getAllAthleteFromCity(String city);
    void delete (Long id);

}
