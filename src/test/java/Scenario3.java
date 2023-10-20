import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Scenario3 extends TestBase {

    @Test
    public void navigateToProductCategory() {
        page.navigate("https://fyndiq.se");

        Locator button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Godkänn och stäng"));
        button.click();

        // Hitta och klicka på länken till en produktkategori
        page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Hemmet")).click();
        page.getByRole(AriaRole.HEADING,new Page.GetByRoleOptions().setName("Hemmet")).click();

        // Vänta på att sidan ska laddas klart
        page.waitForLoadState(LoadState.LOAD);


        String currentUrl = page.url();
        assertTrue(currentUrl.contains("hemmet"), "Du är inte på rätt kategori-sida.");
    }
}