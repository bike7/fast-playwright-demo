package pl.akademiaqa.modul16;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixturesAPI;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserTest extends TestFixturesAPI {

    @Test
    void shouldCreateUser() {
        User user = User.getRandomUser();

        APIResponse response = context.post("users", RequestOptions.create().setData(user));

        assertThat(response).isOK();
        assertThat(response.status()).isEqualTo(201);
    }
}
