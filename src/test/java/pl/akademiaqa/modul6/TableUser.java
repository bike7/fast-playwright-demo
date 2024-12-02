package pl.akademiaqa.modul6;

import com.microsoft.playwright.Locator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class TableUser {

    private String firstName;
    private String lastName;
    private String email;
    private String due;
    private String website;
    private Locator action;
}
