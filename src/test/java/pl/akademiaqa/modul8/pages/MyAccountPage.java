package pl.akademiaqa.modul8.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.modul8.pages.sections.MyAccountSection;

@Getter
public class MyAccountPage extends BasePage {

    private MyAccountSection myAccountSection;

    public MyAccountPage(Page page) {
        super(page);
        this.myAccountSection = new MyAccountSection(page);
    }
}
