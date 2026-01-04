package mapper;

import api.RegistrationEmployeeRequest;
import kafka.payloads.EmployeePayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "email", source = "email")
    EmployeePayload toPayload(RegistrationEmployeeRequest request);
}
