package pl.akademiaqa.modul8.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

public class ContactUsForm {

    private Page page;
    private Locator sendButton;
    @Getter
    private Locator errorMessage;

    public ContactUsForm(Page page) {
        this.page = page;
        this.sendButton = page.locator("#submitMessage");
        this.errorMessage = page.getByText("Invalid email address.");
    }

    public void clickSendButton() {
        sendButton.click();
    }

}
