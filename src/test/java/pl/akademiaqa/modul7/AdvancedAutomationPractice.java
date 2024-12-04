package pl.akademiaqa.modul7;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AdvancedAutomationPractice {

    private Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));

    @Test
    @DisplayName("Should open multiple windows")
    public void shouldOpenMultipleWindows() {
        //Logged in user
        Page page = browser.newContext().newPage();
        page.navigate("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");
        page.locator("#email").fill("demouser@akademia.pl");
        page.locator("#passwd").fill("123456");
        page.locator("#SubmitLogin").click();

        //Open another window without logging in
        Page page1 = browser.newContext().newPage();
        page1.navigate("http://www.automationpractice.pl/index.php?");
        page.locator("a[title=Women]").click();
    }
}
