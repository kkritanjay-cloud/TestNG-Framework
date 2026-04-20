package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String fileName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // 👉 Project ke andar save karega
            File dest = new File(System.getProperty("user.dir") + "/screenshots/" + fileName + ".png");

            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved at: " + dest.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}