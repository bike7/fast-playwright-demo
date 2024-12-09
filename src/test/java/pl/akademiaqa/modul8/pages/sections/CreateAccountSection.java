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
    private Locator mrsRadioButton;
    private Locator mrRadioButton;
    private Locator firstNameField;
    private Locator lastNameField;
    private Locator passwordField;
    private Locator dayOfBirthField;
    private Locator monthOfBirthField;
    private Locator yearOfBirthField;
    private Locator newsletterCheckbox;
    private Locator registerButton;

    public CreateAccountSection(Page page) {
        super(page);
        mrRadioButton = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Mr."));
        mrsRadioButton = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Mrs."));
        firstNameField = page.getByLabel("First name *");
        lastNameField = page.getByLabel("Last name *");
        passwordField = page.getByLabel("Password *");
        dayOfBirthField = page.locator("#days");
        monthOfBirthField = page.locator("#months");
        yearOfBirthField = page.locator("#years");
        newsletterCheckbox = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Sign up for our newsletter!"));
        registerButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register "));
    }

    public CreateAccountSection fillInForm(NewAccountDTO newAccountDTO) {
        checkGender(newAccountDTO.isMale());
        insertFirstName(newAccountDTO.getFirstName());
        insertLastName(newAccountDTO.getLastName());
        insertPassword(newAccountDTO.getPassword());
        selectDayOfBirth(newAccountDTO.getDayOfBirth());
        selectMonthOfBirth(newAccountDTO.getMonthOfBirth());
        selectYearOfBirth(newAccountDTO.getYearOfBirth());
        checkNewsletter(newAccountDTO.isNewsletter());
        return this;
    }

    public MyAccountPage clickRegisterButton() {
        registerButton.click();
        return new MyAccountPage(page);
    }

    private void checkGender(boolean isMale) {
        if (isMale) {
            mrRadioButton.click();
        } else {
            mrsRadioButton.click();
        }
    }

    private void insertFirstName(String firstName) {
        firstNameField.fill(firstName);
    }

    private void insertLastName(String lastName) {
        lastNameField.fill(lastName);
    }

    private void insertPassword(String password) {
        passwordField.fill(password);
    }

    private void selectDayOfBirth(int dayOfBirth) {
        dayOfBirthField.selectOption(String.valueOf(dayOfBirth));
    }

    private void selectMonthOfBirth(int monthOfBirth) {
        monthOfBirthField.selectOption(String.valueOf(monthOfBirth));
    }

    private void selectYearOfBirth(int yearOfBirth) {
        yearOfBirthField.selectOption(String.valueOf(yearOfBirth));
    }

    private void checkNewsletter(boolean isNewsletter) {
        if (isNewsletter) {
            newsletterCheckbox.check();
        }
    }
}
