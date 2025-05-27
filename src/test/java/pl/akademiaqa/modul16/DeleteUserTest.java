package pl.akademiaqa.modul16;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.playwright.APIResponse;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.akademiaqa.modul16.ApiConstants.*;

public class DeleteUserTest extends BaseApiTest {
    @Test
    public void deleteUser() throws JsonProcessingException {
        User user = createUser(User.getRandomUser());

        APIResponse deleteResponse = context.delete(String.format(USER_BY_ID_ENDPOINT, user.getId()));

        assertThat(deleteResponse).isOK();
        assertThat(deleteResponse.status()).isEqualTo(OK_STATUS);

        APIResponse getResponse = context.get(String.format(USER_BY_ID_ENDPOINT, user.getId()));
        assertThat(getResponse.status()).isEqualTo(NOT_FOUND_STATUS);
    }
}
