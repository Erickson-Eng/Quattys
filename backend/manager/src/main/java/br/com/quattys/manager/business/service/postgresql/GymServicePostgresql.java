package br.com.quattys.manager.business.service.postgresql;

import br.com.quattys.manager.application.dto.mapper.GymMapper;
import br.com.quattys.manager.application.dto.request.GymRequest;
import br.com.quattys.manager.application.dto.response.GymResponse;
import br.com.quattys.manager.business.entity.Gym;
import br.com.quattys.manager.business.service.GymService;
import br.com.quattys.manager.business.service.exception.DatabaseException;
import br.com.quattys.manager.business.service.exception.EntityNotFoundException;
import br.com.quattys.manager.resource.repository.GymRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GymServicePostgresql implements GymService {

    private GymRepository gymRepository;
    private GymMapper gymMapper;

    @Override
    public GymResponse saveGym(GymRequest gymRequest) {
        try {
            Gym gym = gymMapper.convertDtoFromEntity(gymRequest);
            gym = gymRepository.save(gym);
            return gymMapper.convertEntityToDto(gym);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro ao salvar gym");
        }
    }

    @Override
    public GymResponse updateGym(Long id, GymRequest gymRequest) {
        Gym gym = verifyIfExist(id);
        gymMapper.updateGymInfo(gymRequest, gym);
        return null;
    }

    @Override
    public void deleteGymById(Long id) {
        try {
            Gym gym = verifyIfExist(id);
            gymRepository.delete(gym);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("O ginásio pode está associado a vários itens que é necessário remoção primeiro");
        }

    }
    private Gym verifyIfExist(Long id){
        return gymRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ginasio não cadastrado"));
    }
}
