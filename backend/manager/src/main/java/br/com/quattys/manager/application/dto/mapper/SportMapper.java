package br.com.quattys.manager.application.dto.mapper;

import br.com.quattys.manager.application.dto.request.SportRequest;
import br.com.quattys.manager.application.dto.response.SportResponse;
import br.com.quattys.manager.business.entity.Sport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SportMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    Sport convertDtoToEntity(SportRequest sportRequest);

    SportResponse convertEntityToDto(Sport sport);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    void updateSportInfo(SportRequest sportRequest, @MappingTarget Sport sport);
}
