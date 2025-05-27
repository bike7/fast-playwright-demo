package pl.akademiaqa.modul16;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.playwright.APIResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.akademiaqa.modul16.ApiConstants.*;


public class ReadUserTest extends BaseApiTest {
    @Test
    void shouldReadASingleUser() throws IOException {
        int userId = 2;

        APIResponse response = context.get(String.format(USER_BY_ID_ENDPOINT, userId));
        User user = mapper.readValue(response.text(), User.class);

        assertThat(response).isOK();
        assertThat(response.status()).isEqualTo(OK_STATUS);
        assertThat(user.getId()).isEqualTo(userId);
        assertThat(user.getName()).isEqualTo("Ervin Howell");
        assertThat(user.getUsername()).isEqualTo("Antonette");
        assertThat(user.getEmail()).isEqualTo("Shanna@melissa.tv");
        assertThat(user.getAddress().getStreet()).isEqualTo("Victor Plains");
        assertThat(user.getAddress().getCity()).isEqualTo("Wisokyburgh");
        assertThat(user.getCompany().getName()).isEqualTo("Deckow-Crist");
    }

    @Test
    void shouldReadAListOfAllUsers() throws JsonProcessingException {
        APIResponse response = context.get(USERS_ENDPOINT);
        List<User> users = mapper.readValue(response.text(), new TypeReference<List<User>>() {
        });

        assertThat(response).isOK();
        assertThat(response.status()).isEqualTo(OK_STATUS);
        assertThat(users).isNotEmpty();

        User user2 = users.stream()
                .filter(user -> user.getId() == 2)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User not found"));

        assertThat(user2).isNotNull();
        assertThat(user2.getName()).isEqualTo("Ervin Howell");
        assertThat(user2.getUsername()).isEqualTo("Antonette");
    }
}
