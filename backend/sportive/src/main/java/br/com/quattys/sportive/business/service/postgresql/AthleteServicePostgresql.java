package br.com.quattys.sportive.business.service.postgresql;

import br.com.quattys.sportive.application.dto.mapper.AthleteMapper;
import br.com.quattys.sportive.application.dto.request.AthleteRequest;
import br.com.quattys.sportive.application.dto.response.AthleteResponse;
import br.com.quattys.sportive.application.dto.table.AthleteTableResponse;
import br.com.quattys.sportive.business.entity.Athlete;
import br.com.quattys.sportive.business.service.AthleteService;
import br.com.quattys.sportive.business.service.exception.EntityNotFoundException;
import br.com.quattys.sportive.resource.repository.AthleteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AthleteServicePostgresql implements AthleteService {

    private AthleteRepository athleteRepository;
    private AthleteMapper athleteMapper;

    @Override
    public AthleteResponse save(AthleteRequest athleteRequest) {
        Athlete athlete = athleteMapper.requestToAthlete(athleteRequest);
        athlete = athleteRepository.save(athlete);
        return athleteMapper.athleteToResponse(athlete);
    }

    @Override
    public AthleteResponse update(Long id, AthleteRequest athleteRequest) {
        Athlete athlete = verifyIfExist(id);
        athleteMapper.updateAthleteRequest(athleteRequest, athlete);
        athlete = athleteRepository.save(athlete);
        return athleteMapper.athleteToResponse(athlete);
    }

    @Override
    public AthleteResponse getAthleteById(Long id) {
        Athlete athlete = verifyIfExist(id);
        return athleteMapper.athleteToResponse(athlete);
    }

    @Override
    public AthleteTableResponse getAthleteBySocialName(String socialName) {
        List<Athlete> athletes = verifyIfExist(socialName);
        List<AthleteResponse> athleteResponseList = convertAthleteListInResponse(athletes);
        return AthleteTableResponse
                .builder()
                .athleteResponseList(athleteResponseList)
                .build();
    }

    @Override
    public AthleteTableResponse getAllAthleteFromCity(String city) {
        List<Athlete> athletes = athleteRepository.findAthleteByAddress_City(city);
        List<AthleteResponse> athleteResponseList = convertAthleteListInResponse(athletes);
        return AthleteTableResponse
                .builder()
                .athleteResponseList(athleteResponseList)
                .build();
    }

    @Override
    public void delete(Long id) {
        Athlete athlete = verifyIfExist(id);
        athleteRepository.delete(athlete);
    }

    private List<AthleteResponse> convertAthleteListInResponse(List<Athlete> athletes){
        return athletes.stream()
                .map(athleteMapper::athleteToResponse)
                .collect(Collectors.toList());
    }

    private Athlete verifyIfExist(Long id){
        return athleteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Athlete not registered"));
    }

    private List<Athlete> verifyIfExist(String socialName){
        return athleteRepository.findAthleteBySocialName(socialName);
    }
}
