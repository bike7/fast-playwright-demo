package pl.akademiaqa.modul4;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTests extends TestFixtures {

    @Test
    void shouldLoginWithValidCredentials() {
        //given
        String username = "tomsmith";
        String password = "SuperSecretPassword!";
        String expectedLoginMessage = "You logged into a secure area!";
        String expectedLogoutMessage = "You logged out of the secure area!";

        //when
        page.navigate("https://the-internet.herokuapp.com");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Form Authentication")).click();
        page.getByLabel("Username").fill(username);
        page.getByLabel("Password").fill(password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

        //then
        assertThat(page.locator("#flash-messages")).containsText(expectedLoginMessage);
        //and
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout")).click();
        assertThat(page.locator("#flash-messages")).containsText(expectedLogoutMessage);
    }

    @Test
    void shouldFailLoginWithIncorrectCredentials() {
        //given
        String incorrectUsername = "wronguser";
        String incorrectPassword = "wrongpassword";
        String expectedErrorMessage = "Your username is invalid!";

        //when
        page.navigate("https://the-internet.herokuapp.com");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Form Authentication")).click();
        page.getByLabel("Username").fill(incorrectUsername);
        page.getByLabel("Password").fill(incorrectPassword);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

        //then
        assertThat(page.locator("#flash-messages")).containsText(expectedErrorMessage);
    }

    @Test
    void shouldFailLoginWithEmptyCredentials() {
        //given
        String expectedErrorMessage = "Your username is invalid!";

        //when
        page.navigate("https://the-internet.herokuapp.com");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Form Authentication")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

        //then
        assertThat(page.locator("#flash-messages")).containsText(expectedErrorMessage);
    }
}
