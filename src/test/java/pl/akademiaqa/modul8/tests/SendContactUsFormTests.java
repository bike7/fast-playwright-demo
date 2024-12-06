package pl.akademiaqa.modul8.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;
import pl.akademiaqa.modul8.dataTransferObjects.ContactUsDTO;
import pl.akademiaqa.modul8.pages.HomePage;

public class SendContactUsFormTests extends TestFixtures {
    @Test
    public void shouldShowErrorMessageWhenSendingEmptyForm() {
        //given
        page.navigate("http://www.automationpractice.pl");
        //when
        Locator errorMessage = new HomePage(page)
                .getTopMenuSection()
                .clickContactUsLink()
                .getContactUsFormSection()
                .clickSendMessageButton()
                .getErrorMessage();
        //then
        PlaywrightAssertions.assertThat(errorMessage).isVisible();
    }

    @Test
    public void shouldFillAndSendContactUsForm() {
        //given
        page.navigate("http://www.automationpractice.pl");
        //when
        Locator confirmationMessage = new HomePage(page)
                .getTopMenuSection()
                .clickContactUsLink()
                .getContactUsFormSection()
                .fillInForm(ContactUsDTO.getDefaultContactUsDTO())
                .clickSendMessageButton()
                .getConfirmationMessage();
        //then
        PlaywrightAssertions.assertThat(confirmationMessage).isVisible();
    }
}
