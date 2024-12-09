package pl.akademiaqa.modul8.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;
import pl.akademiaqa.modul8.pages.BasePage;
import pl.akademiaqa.modul8.pages.CreateAccountPage;

@Getter
public class AuthenticationSection extends BasePage {

    private Locator emailField;
    private Locator createAnAccountButton;

    public AuthenticationSection(Page page) {
        super(page);
        emailField = page.locator("#email_create");
        createAnAccountButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" Create an account"));
    }

    public AuthenticationSection fillEmailField(String email) {
        emailField.fill(email);
        return this;
    }

    public CreateAccountPage clickCreateAnAccount() {
        createAnAccountButton.click();
        return new CreateAccountPage(page);
    }
}
