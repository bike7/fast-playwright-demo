package pl.akademiaqa.modul16;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixturesAPI;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class UpdateUserTest extends TestFixturesAPI {

    @Test
    void shouldUpdateUserUsingPut() throws JsonProcessingException {
        String updatedEmail = "test@email.com";
        User user = User.getRandomUser();
        APIResponse response = context.post("users", RequestOptions.create().setData(user));
        User createdUser = new ObjectMapper().readValue(response.text(), User.class);
        user.setEmail(updatedEmail);

        APIResponse updatedUserResponse = context.put("users/" + createdUser.getId(), RequestOptions.create().setData(user));
        User updatedUser = new ObjectMapper().readValue(updatedUserResponse.text(), User.class);

        assertThat(updatedUserResponse).isOK();
        assertThat(updatedUserResponse.status()).isEqualTo(200);
        assertThat(updatedUser.getEmail()).isEqualTo(updatedEmail);
    }

    @Test
    void shouldUpdateUserUsingPatch() throws JsonProcessingException {
        String updatedEmail = "patched@email.com";
        ObjectMapper mapper = new ObjectMapper();
        String patchData = mapper.writeValueAsString(mapper.createObjectNode().put("email", updatedEmail));
        User user = User.getRandomUser();
        APIResponse response = context.post("users", RequestOptions.create().setData(user));
        User createdUser = new ObjectMapper().readValue(response.text(), User.class);

        APIResponse patchedUserResponse = context.patch("users/" + createdUser.getId(), RequestOptions.create().setData(patchData));
        User patchedUser = mapper.readValue(patchedUserResponse.text(), User.class);

        assertThat(patchedUserResponse).isOK();
        assertThat(patchedUserResponse.status()).isEqualTo(200);
        assertThat(patchedUser.getEmail()).isEqualTo(updatedEmail);
    }
}
