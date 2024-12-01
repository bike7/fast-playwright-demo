package pl.akademiaqa.modul6;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.KeyboardModifier;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.WaitUntilState;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PageOptions extends TestFixtures {

    //Assertions documentation: https://playwright.dev/java/docs/test-assertions
    //Keyboard keys documentation: https://playwright.dev/java/docs/api/class-keyboard
    //Mouse documentation: https://playwright.dev/java/docs/api/class-mouse
    //Playwright debug mode environment variable: PWDEBUG=1

    @Test
    @DisplayName("Navigation options")
    void navigateOptions() {
        page.navigate("https://www.wikipedia.org", new Page.NavigateOptions().setTimeout(10000)); //default set to 30000 (30sek)
        page.navigate("https://www.wikipedia.org", new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED)); //DOM is loaded, no css, styles, images, etc
        page.navigate("https://www.wikipedia.org", new Page.NavigateOptions().setWaitUntil(WaitUntilState.LOAD)); //all resources are loaded (default)
        page.navigate("https://www.wikipedia.org", new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE)); //all resources are loaded and no new network calls for 500ms
        page.navigate("https://www.wikipedia.org", new Page.NavigateOptions().setReferer("https://www.google.com")); //default set to null, sets redirection from referer
    }

    @Test
    @DisplayName("Other navigation options")
    void otherNavigationOptions() {
        page.navigate("https://www.wikipedia.org");
        page.navigate("https://www.google.com");
        page.reload(new Page.ReloadOptions().setWaitUntil(WaitUntilState.LOAD)); //ReloadOptions can set WaitUntil or Timeout
        page.goBack(new Page.GoBackOptions().setTimeout(60000)); //GoBackOptions can set WaitUntil or Timeout
        page.goForward(new Page.GoForwardOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED)); //GoForwardOptions can set WaitUntil or Timeout
    }

    @Test
    @DisplayName("Click options")
    @Disabled
    void clickOptions() {
        page.click("", new Page.ClickOptions().setClickCount(10)); //default set to 1, how many times to click
        page.click("", new Page.ClickOptions().setButton(MouseButton.RIGHT));
        page.click("", new Page.ClickOptions().setModifiers(List.of(KeyboardModifier.ALT, KeyboardModifier.SHIFT))); //not all keyboard buttons supported
        page.press("", "Delete", new Page.PressOptions().setDelay(1000)); //supports keys: Backquote, Minus, Equal, Backslash, Backspace, Tab, Delete, Escape,ArrowDown, End, Enter, Home, Insert, PageDown, PageUp, ArrowRight,ArrowUp, F1 - F12, Digit0 - Digit9, KeyA - KeyZ, etc.
        page.dblclick(""); //double click
    }

    @Test
    @DisplayName("Checkboxes options")
    void checkboxesOptions() {
        page.navigate("https://the-internet.herokuapp.com/checkboxes");

        Locator firstCheckbox = (page.getByRole(AriaRole.CHECKBOX).first());
        assertThat(firstCheckbox).not().isChecked();
        firstCheckbox.check();
        assertThat(firstCheckbox).isChecked();

        Locator lastCheckbox = (page.getByRole(AriaRole.CHECKBOX).last());
        assertThat(lastCheckbox).isChecked();
        lastCheckbox.uncheck();
        assertThat(lastCheckbox).not().isChecked();
    }

    @Test
    @DisplayName("Input options")
    @Disabled
    void inputOptions() {
        page.fill("", "Hello"); //fills input with text
        page.fill("", "Hello", new Page.FillOptions().setForce(true)); //allows to fill in input obstructed by other elements
        page.locator("").pressSequentially("Hello, World!", new Locator.PressSequentiallyOptions().setDelay(1000)); //presses keys one by one with delay
    }

    @Test
    @DisplayName("Text options")
    void textOptions() {
        page.navigate("https://the-internet.herokuapp.com/notification_message_rendered");
        System.out.println(page.innerHTML("#content")); //returns innerHTML of the element including HTML tags
        System.out.println(page.innerText("#content"));//returns text of the element without HTML tags, text visible to user
        System.out.println(page.textContent("#content")); //returns text of the element without HTML tags, also text invisible to user
        System.out.println(page.getAttribute("div img", "alt")); //returns value of the attribute, in this case "alt" attribute of the image
    }

    @Test
    @DisplayName("Select options")
    void selectOptions() {
        page.navigate("https://the-internet.herokuapp.com/dropdown");
        page.selectOption("#dropdown", "Option 1"); //selects option by text
        page.selectOption("#dropdown", "1"); //selects option by value attribute
    }

    @Test
    @DisplayName("Multiple select options")
    void multipleSelectOptions() {
        page.navigate("https://testpages.herokuapp.com/styled/basic-html-form-test.html");
        page.getByRole(AriaRole.LISTBOX).selectOption(new String[]{"ms2", "ms3"}); //selects multiple options
    }

    @Test
    @DisplayName("Radio button options")
    void radioButtonOptions() {
        page.navigate("https://testpages.herokuapp.com/styled/basic-html-form-test.html");
        Locator secondRadioButton = page.getByRole(AriaRole.RADIO).nth(1);
        Locator thirdRadioButton = page.getByRole(AriaRole.RADIO).nth(2);

        assertThat(secondRadioButton).isChecked();
        assertThat(thirdRadioButton).not().isChecked();

        thirdRadioButton.check();

        assertThat(secondRadioButton).not().isChecked();
        assertThat(thirdRadioButton).isChecked();

    }

    @Test
    @DisplayName("Dynamic loading options example 1")
    void dynamicLoadingOptionsExample1() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_loading/1");
        Locator text = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Hello World!"));
        assertThat(text).not().isVisible();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Start")).click();
        //No need to wait for the element to appear, Playwright will wait for it to appear
        assertThat(text).isVisible();
    }

    @Test
    @DisplayName("Dynamic loading options example 2")
    void dynamicLoadingOptionsExample2() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_loading/2");
        Locator text = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Hello World!"));
        assertThat(text).not().isVisible();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Start")).click();
        //No need to wait for the element to appear, Playwright will wait for it to appear
        assertThat(text).isVisible();
    }

    @Test
    @DisplayName("Dynamic controls options example 1")
    void dynamicControlsOptionsExample1() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_controls");
        Locator checkbox = page.locator("#checkbox");
        assertThat(checkbox).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove")).click();
        //No need to wait for the element to disappear, Playwright will automatically wait for it to disappear
        assertThat(checkbox).not().isVisible();
    }

    @Test
    @DisplayName("Dynamic controls options example 2")
    void dynamicControlsOptionsExample2() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_controls");
        Locator input = page.locator("#input-example input[type='text']");
        assertThat(input).isDisabled();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Enable")).click();
        //No need to wait for the element to appear, Playwright will automatically wait for it to appear
        assertThat(input).isEnabled();
    }

    @Test
    @DisplayName("Key (keyboard) press options)")
    void keyPressOptions() {
        page.navigate("https://the-internet.herokuapp.com/key_presses");
        page.press("#target", "ArrowUp");
        //or
        //page.locator("#target").click();
        //page.keyboard().press("ArrowUp");
        assertThat(page.getByText("You entered: UP")).isVisible();
    }

    @Test
    @DisplayName("Mouse options")
    void mouseOptions() {
        page.navigate("https://the-internet.herokuapp.com/context_menu");
        page.mouse().click(250, 250, new Mouse.ClickOptions().setButton(MouseButton.RIGHT));
    }

    @Test
    @DisplayName("Drag and drop options")
    void dragAndDropOptions() {
        page.navigate("https://the-internet.herokuapp.com/drag_and_drop");
        Locator source = page.locator("#column-a");
        Locator target = page.locator("#column-b");
        source.dragTo(target);
    }

    @Test
    @DisplayName("Tables options")
    void tablesOptions() {
        page.navigate("https://the-internet.herokuapp.com/tables");

    }
}

