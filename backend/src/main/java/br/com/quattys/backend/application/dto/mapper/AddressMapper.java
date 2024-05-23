package br.com.quattys.backend.application.dto.mapper;

import br.com.quattys.backend.application.dto.request.AddressRequest;
import br.com.quattys.backend.domain.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    Address toDomain(AddressRequest addressRequest);
}
