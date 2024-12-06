package pl.akademiaqa.modul8.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {

    private Page page;
    private Locator contactUsLink;

    public HomePage(Page page) {
        this.page = page;
        this.contactUsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact us"));
    }

    public void clickContactUsLink() {
        contactUsLink.click();
    }
}