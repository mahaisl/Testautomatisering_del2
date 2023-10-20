import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Scenario5 extends TestBase {

    @Test
    public void addToCart() {
        page.navigate("https://fyndiq.se");
        String loginUrl;
        Locator button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Godkänn och stäng"));
        button.click();

        Locator searchBox = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sök produkt"));

        // Fyll i sökfältet med den önskade söktermen,
        searchBox.fill("lampa");

        // Tryck på Enter-tangenten för att starta sökningen
        page.keyboard().press("Enter");
        page.waitForLoadState(LoadState.LOAD);

        // Klicka på den första produkten i sökresultaten för att öppna produktens sida
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Stjärnprojektor med Bluetooth Högtalare - Din egna Galaxlampa multifärg")).click();
        page.waitForLoadState(LoadState.LOAD);

        // Klicka på knappen för att lägga till produkten i varukorgen
        page.locator("button").filter(new Locator.FilterOptions().setHasText("Lägg i varukorgen")).nth(1).click();
        page.waitForLoadState(LoadState.LOAD);


        String pageContent = page.textContent("body");
        assertTrue(pageContent.contains("Stjärnprojektor med Bluetooth Högtalare"), "Produktsidan innehåller inte förväntad text.");


    }
}





