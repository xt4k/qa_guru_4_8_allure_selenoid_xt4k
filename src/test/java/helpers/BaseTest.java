package helpers;

import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Configuration.startMaximized;
import static java.lang.System.setProperty;
import static org.junit.jupiter.api.Assertions.fail;

public class BaseTest {
    protected static String basePageUrl = "https://demoqa.com/automation-practice-form";

    @BeforeAll
    static void setup() {
        startMaximized = true;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/properties/common.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setProps(properties);
    }

    private static void setProps(Properties properties) {
        properties.forEach((key, value) -> setProperty((String) key, (String) value));
    }

    @Step("Here will be in purpose failed test.")
    public void failStep() {
        fail("This step fails test.");
    }

}