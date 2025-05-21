package pl.akademiaqa.modul13;

import com.microsoft.playwright.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class RequestResponseTests extends TestFixtures {

    @DisplayName("Should return status code 200")
    @Test
    void shouldReturnStatusCode200() {
        Response response = page.navigate("https://skleptestera.pl/index.php");
        assertThat(response.ok()).isTrue();
    }

    @DisplayName("Should return request method")
    @Test
    void shouldReturnRequestMethod() {
        Response response = page.navigate("https://skleptestera.pl/index.php");
        assertThat(response.request().method()).isEqualTo("GET");
    }
    //http handler test

    @DisplayName("Should use http handlers")
    @Test
    void shouldUseHTTPHandlers() {
        page.onRequest(r -> assertThat(r.method()).isEqualTo("GET"));
        page.onResponse(r -> assertThat(r.ok()).isTrue());
        page.navigate("https://skleptestera.pl/index.php");
    }

    @DisplayName("Should detect redirects in response")
    @Test
    void shouldReturnRedirects() {
        List<Integer> statusCodes = new ArrayList<>();
        page.onResponse(r -> statusCodes.add(r.status()));
        page.navigate("https://skleptestera.pl");

        assertThat(statusCodes.stream().allMatch(statusCode -> statusCode == 200)).isFalse();
        assertThat(statusCodes).contains(302);
    }

    @DisplayName("Should return only 200 status codes for direct URL")
    @Test
    void shouldReturnOnly200() {
        List<Integer> statusCodes = new ArrayList<>();
        page.onResponse(r -> statusCodes.add(r.status()));
        page.navigate("https://skleptestera.pl/index.php");

        assertThat(statusCodes.stream().allMatch(statusCode -> statusCode == 200)).isTrue();
    }

}
