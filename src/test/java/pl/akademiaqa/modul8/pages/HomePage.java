package pl.akademiaqa.modul8.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.modul8.pages.sections.TopMenuSection;

public class HomePage {

    private Page page;
    @Getter
    private TopMenuSection topMenuSection;

    public HomePage(Page page) {
        this.page = page;
        this.topMenuSection = new TopMenuSection(page);
    }

}