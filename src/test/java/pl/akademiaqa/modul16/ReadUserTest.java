package pl.akademiaqa.modul16;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixturesAPI;

import java.io.IOException;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;


public class ReadUserTest extends TestFixturesAPI {
    @Test
    void shouldReadASingleUser() throws IOException {
        APIResponse response = context.get("users/2");
        User user = new ObjectMapper().readValue(response.text(), User.class);

        assertThat(response).isOK();
        assertThat(user.getId()).isEqualTo(2);
        assertThat(user.getName()).isEqualTo("Ervin Howell");
        assertThat(user.getUsername()).isEqualTo("Antonette");
        assertThat(user.getEmail()).isEqualTo("Shanna@melissa.tv");
        assertThat(user.getAddress().getStreet()).isEqualTo("Victor Plains");
        assertThat(user.getAddress().getCity()).isEqualTo("Wisokyburgh");
        assertThat(user.getCompany().getName()).isEqualTo("Deckow-Crist");
    }

    @Test
    void shouldReadAListOfAllUsers() throws JsonProcessingException {
        APIResponse response = context.get("users");
        List<User> users = new ObjectMapper().readValue(response.text(), new TypeReference<List<User>>() {
        });

        assertThat(response).isOK();
        assertThat(users).isNotEmpty();

        User user2 = users.stream()
                .filter(user -> user.getId() == 2)
                .findFirst()
                .orElse(null);

        assertThat(user2).isNotNull();
        assertThat(user2.getName()).isEqualTo("Ervin Howell");
        assertThat(user2.getUsername()).isEqualTo("Antonette");
    }
}
