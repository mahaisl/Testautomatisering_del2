import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Scenario7 extends TestBase {

    @Test
    public void navigateToProductCategory() {
        page.navigate("https://fyndiq.se");

        Locator button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Godkänn och stäng"));
        button.click();

        // Hitta och klicka på länken till en produktkategori med hjälp av aria-label, type="button", och class="sc-bcPKhP bsBTWI"
        page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Kundservice")).click();
        page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Kundservice")).click();

        page.waitForLoadState(LoadState.LOAD);

        String pageTitle = page.title();
        assertTrue(pageTitle.contains("Fyndiq"), "Sidan kunde inte laddas.");
    }
}