package pl.akademiaqa.modul7;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class AdvancedAutomationPractice {

    private Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));

    @Test
    @DisplayName("Should open multiple windows")
    public void shouldOpenMultipleWindows() {
        //Logged in user
        Page page = browser.newContext().newPage();
        page.navigate("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
        page.locator("#email").fill("demo@mailinator.com");
        page.locator("#passwd").fill("test123");
        page.locator("#SubmitLogin").click();

        //Open another window without logging in
        Page page1 = browser.newContext().newPage();
        page1.navigate("http://www.automationpractice.pl/index.php?");
        page.locator("a[title=Women]").click();
    }

    @Test
    @DisplayName("Should save storage state")
    public void shouldSaveStorageState() {
        BrowserContext bc = browser.newContext();
        Page page = bc.newPage();
        page.navigate("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
        page.locator("#email").fill("demo@mailinator.com");
        page.locator("#passwd").fill("test123");
        page.locator("#SubmitLogin").click();
        bc.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("storage/loginData.json")));
    }

    @Test
    @DisplayName("Should load storage state")
    public void shouldLoadStorageState() {
        BrowserContext bc = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("storage/loginData.json")));
        Page page = bc.newPage();
        page.navigate("http://www.automationpractice.pl/index.php?controller=my-account");
        PlaywrightAssertions.assertThat(page.locator("h1[class=page-heading]")).hasText("My account");
    }
}
