package pl.akademiaqa.modul7;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;

import java.nio.file.Paths;

public class AdvancedPageOptions extends TestFixtures {

    @Test
    @DisplayName("Basic iframe handling. Should get text from an iframe")
    public void shouldGetTextFromAnIframe() {
        page.navigate("https://the-internet.herokuapp.com/iframe");
        PlaywrightAssertions.assertThat(page.frameLocator("#mce_0_ifr").locator("#tinymce")).hasText("Your content goes here."); //If iframe doesn't have the name you should use page.frameLocator()
    }

    @Test
    @DisplayName("Nested iframes handling. Should get text from nested frames")
    public void shouldGetTextFromNestedIframe() {
        page.navigate("https://the-internet.herokuapp.com/nested_frames");
        PlaywrightAssertions.assertThat(page.frame("frame-middle").locator("#content")).hasText("MIDDLE"); //If iframe has the name you should use page.frame()
    }

    @Test
    @DisplayName("Basic shadow DOM handling. Should get text from shadow DOM")
    public void shouldGetTextFromShadowDOM() {
        page.navigate("https://the-internet.herokuapp.com/shadowdom");
        PlaywrightAssertions.assertThat(page.locator("span[slot=my-text]")).hasText("Let's have some different text!"); //Can only access open shadow DOM
    }

    @Test
    @DisplayName("Clicks Confirm/OK button in alert (only one button available)")
    public void shouldHandleAlert() {
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

        //Dialog handler before click. It is not necessary. Playwright will ACCEPT this type of dialog by default.
        page.onDialog(dialog -> {
            page.waitForTimeout(3000);
            dialog.accept();
        });

        page.locator("text=Click for JS Alert").click();
        PlaywrightAssertions.assertThat(page.locator("#result")).hasText("You successfully clicked an alert");
    }

    @Test
    @DisplayName("Clicks both buttons in dialog (Confirm and Cancel available)")
    public void shouldHandleDialog() {
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

        //For two buttons dialog you need to use page.onceDialog() method instead of page.onDialog()
        //Dialog handler before click. It is not necessary. Playwright will CANCEL this type of dialog by default.
        page.onceDialog(dialog -> {
            page.waitForTimeout(3000);
            dialog.accept();
        });

        page.locator("text=Click for JS Confirm").click();
        PlaywrightAssertions.assertThat(page.locator("#result")).hasText("You clicked: Ok");

        //Dialog handler before click. It is not necessary. Playwright will CANCEL this type of dialog by default.
        page.onceDialog(dialog -> {
            page.waitForTimeout(3000);
            dialog.dismiss();
        });

        page.locator("text=Click for JS Confirm").click();
        PlaywrightAssertions.assertThat(page.locator("#result")).hasText("You clicked: Cancel");
    }

    @Test
    @DisplayName("Inserts text into prompt dialog and accepts it, then dismiss it")
    public void shouldHandlePromptDialog() {
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

        //Dialog handler before click. It is not necessary. Playwright will CANCEL this type of dialog by default.
        page.onceDialog(dialog -> {
            page.waitForTimeout(3000);
            dialog.accept("123");
        });

        page.locator("text=Click for JS Prompt").click();
        PlaywrightAssertions.assertThat(page.locator("#result")).hasText("You entered: 123");

        //Cancelled by default
        page.locator("text=Click for JS Prompt").click();
        PlaywrightAssertions.assertThat(page.locator("#result")).hasText("You entered: null");
    }

    @Test
    @DisplayName("Download file using handler")
    public void shoulddownloadFileUsingHandler() {
        page.navigate("https://the-internet.herokuapp.com/download");

        //Handler before click
        page.onDownload(download -> download.saveAs(Paths.get("downloads/downloaded_file.txt")));

        page.getByText("testtesttest.txt").click();
    }

    @Test
    @DisplayName("Download file using save")
    public void shoulddownloadFileUsingSave() {
        page.navigate("https://the-internet.herokuapp.com/download");

        //Click first then save
        //Click first then save
        page.waitForDownload(() -> page.getByText("testtesttest.txt").click())
                .saveAs(Paths.get("downloads/downloaded_file1.txt"));
    }

}
