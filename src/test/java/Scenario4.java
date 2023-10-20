import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.options.LoadState;

public class Scenario4 extends TestBase {

    @Test
    public void invalidSearchTerm() {
        page.navigate("https://fyndiq.se");

        Locator button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Godkänn och stäng"));
        button.click();

        // Ange en ogiltig sökterm i sökfältet
        String invalidSearchTerm = "ogiltigSökterm123";
        page.fill("role=combobox[name='Sök produkt']", invalidSearchTerm);

        // Klicka på sökknappen
        page.keyboard().press("Enter");

        // Vänta på att sidan ska laddas klart
        page.waitForLoadState(LoadState.LOAD);


        boolean noResultsMessageDisplayed = page.isVisible("text=Vi kunde inte hitta någon matchning för din sökning");
        assertTrue(noResultsMessageDisplayed, "Meddelande om inga resultat hittades visas inte.");

    }
}



