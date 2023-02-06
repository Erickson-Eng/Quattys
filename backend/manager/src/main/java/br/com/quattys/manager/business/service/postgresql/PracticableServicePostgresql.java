package br.com.quattys.manager.business.service.postgresql;


import br.com.quattys.manager.application.dto.mapper.PracticableMapper;
import br.com.quattys.manager.application.dto.request.PracticableRequest;
import br.com.quattys.manager.application.dto.response.PracticableResponse;
import br.com.quattys.manager.application.dto.table.PracticableTableRequest;
import br.com.quattys.manager.application.dto.table.PracticableTableResponse;
import br.com.quattys.manager.business.entity.Practicable;
import br.com.quattys.manager.business.service.PracticableService;
import br.com.quattys.manager.business.service.exception.DatabaseException;
import br.com.quattys.manager.business.service.exception.EntityNotFoundException;
import br.com.quattys.manager.resource.repository.PracticableRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PracticableServicePostgresql implements PracticableService {

    private PracticableRepository practicableRepository;
    private PracticableMapper practicableMapper;


    @Transactional
    @Override
    public PracticableTableResponse savePracticableSports(PracticableTableRequest practicable) {
        try {
            List<Practicable> practicableList = practicable.getPracticableList()
                    .stream()
                    .map(practicableMapper::convertDtoToEntity)
                    .collect(Collectors.toList());

            practicableList = practicableRepository.saveAll(practicableList);

            return PracticableTableResponse.builder()
                    .practicableList(practicableList.stream().map(practicableMapper::convertEntityToDTO).collect(Collectors.toList()))
                    .build();
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
            throw new DatabaseException("Erro ao salvar daados");
        }
    }

    @Transactional
    @Override
    public void excludePracticableSports(List<Long> idList) {
        try {
            practicableRepository.deleteAllById(idList);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro ao excluir valores");
        }
    }

    @Override
    public PracticableResponse updatePracticableInfo(Long id, PracticableRequest practicableRequest) {
        Practicable practicable = verifyIfExist(id);
        practicableMapper.updatePracticableInfo(practicableRequest, practicable);
        practicable = practicableRepository.save(practicable);
        return practicableMapper.convertEntityToDTO(practicable);
    }


    protected Practicable verifyIfExist(Long id){
        return practicableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Esporte não está cadastrado nos praticaveis"));
    }
}
