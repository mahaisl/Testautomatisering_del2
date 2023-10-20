
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario2 extends TestBase{
    @Test
    public void test1(){
        page.navigate("https://fyndiq.se");
        String loginUrl;

        Locator button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Godkänn och stäng"));
        button.click();

        Locator searchBox = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sök produkt"));

        // Fyll i sökfältet med den önskade söktermen, t.ex. "smink"
        searchBox.fill("smink");

        // Tryck på Enter-tangenten för att starta sökningen
        page.keyboard().press("Enter");

        // Vänta på att sidan ska laddas klart efter sökningen
        page.waitForLoadState(LoadState.LOAD);

        // Kontrollera att sökresultaten innehåller relevanta produkter (i detta fall, "smink")
        Locator searchResults = page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Sökresultat \"smink\""));
        assertThat(searchResults).containsText("Sökresultat \"smink\"");

    }
}
