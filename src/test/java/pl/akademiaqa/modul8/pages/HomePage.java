package pl.akademiaqa.modul8.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.modul8.pages.sections.TopMenuSection;

public class HomePage extends BasePage {

    @Getter
    private TopMenuSection topMenuSection;

    public HomePage(Page page) {
        super(page);
        this.topMenuSection = new TopMenuSection(page);
    }

    public HomePage navigate() {
        page.navigate("http://www.automationpractice.pl");
        return this;
    }

}