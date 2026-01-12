package mapper;

import api.RegistrationUserRequest;
import kafka.payloads.UserPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    UserPayload toPayload(RegistrationUserRequest request);
}
