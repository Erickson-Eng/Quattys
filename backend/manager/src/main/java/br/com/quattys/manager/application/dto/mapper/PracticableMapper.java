package br.com.quattys.manager.application.dto.mapper;

import br.com.quattys.manager.application.dto.request.PracticableRequest;
import br.com.quattys.manager.application.dto.response.PracticableResponse;
import br.com.quattys.manager.business.entity.Practicable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PracticableMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sport.id", source = "sportId")
    @Mapping(target = "gym.id", source = "gymId")
    @Mapping(target = "externalId", ignore = true)
    Practicable convertDtoToEntity(PracticableRequest request);


    @Mapping(target = "sportId", source = "sport.id")
    @Mapping(target = "gymId", source = "gym.id")
    @Mapping(target = "sportName", source = "sport.name")
    PracticableResponse convertEntityToDTO(Practicable practicable);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    @Mapping(target = "gym", ignore = true)
    @Mapping(target = "sport", ignore = true)
    void updatePracticableInfo(PracticableRequest request, @MappingTarget Practicable p);


}
