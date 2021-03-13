package po;

import io.qameta.allure.Step;
import pojo.Student;

import java.io.File;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.openqa.selenium.Keys.*;

public class BasePageObject {

    @Step("Open form Page {url}")
    public BasePageObject openForm(String url) {
        open(url);

        return this;
    }

    @Step("Fill fields in registration fields based on `pojo` student info")
    public BasePageObject fillRegForm(Student student) {
        String cssGender;
        if (student.getFirstName() != null)
            $("#firstName").setValue(student.getFirstName());
        if (student.getLastName() != null)
            $("#lastName").setValue(student.getLastName());

        $("#userEmail").setValue(student.getEmail());

        if (student.getGender() != 0) {
            cssGender = format("#gender-radio-%s", student.getGender());
            $(cssGender).parent().click();
            String genderName = $(cssGender).getValue();
            student.setGender(genderName);
        }
        $("#userNumber").setValue(student.getMobileNum());
        if (student.getBirthDate() != null) {
            $("#dateOfBirthInput").sendKeys(chord(CONTROL, "a"));
            $("#dateOfBirthInput").sendKeys(student.getBirthDateIn() + ENTER);
        }
        if (student.getSubjects() != null)
            $("#subjectsInput").setValue(student.getSubjects())
                    .pressEnter();
        if (student.getHobbies() != 0)
            $(format("#hobbies-checkbox-%s", student.getHobbies())).parent()
                    .click();
        if (student.getAddress() != null)
            $("#currentAddress").setValue(student.getAddress());
        if (student.getState() != null)
            $("#react-select-3-input").setValue(student.getState())
                    .pressEnter();
        if (student.getCity() != null)
            $("#react-select-4-input").setValue(student.getCity())
                    .pressEnter();
     /*   if (student.getIcon() != null) {
            File file = student.getIcon();
            $("#uploadPicture").uploadFile(file);
        }*/
        return this;
    }

    @Step("Check if all fields for registered student has expected values")
    public void registeredFormVerification(Student student) {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//td[text()='Student Name']").parent().shouldHave(text(student.getFirstName() + " " + student.getLastName()));
        if (student.getEmail() != null)
            $x("//td[text()='Student Email']").parent().shouldHave(text(student.getEmail()));
        $x("//td[text()='Gender']").parent().shouldHave(text(student.getGenderName()));
        if (student.getMobileNum() != null)
            $x("//td[text()='Mobile']").parent().shouldHave(text(student.getMobileNum()));
        if (student.getBirthDate() != null)
            $x("//td[text()='Date of Birth']").parent().shouldHave(text(student.getBirthDateOut()));
    }

    @Step("Submit registration form")
    public void submitForm() {
        $("#submit").click();
    }

    @Step("Validate that Student registration form is opened")
    public BasePageObject validateFormOpen() {
        $("#app").shouldHave(text("Student Registration Form"));
        return this;
    }

    @Step("Validate that required field in Student registration form is empty")
    public void emptyField(String field) {
        $(("#" + field)).shouldBe(empty);
    }

}
