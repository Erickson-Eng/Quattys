package br.com.quattys.manager.business.service.postgresql;

import br.com.quattys.manager.application.dto.mapper.SportMapper;
import br.com.quattys.manager.application.dto.request.SportRequest;
import br.com.quattys.manager.application.dto.response.SportResponse;
import br.com.quattys.manager.application.dto.table.SportTableResponse;
import br.com.quattys.manager.business.entity.Sport;
import br.com.quattys.manager.business.service.SportService;
import br.com.quattys.manager.business.service.exception.DatabaseException;
import br.com.quattys.manager.business.service.exception.EntityNotFoundException;
import br.com.quattys.manager.resource.repository.SportRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SportServicePostgresql implements SportService {

    private SportMapper sportMapper;
    private SportRepository sportRepository;

    @Override
    public SportResponse saveSport(SportRequest sportRequest) {
        try{
            Sport sport = sportMapper.convertDtoToEntity(sportRequest);
            sport = sportRepository.save(sport);
            return sportMapper.convertEntityToDto(sport);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Não foi possivel salvar");
        }
    }

    @Override
    public SportResponse updateSport(Long id, SportRequest sportRequest) {
        Sport sport = verifyIfExist(id);
        sportMapper.updateSportInfo(sportRequest, sport);
        sport = sportRepository.save(sport);
        return sportMapper.convertEntityToDto(sport);
    }

    @Override
    public void deleteSportById(Long id) {
        Sport sport = verifyIfExist(id);
        sportRepository.delete(sport);

    }

    @Override
    public SportResponse findSportById(Long id) {
        Sport sport = verifyIfExist(id);
        return sportMapper.convertEntityToDto(sport);
    }

    @Override
    public SportTableResponse findSportByName(String name) {
        List<Sport> sports = verifyIfExist(name);
        List<SportResponse> responses = sports.stream()
                .map(sportMapper::convertEntityToDto).collect(Collectors.toList());
        return SportTableResponse.builder()
                .sportList(responses)
                .build();
    }

    private Sport verifyIfExist(Long id){
        return sportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Esporte não cadastrado"));
    }

    private List<Sport> verifyIfExist(String name){
        return sportRepository.findAllByName(name);
    }
}
