import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Scenario9 extends TestBase {

    @Test
    public void navigateAndGoBack() {
        // Besök startsidan
        page.navigate("https://fyndiq.se/");

        Locator button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Godkänn och stäng"));
        button.click();

        // Hitta och klicka på länken till en produktkategori med hjälp av aria-label, type="button", och class="sc-bcPKhP bsBTWI"
        page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Barn")).click();
        page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Barn")).click();

        page.waitForLoadState(LoadState.LOAD);

        // Gå tillbaka till den första sidan med webbläsarens "Tillbaka"-knapp
        page.goBack();


        page.waitForLoadState(LoadState.LOAD);

        // Hämta URL:en för den andra sidan innan du går tillbaka dit
        String secondPageUrl = page.url();


        // Kontrollera att vi är tillbaka på den andra sidan genom att jämföra URL:en
        String currentUrl = page.url();
        assertEquals(secondPageUrl, currentUrl, "Sidan gick inte tillbaka till den andra sidan korrekt.");
    }
}