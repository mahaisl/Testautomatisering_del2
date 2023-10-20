import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.options.LoadState;
import static org.junit.jupiter.api.Assertions.*;

public class Scenario1 extends TestBase {

    @Test
    public void verifyHomePageLoad() {
        page.navigate("https://fyndiq.se");

        Locator button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Godkänn och stäng"));
        button.click();

        // Vänta på att sidan ska laddas klart
        page.waitForLoadState(LoadState.LOAD);

        // Verifiera att sidan har laddats korrekt genom att kontrollera titeln
        String pageTitle = page.title();
        assertTrue(pageTitle.contains("Fyndiq"), "Sidan laddades inte korrekt eller har fel titel.");
    }
}
