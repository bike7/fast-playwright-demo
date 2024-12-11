package pl.akademiaqa.modul8.pages;


import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.modul8.pages.sections.AuthenticationSection;
import pl.akademiaqa.modul8.pages.sections.TopMenuSection;

@Getter
public class SignInPage extends BasePage {

    private TopMenuSection topMenuSection;
    private AuthenticationSection authenticationSection;

    public SignInPage(Page page) {
        super(page);
        this.topMenuSection = new TopMenuSection(page);
        this.authenticationSection = new AuthenticationSection(page);
    }
}
