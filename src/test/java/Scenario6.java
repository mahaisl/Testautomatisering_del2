import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class Scenario6 extends TestBase {

    @Test
    public void removeFromCart() {
        page.navigate("https://fyndiq.se");
        String loginUrl;

        Locator button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Godkänn och stäng"));
        button.click();

        Locator searchBox = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sök produkt"));

        // Fyll i sökfältet med den önskade söktermen,"lampa"
        searchBox.fill("lampa");
        page.keyboard().press("Enter");

        page.waitForLoadState(LoadState.LOAD);

        // Klicka på den första produkten i sökresultaten för att öppna produktens sida
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Stjärnprojektor med Bluetooth Högtalare - Din egna Galaxlampa multifärg")).click();
        page.locator("button").filter(new Locator.FilterOptions().setHasText("Lägg i varukorgen")).nth(1).click();

        // Klicka på till kassan för att öppna varukorgen
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("1 Till kassan")).click();

        page.waitForLoadState(LoadState.LOAD);

        // Hitta produkten som du vill ta bort från varukorgen
        Locator productToRemove = page.locator("Stjärnprojektor med Bluetooth Högtalare - Din egna Galaxlampa multifärg");

        // Klicka på knappen för att ta bort produkten från varukorgen
        page.getByLabel("Ta bort Stjärnprojektor med Bluetooth Högtalare - Din egna Galaxlampa multifärg från varukorgen").first().click();

        page.waitForLoadState(LoadState.LOAD);

        // Kontrollera att produkten har tagits bort från varukorgen
       Locator message = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Oops!"));
        assertThat( message).hasText("Oops!");
    }

}