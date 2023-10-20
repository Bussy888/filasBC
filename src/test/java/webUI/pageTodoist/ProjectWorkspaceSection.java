package webUI.pageTodoist;

import org.openqa.selenium.By;
import webUI.controls.Button;
import webUI.controls.TextBox;

public class ProjectWorkspaceSection {
    public Button addProjectButton = new Button(By.xpath("//button[@class='_8313bd46 f169a390 _8b7f1a82 _8644eccb _2a3b75a1']"));

    public TextBox projectCreated(String name){
        return new TextBox(By.xpath("//h1//span[text()='"+name+"']"));
    }
}
