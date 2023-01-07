package br.com.quattys.sportive.application.dto.mapper;

import br.com.quattys.sportive.application.dto.request.AddressRequest;
import br.com.quattys.sportive.application.dto.response.AddressResponse;
import br.com.quattys.sportive.business.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)

    Address requestToAddress(AddressRequest addressRequest);

    AddressResponse addressToResponse(Address address);

    @Mapping(target = "id", ignore = true)
    void updateAddressFromRequest(AddressRequest addressRequest, @MappingTarget Address address);

}
