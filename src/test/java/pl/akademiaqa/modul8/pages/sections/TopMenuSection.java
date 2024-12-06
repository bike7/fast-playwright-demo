package pl.akademiaqa.modul8.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class TopMenuSection {

    private Page page;
    private Locator contactUsLink;

    public TopMenuSection(Page page) {
        this.page = page;
        this.contactUsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact us"));
    }

    public void clickContactUsLink() {
        contactUsLink.click();
    }
}
