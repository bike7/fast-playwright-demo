package pl.akademiaqa.common;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFixturesAPI {
    private Playwright playwright;
    private APIRequest request;
    protected APIRequestContext context;

    @BeforeEach
    void setup() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        playwright = Playwright.create();
        request = playwright.request();
        context = request.newContext(new APIRequest.NewContextOptions()
                //.setBaseURL("https://jsonplaceholder.typicode.com/") //Create and update tests will work only on localhost
                .setBaseURL("http://localhost:3000/")
                .setExtraHTTPHeaders(headers));
    }

    @AfterEach
    void teardown() {
        context.dispose();
        playwright.close();
    }
}
