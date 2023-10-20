package webUI.pageTodoist;

import org.openqa.selenium.By;
import webUI.controls.Button;
import webUI.controls.TextBox;

public class CreateProfileSection1Page {
    public TextBox profileName = new TextBox(By.id("element-0"));
    public Button continueButton = new Button(By.xpath("//button[@type='submit']"));
}
