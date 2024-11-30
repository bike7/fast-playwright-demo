package pl.akademiaqa.modul3;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;

import java.nio.file.Paths;

public class AllBrowsersTests extends TestFixtures {

    @Test
    @DisplayName("Open Chromium browser")
    void shouldOpenSupportedBrowsersChromium() {
        page.navigate("https://www.whatismybrowser.com/");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/chromium.png")));
    }

    @Test
    @DisplayName("Open Firefox browser")
    void shouldOpenSupportedBrowsersFirefox() {
        Page page = playwright.firefox().launch().newContext().newPage();
        page.navigate("https://www.whatismybrowser.com/");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/firefox.png")));
    }

    @Test
    @DisplayName("Open WebKit browser")
    void shouldOpenSupportedBrowsersWebkit() {
        Page page = playwright.webkit().launch().newContext().newPage();
        page.navigate("https://www.whatismybrowser.com/");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/webkit.png")));
    }
}