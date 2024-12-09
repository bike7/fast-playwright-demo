package pl.akademiaqa.modul8.tests;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;
import pl.akademiaqa.modul8.dataTransferObjects.NewAccountDTO;
import pl.akademiaqa.modul8.pages.HomePage;

public class CreateAccountTests extends TestFixtures {


    @Test
    public void shouldCreateNewAccountTest() {
        //given
        Faker faker = new Faker();
        NewAccountDTO newAccountData = NewAccountDTO.builder()
                .isMale(faker.bool().bool())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.name().username() + "@mailinator.com")
                .password(faker.internet().password(5, 10, false, false, true))
                .dayOfBirth(faker.number().numberBetween(1, 28))
                .monthOfBirth(faker.number().numberBetween(1, 12))
                .yearOfBirth(faker.number().numberBetween(1985, 2000))
                .newsletter(faker.bool().bool())
                .build();
        String expectedConfirmationMessage = "Your account has been created.";

        //when
        Locator actualConfirmationMessage = new HomePage(page)
                .navigate()
                .getTopMenuSection()
                .clickSignInLink()
                .getAuthenticationSection()
                .fillEmailField(newAccountData.getEmail())
                .clickCreateAnAccount()
                .getCreateAccountSection()
                .fillInForm(newAccountData)
                .clickRegisterButton()
                .getMyAccountSection()
                .getMyAccountSuccessAlert();

        //then
        PlaywrightAssertions.assertThat(actualConfirmationMessage).hasText(expectedConfirmationMessage);
    }
}
