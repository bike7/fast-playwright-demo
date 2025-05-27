package pl.akademiaqa.modul16;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.akademiaqa.modul16.ApiConstants.OK_STATUS;
import static pl.akademiaqa.modul16.ApiConstants.USER_BY_ID_ENDPOINT;

public class UpdateUserTest extends BaseApiTest {

    @Test
    void shouldUpdateUserUsingPut() throws JsonProcessingException {
        String updatedEmail = "test@email.com";
        User createdUser = createUser(User.getRandomUser());
        createdUser.setEmail(updatedEmail);

        APIResponse updatedUserResponse = context.put(
                String.format(USER_BY_ID_ENDPOINT, createdUser.getId()),
                RequestOptions.create().setData(createdUser)
        );
        User updatedUser = mapper.readValue(updatedUserResponse.text(), User.class);

        assertThat(updatedUserResponse).isOK();
        assertThat(updatedUserResponse.status()).isEqualTo(OK_STATUS);
        assertThat(updatedUser.getEmail()).isEqualTo(updatedEmail);
    }

    @Test
    void shouldUpdateUserUsingPatch() throws JsonProcessingException {
        String updatedEmail = "patched@email.com";
        User user = createUser(User.getRandomUser());
        String patchData = mapper.writeValueAsString(mapper.createObjectNode().put("email", updatedEmail));

        APIResponse patchedUserResponse = context.patch(
                String.format(USER_BY_ID_ENDPOINT, user.getId()),
                RequestOptions.create().setData(patchData)
        );
        User patchedUser = mapper.readValue(patchedUserResponse.text(), User.class);

        assertThat(patchedUserResponse).isOK();
        assertThat(patchedUserResponse.status()).isEqualTo(OK_STATUS);
        assertThat(patchedUser.getEmail()).isEqualTo(updatedEmail);
        assertThat(patchedUser.getName()).isEqualTo(user.getName());
    }
}
