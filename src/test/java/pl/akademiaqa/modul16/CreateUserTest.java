package pl.akademiaqa.modul16;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.akademiaqa.modul16.ApiConstants.CREATED_STATUS;
import static pl.akademiaqa.modul16.ApiConstants.USERS_ENDPOINT;

public class CreateUserTest extends BaseApiTest {

    @Test
    void shouldCreateUser() throws JsonProcessingException {
        User user = User.getRandomUser();

        APIResponse response = context.post(USERS_ENDPOINT, RequestOptions.create().setData(user));
        User createdUser = mapper.readValue(response.text(), User.class);

        assertThat(response).isOK();
        assertThat(response.status()).isEqualTo(CREATED_STATUS);
        assertThat(createdUser.getName()).isEqualTo(user.getName());
        assertThat(createdUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(createdUser.getId()).isPositive();
    }
}
