package pl.akademiaqa.modul7;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.common.TestFixtures;

import java.nio.file.Path;
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
    public void shouldDownloadFileUsingHandler() {
        page.navigate("https://the-internet.herokuapp.com/download");

        //Handler before click
        page.onDownload(download -> download.saveAs(Paths.get("downloads/downloaded_file.txt")));

        page.getByText("testtesttest.txt").click();
    }

    @Test
    @DisplayName("Download file using save")
    public void shouldDownloadFileUsingSave() {
        page.navigate("https://the-internet.herokuapp.com/download");

        //Click first then save
        page.waitForDownload(() -> page.getByText("testtesttest.txt").click())
                .saveAs(Paths.get("downloads/downloaded_file1.txt"));
    }

    @Test
    @DisplayName("Upload file")
    public void shouldUploadFile() {
        page.navigate("https://the-internet.herokuapp.com/upload");

        page.setInputFiles("#file-upload", Paths.get("uploads/test.txt"));
        page.setInputFiles("#file-upload", new Path[0]);
        page.setInputFiles("#file-upload", Paths.get("uploads/test1.txt"));
        page.locator("#file-submit").click();

        PlaywrightAssertions.assertThat(page.getByText("File Uploaded!")).isVisible();
    }

    @Test
    @DisplayName("Multiple file upload")
    public void shouldUploadMultipleFile() {
        page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");

        page.setInputFiles("#filesToUpload", new Path[]{Paths.get("uploads/test.txt"), Paths.get("uploads/test1.txt")});
    }

    @Test
    @DisplayName("Screenshot of the whole page")
    public void shouldTakeScreenshotOfThePage() {
        page.navigate("https://the-internet.herokuapp.com/");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/home.jpeg")));
    }

    @Test
    @DisplayName("Screenshot of one element")
    public void shouldTakeScreenshotOfElement() {
        page.navigate("https://the-internet.herokuapp.com/login");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        page.locator("#flash-messages").screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("screenshots/login-error.jpeg")));
    }
}
