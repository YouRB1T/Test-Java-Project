package mapper;

import api.RegistrationCounterpartyRequest;
import kafka.payloads.CounterpartyPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CounterpartyMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "email", source = "email")
    CounterpartyPayload toPayload(RegistrationCounterpartyRequest request);
}
