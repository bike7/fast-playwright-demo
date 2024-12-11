package pl.akademiaqa.modul8.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.modul8.pages.sections.CreateAccountSection;

@Getter
public class CreateAccountPage extends BasePage {
    private CreateAccountSection createAccountSection;

    public CreateAccountPage(Page page) {
        super(page);
        this.createAccountSection = new CreateAccountSection(page);
    }
}
