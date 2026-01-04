package mapper;

import api.RegistrationResidentRequest;
import kafka.payloads.ResidentPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResidentMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "email", source = "email")
    ResidentPayload toPayload(RegistrationResidentRequest request);
}
