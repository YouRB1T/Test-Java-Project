package api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationCounterpartyRequest {
    private String userName;
    private String email;
    private String password;

    private String name;
    private String taxId;
    private String RRC;
    private String PSRN;
    private String address;
    private String phone;
    private String contactPerson;
}
