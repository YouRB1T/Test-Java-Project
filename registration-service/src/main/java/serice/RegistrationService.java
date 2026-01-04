package serice;

import api.*;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    RegistrationUserResponse registrationUser(RegistrationUserRequest request);
    void registrationCounterparty(RegistrationCounterpartyRequest request);
    void registrationEmployee(RegistrationEmployeeRequest request);
    void registrationResident(RegistrationResidentRequest request);
}
