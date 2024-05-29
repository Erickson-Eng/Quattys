package br.com.quattys.backend.application.dto.mapper;

import br.com.quattys.backend.application.dto.request.CreateUserRequest;
import br.com.quattys.backend.application.dto.response.UserResponse;
import br.com.quattys.backend.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    User toDomain(CreateUserRequest createUserRequest);


    UserResponse toResponse(User user);
}
