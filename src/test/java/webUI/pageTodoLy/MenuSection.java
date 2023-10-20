package webUI.pageTodoLy;

import org.openqa.selenium.By;
import webUI.controls.Button;
import webUI.controls.TextBox;

public class MenuSection{
        public Button logoutButton = new Button(By.xpath("//a[text()='Logout']"));
        public Button settings = new Button(By.xpath("//a[text()='Settings']"));



}
