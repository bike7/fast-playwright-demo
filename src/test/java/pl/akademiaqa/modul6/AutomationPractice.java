package pl.akademiaqa.modul6;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;

import java.util.List;

public class AutomationPractice extends TestFixtures {

    @Test
    @DisplayName("Should contain only dresses with price higher than 15$")
    public void shouldContainExpensiveDresses() {
        //given
        int expectedPriceLowerLimit = 15;
        page.navigate("http://www.automationpractice.pl/index.php?");

        //when
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Dresses").setExact(true)).click();
        List<Locator> allproducts = page.locator(".product-container").all();
        List<Product> dresses = allproducts.stream().map(product -> {
            String name = product.locator(".product-name").innerText();
            int price = Integer.parseInt(product.locator("//span[@itemprop='price']").innerText().replace("$", "").trim());
            return new Product(name, price);
        }).toList();

        //then
        dresses.forEach(dress -> {
            Assertions.assertTrue(dress.getPrice() > expectedPriceLowerLimit, "Dress " + dress.getName() + " is too cheap");
        });
    }

    @Test
    @DisplayName("Should contain only dresses with name containing 'printed'")
    public void shouldContainPrintedDresses() {
        //given
        String expectedNamePart = "printed";
        page.navigate("http://www.automationpractice.pl/index.php?");

        //when
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Dresses").setExact(true)).click();
        List<Locator> allproducts = page.locator(".product-container").all();
        List<Product> dresses = allproducts.stream().map(product -> {
            String name = product.locator(".product-name").innerText();
            int price = Integer.parseInt(product.locator("//span[@itemprop='price']").innerText().replace("$", "").trim());
            return new Product(name, price);
        }).toList();

        //then
        dresses.forEach(dress -> {
            Assertions.assertTrue(dress.getName().toLowerCase().contains(expectedNamePart), "Dress " + dress.getName() + " doesn't contain " + expectedNamePart + " in name");
        });
    }
}
