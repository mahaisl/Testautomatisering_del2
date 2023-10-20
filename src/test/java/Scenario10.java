import com.microsoft.playwright.*;
import java.io.File;

import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;


public class Scenario10 extends TestBase {
    @Test
    public void captureScreenshot() {
        page.navigate("https://fyndiq.se");

        Locator button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Godkänn och stäng"));
        button.click();

        // Ange sökväg och filnamn som en sträng
        String outputPath = "screenshot.png";

        // Skapa en File-objekt från strängen
        File outputFile = new File(outputPath);

        // Ta en skärmbild och spara den med File-objektet som sökväg
        page.screenshot(new Page.ScreenshotOptions().setPath(outputFile.toPath()));

        // Verify that the screenshot file exists
        assertTrue(outputFile.exists());
    }
}