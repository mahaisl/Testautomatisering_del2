import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Scenario8 extends TestBase {
    @Test
    void TextInput() throws InterruptedException {

        String loginUrl = "https://fyndiq.se/";
        page.navigate(loginUrl);

        Locator button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Godkänn och stäng"));
        button.click();

        // Scrolla ner till botten av sidan
        page.evaluate("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);

        Locator finlandButton = page.locator("text=Fyndiq Finland");
        finlandButton.click();

        String pageTitle = page.title();
        assertTrue(pageTitle.contains("Fyndiq"), "Sidan kunde inte laddas.");

    }
}
