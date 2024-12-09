package pl.akademiaqa.modul8.tests;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;
import pl.akademiaqa.modul8.dataTransferObjects.NewAccountDTO;
import pl.akademiaqa.modul8.pages.HomePage;

public class CreateAccountTests extends TestFixtures {


    @Test
    public void shouldCreateNewAccountTest() {
        //given
        Name name = new Faker().name();
        NewAccountDTO newAccount = NewAccountDTO.builder()
                .firstName(name.firstName())
                .lastName(name.lastName())
                .email(name.username() + "@mailinator.com")
                .password("Password123")
                .build();
        String expectedConfirmationMessage = "Your account has been created.";

        //when
        String actualConfirmationMessage = new HomePage(page)
                .navigate()
                .getTopMenuSection()
                .clickSignInLink()
                .getAuthenticationSection()
                .fillEmailField(newAccount.getEmail())
                .clickCreateAnAccount()
                .getCreateAccountSection()
                .fillInForm(newAccount)
                .clickRegisterButton()
                .getMyAccountSection()
                .getMyAccountSuccessAlertText();

        //then
        Assertions.assertEquals(expectedConfirmationMessage, actualConfirmationMessage);
    }
}
