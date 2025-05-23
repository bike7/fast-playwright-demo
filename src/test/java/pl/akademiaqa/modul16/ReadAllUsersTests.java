package pl.akademiaqa.modul16;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixturesAPI;

public class ReadAllUsersTests extends TestFixturesAPI {

    @Test
    void shouldReturnAListOfUsers() {
        APIResponse response = context.get("users");
        PlaywrightAssertions.assertThat(response).isOK();
    }
}
