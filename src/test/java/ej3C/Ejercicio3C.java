package ej3C;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webUI.pageTodoist.*;
import webUI.session.Session;

import java.util.Date;
import java.util.Random;

public class Ejercicio3C {

    public MainPage mainTodoistPage = new MainPage();
    public LoginPage loginPage = new LoginPage();
    public WorkspacePage workspacePage = new WorkspacePage();

    public NavBarSection navBarSection = new NavBarSection();
    public ConfigurationSection configurationSection = new ConfigurationSection();
    @AfterEach
    public void close(){
        Session.getInstance().closeSession();
    }

    @BeforeEach
    public void open(){
        Session.getInstance().getBrowser().get("https://todoist.com/");
    }

    @Test
    public void createProject() throws InterruptedException{
        String nameUser = "Mateo"+ new Date().getTime();
        mainTodoistPage.loginButton.click();
        loginPage.emailTextBox.setText("soneg26166@unbiex.com");
        loginPage.passwordTextBox.setText("Pass12345");
        loginPage.loginButton.click();

        workspacePage.profPic.click();
        workspacePage.betaButton.click();
        Thread.sleep(1000);

        navBarSection.openInfoButton.click();
        navBarSection.openSettingsButton.click();
        configurationSection.nameProfileButton.click();
        configurationSection.nameProfileText.clearSetText(nameUser);
        configurationSection.actualizarButton.click();
        configurationSection.cerrarConfigButton.click();
        Thread.sleep(3000);

        navBarSection.openInfoButton.click();
        navBarSection.openSettingsButton.click();
        configurationSection.nameProfileButton.click();
        Assertions.assertEquals(configurationSection.nameProfileText.getTextProperty("defaultValue"), nameUser, "ERROR! El usuario no se actualizo");
        Thread.sleep(3000);

    }
}
