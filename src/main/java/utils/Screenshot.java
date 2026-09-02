package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
    public static void main(String[] args) {
        createFilename();
    }

    private static String createFilename() {
        SimpleDateFormat formater
                = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        System.out.println(System.currentTimeMillis());
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);
        String curDate = formater.format(date);
        System.out.println(curDate);
        String filename = "src/test/resources/screenshots/screen-"
                + curDate + ".png";
        System.out.println(filename);
        return filename;
    }

    public static void takeScreenshot(TakesScreenshot screenshot){
        String filename = createFilename();
        File screen = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screen.toPath(), new File(filename).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
