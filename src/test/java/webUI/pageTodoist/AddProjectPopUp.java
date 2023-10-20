package webUI.pageTodoist;

import org.openqa.selenium.By;
import webUI.controls.Button;
import webUI.controls.TextBox;

public class AddProjectPopUp {
    public TextBox projectName = new TextBox(By.id("edit_project_modal_field_name"));
    public Button addButton = new Button(By.xpath("//button[@type='submit']"));
}
