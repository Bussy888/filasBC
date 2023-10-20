package webUI.pageTodoist;

import org.openqa.selenium.By;
import webUI.controls.Button;

public class NavBarSection {
    public Button openInfoButton = new Button(By.id(":r0:"));
    public Button openSettingsButton = new Button(By.xpath("//span[contains(text(), 'Config')]"));

    public Button logoutButton = new Button(By.xpath("//span[contains(text(), 'Cerrar')]"));
}
