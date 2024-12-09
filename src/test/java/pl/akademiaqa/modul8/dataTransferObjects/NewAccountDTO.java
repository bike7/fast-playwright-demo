package pl.akademiaqa.modul8.dataTransferObjects;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewAccountDTO {
    private boolean isMale;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private boolean newsletter;

}
