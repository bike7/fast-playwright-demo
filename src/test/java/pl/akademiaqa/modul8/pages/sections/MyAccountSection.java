package pl.akademiaqa.modul8.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pl.akademiaqa.modul8.pages.BasePage;

public class MyAccountSection extends BasePage {
    private Locator myAccountSuccessAlert;

    public MyAccountSection(Page page) {
        super(page);
        this.myAccountSuccessAlert = page.locator("//p[contains(@class,'alert-success')]");
    }

    public String getMyAccountSuccessAlertText() {
        return myAccountSuccessAlert.innerText().trim();
    }
}
