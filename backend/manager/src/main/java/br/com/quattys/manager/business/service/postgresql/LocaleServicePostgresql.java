package br.com.quattys.manager.business.service.postgresql;

import br.com.quattys.manager.application.dto.mapper.LocaleMapper;
import br.com.quattys.manager.application.dto.request.LocaleRequest;
import br.com.quattys.manager.application.dto.response.LocaleResponse;
import br.com.quattys.manager.application.dto.table.LocaleTableResponse;
import br.com.quattys.manager.business.entity.Locale;
import br.com.quattys.manager.business.service.LocaleService;
import br.com.quattys.manager.business.service.exception.DatabaseException;
import br.com.quattys.manager.business.service.exception.EntityNotFoundException;
import br.com.quattys.manager.resource.repository.LocaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LocaleServicePostgresql implements LocaleService {

    private LocaleMapper localeMapper;
    private LocaleRepository localeRepository;

    @Override
    public LocaleResponse saveLocale(LocaleRequest localeRequest) {
        try{
            Locale entity = localeMapper.convertDtoToEntity(localeRequest);
            entity = localeRepository.save(entity);
            return localeMapper.convertEntityToDto(entity);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("[MANAGER - LOCALE] - ERROR TO SAVE LOCALE");
        }
    }

    @Override
    public LocaleResponse updateLocale(Long id, LocaleRequest localeRequest) {
        Locale locale = verifyIfExist(id);
        localeMapper.updateLocaleFromRequest(localeRequest, locale);
        locale = localeRepository.save(locale);
        return localeMapper.convertEntityToDto(locale);
    }

    @Override
    public void deleteLocaleById(Long id) {
        Locale locale = verifyIfExist(id);
        localeRepository.delete(locale);
    }

    @Override
    public LocaleResponse getLocaleById(Long id) {
        Locale locale = verifyIfExist(id);
        return localeMapper.convertEntityToDto(locale);
    }

    @Override
    public LocaleTableResponse getLocaleByCity(String city) {
        List<Locale> localeList = localeRepository.findAllByCity(city);
        List<LocaleResponse> responses = localeList.stream()
                .map(localeMapper::convertEntityToDto).collect(Collectors.toList());
        return LocaleTableResponse.builder()
                .localeList(responses)
                .build();
    }

    private Locale verifyIfExist(Long id){
        return localeRepository.findById(id)
                .orElseThrow(() ->  new EntityNotFoundException("Location not registered or not found"));
    }

}
