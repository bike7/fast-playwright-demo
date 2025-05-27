package pl.akademiaqa.modul16;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixturesAPI;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DeleteUserTest extends TestFixturesAPI {
    @Test
    public void deleteUser() throws JsonProcessingException {
        User user = User.getRandomUser();
        APIResponse createdUserResponse = context.post("users", RequestOptions.create().setData(user));
        User createdUser = new ObjectMapper().readValue(createdUserResponse.text(), User.class);

        APIResponse response = context.delete("users/" + createdUser.getId());

        assertThat(response).isOK();
        Assertions.assertThat(response.status()).isEqualTo(200);

        APIResponse deletedUserResponse = context.get("users/" + createdUser.getId());
        Assertions.assertThat(deletedUserResponse.status()).isEqualTo(404);
    }
}
