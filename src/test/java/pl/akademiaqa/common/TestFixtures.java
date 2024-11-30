package pl.akademiaqa.common;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestFixtures {

    protected static Playwright playwright;
    protected BrowserContext context;
    protected static Browser browser;
    protected Page page;

    @BeforeAll
    static void setupEnvironment() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        //browser = playwright.chromium().launch();
    }

    @BeforeEach
    void setupBrowser() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void teardownBrowser() {
        context.close();
    }

    @AfterAll
    static void teardownEnvironment() {
        browser.close();
        playwright.close();
    }
}
