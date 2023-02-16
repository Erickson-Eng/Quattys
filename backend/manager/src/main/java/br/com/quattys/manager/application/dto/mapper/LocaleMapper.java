package br.com.quattys.manager.application.dto.mapper;

import br.com.quattys.manager.application.dto.request.LocaleRequest;
import br.com.quattys.manager.application.dto.response.LocaleResponse;
import br.com.quattys.manager.business.entity.Locale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LocaleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    Locale convertDtoToEntity(LocaleRequest localeRequest);
    LocaleResponse convertEntityToDto(Locale locale);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    void updateLocaleFromRequest(LocaleRequest localeRequest, @MappingTarget Locale locale);
}
