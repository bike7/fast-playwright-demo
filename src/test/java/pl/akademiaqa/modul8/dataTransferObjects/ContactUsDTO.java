package pl.akademiaqa.modul8.dataTransferObjects;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ContactUsDTO {

    private String subjectHeading;
    private String emailAddress;
    private String orderReference;
    private String fileToUpload;
    private String message;

    public static ContactUsDTO getDefaultContactUsDTO() {
        return ContactUsDTO.builder()
                .subjectHeading("Webmaster")
                .emailAddress("demo@mailinator.com")
                .orderReference("123456")
                .fileToUpload("uploads/test.txt")
                .message("This is a test message")
                .build();
    }

}
