package br.com.quattys.manager.application.dto.mapper;

import br.com.quattys.manager.application.dto.request.GymRequest;
import br.com.quattys.manager.application.dto.response.GymResponse;
import br.com.quattys.manager.business.entity.Gym;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PracticableMapper.class})
public interface GymMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    @Mapping(target = "practicableSet", ignore = true)
    Gym convertDtoFromEntity(GymRequest gymRequest);

    GymResponse convertEntityToDto(Gym gym);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    void updateGymInfo(GymRequest gymRequest, @MappingTarget Gym gym);
}
