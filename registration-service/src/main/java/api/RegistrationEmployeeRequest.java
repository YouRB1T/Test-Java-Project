package api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RegistrationEmployeeRequest {
    private String userName;
    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private String middleName;
    private Integer experienceYears;
    private UUID officeId;
    private String phone;
    // need email too
    private LocalDate hireDate;
}
