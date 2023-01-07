package br.com.quattys.sportive.application.dto.mapper;


import br.com.quattys.sportive.application.dto.request.AthleteRequest;
import br.com.quattys.sportive.application.dto.response.AthleteResponse;
import br.com.quattys.sportive.business.entity.Athlete;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface AthleteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "address.id", source = "addressId")
    Athlete requestToAthlete(AthleteRequest athleteRequest);

    @Mapping(target = "addressResponse", source = "address")
    AthleteResponse athleteToResponse(Athlete athlete);

    @Mapping(target = "id", ignore = true)
    void updateAthleteRequest(AthleteRequest athleteRequest, @MappingTarget Athlete athlete);

}
