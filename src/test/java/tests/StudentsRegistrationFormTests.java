package tests;

import helpers.BaseTest;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojo.Student;

import static io.qameta.allure.SeverityLevel.BLOCKER;


public class StudentsRegistrationFormTests extends BaseTest {
    StudentRegistrationScenario test;

    @Test
    @Tag("positive")
    @Severity(BLOCKER)
    @DisplayName("Test 01: Check Student form registration for full form fill.")
    public void test01studentRegForm() {
        Student student = new Student();
        test = new StudentRegistrationScenario();
        test.successStudentRegisterScenario(student);
    }

    @Test
    @Tag("positive")
    @Severity(BLOCKER)
    @DisplayName("Test 02: Check Student form registration for full form only required fields fill.")
    public void test02studentRegForm() {
        Student student = new Student(true);
        test = new StudentRegistrationScenario();
        test.successStudentRegisterScenario(student);
    }

    @Test
    @Tag("negative")
    @Severity(BLOCKER)
    @DisplayName("Test 03: Check unsuccessful Student form registration for not filled required field `First Name`.")
    public void test03studentRegForm() {
        String fieldName = "firstName";
        Student student = new Student(true);
        student.setFirstName(null);
        test = new StudentRegistrationScenario();
        test.unSuccessStudentRegisterScenario(student, fieldName);
    }

    @Test
    @Tag("negative")
    @Severity(BLOCKER)
    @DisplayName("Test 04: Check unsuccessful Student form registration for not filled required field `Last Name`.")
    public void test04studentRegForm() {
        String fieldName = "lastName";
        Student student = new Student(true);
        student.setLastName(null);
        test = new StudentRegistrationScenario();
        test.unSuccessStudentRegisterScenario(student, fieldName);
    }

    @Test
    @Tag("failed")
    @Severity(BLOCKER)
    @DisplayName("Test 05: Failed test.")
    public void test05studentRegForm() {
        test = new StudentRegistrationScenario();
        test.failedTest();
    }

}
