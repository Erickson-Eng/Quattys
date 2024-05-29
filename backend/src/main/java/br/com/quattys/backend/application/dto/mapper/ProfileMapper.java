package br.com.quattys.backend.application.dto.mapper;

import br.com.quattys.backend.application.dto.request.ProfileRequest;
import br.com.quattys.backend.application.dto.response.ProfileResponse;
import br.com.quattys.backend.domain.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface ProfileMapper {


    @Mapping(target = "birthday", source = "birthday")
    @Mapping(target = "homeAddress", source = "homeAddress")
    @Mapping(target = "id", ignore = true)
    Profile toDomain(ProfileRequest profileRequest);

    @Mapping(target = "address", source = "homeAddress")
    ProfileResponse toResponse(Profile profile);

}
