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
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private boolean newsletter;

}
