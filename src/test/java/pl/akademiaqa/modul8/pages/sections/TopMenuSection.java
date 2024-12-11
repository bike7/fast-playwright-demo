package pl.akademiaqa.modul8.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pl.akademiaqa.modul8.pages.BasePage;
import pl.akademiaqa.modul8.pages.ContactUsPage;
import pl.akademiaqa.modul8.pages.SignInPage;

public class TopMenuSection extends BasePage {

    private Locator contactUsLink;
    private Locator signInLink;

    public TopMenuSection(Page page) {
        super(page);
        this.contactUsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact us"));
        this.signInLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in"));
    }

    public ContactUsPage clickContactUsLink() {
        contactUsLink.click();
        return new ContactUsPage(page);
    }

    public SignInPage clickSignInLink() {
        signInLink.click();
        return new SignInPage(page);
    }
}
