package pl.akademiaqa.common;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Paths;

public class TestFixtures {

    protected static Playwright playwright;
    protected BrowserContext context;
    protected static Browser browser;
    protected Page page;

    @BeforeAll
    static void setupEnvironment() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
    }

    @BeforeEach
    void setupBrowser() {
        /* video setup
        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1920, 1080)
                        .setRecordVideoDir(Paths.get("videos/"))
                        .setRecordVideoSize(new RecordVideoSize(1920, 1080))
        );
        */
        context = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));
        //context = browser.newContext();
        //start tracing
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
        );
        page = context.newPage();
    }

    @AfterEach
    void teardownBrowser() {
        //stop tracing
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("tracing/trace.zip"))); //open in https://trace.playwright.dev
        context.close();
    }

    @AfterAll
    static void teardownEnvironment() {
        browser.close();
        playwright.close();
    }
}
