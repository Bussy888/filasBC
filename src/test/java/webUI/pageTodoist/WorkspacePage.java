package webUI.pageTodoist;

import org.openqa.selenium.By;
import webUI.controls.Button;

public class WorkspacePage {

    public Button projects = new Button(By.xpath("//a[@href='/app/projects']"));
    public Button profPic = new Button(By.id(":r3:"));
    public Button betaButton = new Button(By.id(":rf:"));

}
