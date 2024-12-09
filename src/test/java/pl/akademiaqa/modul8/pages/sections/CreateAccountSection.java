package pl.akademiaqa.modul8.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;
import pl.akademiaqa.modul8.dataTransferObjects.NewAccountDTO;
import pl.akademiaqa.modul8.pages.BasePage;
import pl.akademiaqa.modul8.pages.MyAccountPage;

@Getter
public class CreateAccountSection extends BasePage {
    private Locator firstNameField;
    private Locator lastNameField;
    private Locator passwordField;
    private Locator registerButton;

    public CreateAccountSection(Page page) {
        super(page);
        firstNameField = page.getByLabel("First name *");
        lastNameField = page.getByLabel("Last name *");
        passwordField = page.getByLabel("Password *");
        registerButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register "));
    }

    public CreateAccountSection fillInForm(NewAccountDTO newAccountDTO) {
        fillInFirstNameField(newAccountDTO.getFirstName());
        fillInLastNameField(newAccountDTO.getLastName());
        fillInPasswordField(newAccountDTO.getPassword());
        return this;
    }

    public MyAccountPage clickRegisterButton() {
        registerButton.click();
        return new MyAccountPage(page);
    }

    private void fillInFirstNameField(String firstName) {
        firstNameField.fill(firstName);
    }

    private void fillInLastNameField(String lastName) {
        lastNameField.fill(lastName);
    }

    private void fillInPasswordField(String password) {
        passwordField.fill(password);
    }
}
