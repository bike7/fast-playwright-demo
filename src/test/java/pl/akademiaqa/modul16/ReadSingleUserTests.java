package pl.akademiaqa.modul16;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixturesAPI;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadSingleUserTests extends TestFixturesAPI {

    @Test
    void shouldReturnSingleUser() throws IOException {
        APIResponse response = context.get("users/2");
        User user = new ObjectMapper().readValue(response.text(), User.class);

        PlaywrightAssertions.assertThat(response).isOK();
        assertThat(user.getId()).isEqualTo(2);
        assertThat(user.getName()).isEqualTo("Ervin Howell");
        assertThat(user.getUsername()).isEqualTo("Antonette");
        assertThat(user.getEmail()).isEqualTo("Shanna@melissa.tv");
        assertThat(user.getAddress().getStreet()).isEqualTo("Victor Plains");
        assertThat(user.getAddress().getCity()).isEqualTo("Wisokyburgh");
        assertThat(user.getCompany().getName()).isEqualTo("Deckow-Crist");
    }
}