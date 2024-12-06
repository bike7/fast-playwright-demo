package pl.akademiaqa.modul8.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.modul8.pages.sections.ContactUsForm;

public class ContactUsPage {

    private Page page;
    @Getter
    private ContactUsForm contactUsForm;

    public ContactUsPage(Page page) {
        this.page = page;
    }

}
