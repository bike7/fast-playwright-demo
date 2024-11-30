package pl.akademiaqa.modul3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;

class FirstTest extends TestFixtures {

    @Test()
    @DisplayName("Navigate to Wikipedia and check the page title")
    void shouldNavigateToWikipediaAndCheckTitle() {
        page.navigate("https://www.wikipedia.org");
        Assertions.assertEquals("Wikipedia", page.title());
    }

}
