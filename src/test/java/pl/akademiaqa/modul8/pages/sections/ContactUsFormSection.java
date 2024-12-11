package pl.akademiaqa.modul8.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.modul8.dataTransferObjects.ContactUsDTO;
import pl.akademiaqa.modul8.pages.BasePage;

import java.nio.file.Paths;

@Getter
public class ContactUsFormSection extends BasePage {

    private Locator sendMessageButton;
    private Locator errorMessage;
    private Locator subjectHeading;
    private Locator emailAddressInput;
    private Locator orderReferenceInput;
    private Locator fileUploadInput;
    private Locator messageText;
    private Locator confirmationMessage;

    public ContactUsFormSection(Page page) {
        super(page);
        this.sendMessageButton = page.locator("#submitMessage");
        this.errorMessage = page.getByText("Invalid email address.");
        this.subjectHeading = page.locator("#id_contact");
        this.emailAddressInput = page.getByLabel("Email address");
        this.orderReferenceInput = page.locator("#id_order");
        this.fileUploadInput = page.locator("#fileUpload");
        this.messageText = page.locator("#message");
        this.confirmationMessage = page.getByText("Your message has been successfully sent to our team.");
    }

    public ContactUsFormSection fillInForm(ContactUsDTO contactUsDTO) {
        selectSubjectHeading(contactUsDTO.getSubjectHeading())
                .enterEmailAddress(contactUsDTO.getEmailAddress())
                .enterOrderReference(contactUsDTO.getOrderReference())
                .uploadFile(contactUsDTO.getFileToUpload())
                .enterMessageText(contactUsDTO.getMessage());
        return this;
    }

    public ContactUsFormSection clickSendMessageButton() {
        sendMessageButton.click();
        return this;
    }

    private ContactUsFormSection selectSubjectHeading(String subject) {
        subjectHeading.selectOption(subject);
        return this;
    }

    private ContactUsFormSection enterEmailAddress(String email) {
        emailAddressInput.fill(email);
        return this;
    }

    private ContactUsFormSection enterOrderReference(String orderReference) {
        orderReferenceInput.fill(orderReference);
        return this;
    }

    private ContactUsFormSection uploadFile(String filePath) {
        fileUploadInput.setInputFiles(Paths.get(filePath));
        return this;
    }

    private ContactUsFormSection enterMessageText(String message) {
        messageText.fill(message);
        return this;
    }
}
