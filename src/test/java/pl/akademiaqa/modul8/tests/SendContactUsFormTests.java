package pl.akademiaqa.modul8.tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;
import pl.akademiaqa.modul8.pages.ContactUsPage;
import pl.akademiaqa.modul8.pages.HomePage;

public class SendContactUsFormTests extends TestFixtures {
    @Test
    public void shouldShowErrorMessageWhenSendingEmptyForm() {
        //given
        page.navigate("http://www.automationpractice.pl");
        HomePage homePage = new HomePage(page);
        ContactUsPage contactUsPage = new ContactUsPage(page);

        //when
        homePage.getTopMenuSection().clickContactUsLink();
        contactUsPage.getContactUsForm().clickSendButton();

        //then
        PlaywrightAssertions.assertThat(contactUsPage.getContactUsForm().getErrorMessage()).isVisible();
    }
}
