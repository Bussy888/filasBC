package ej4C;

import org.junit.jupiter.api.*;
import webUI.pageTodoLy.*;
import webUI.session.Session;

import java.util.Date;

public class Ejercicio4C {


    public MainPage mainPage = new MainPage();
    public MenuSection menuSection = new MenuSection();


    public CentralSection centralSection = new CentralSection();

    public SettingsSection settingsSection = new SettingsSection();

    protected SignUpSection signUpPage = new SignUpSection();


    @AfterEach
    public void close(){
        Session.getInstance().closeSession();
    }

    @BeforeEach
    public void open(){
        Session.getInstance().getBrowser().get("https://todo.ly/");
    }

    @BeforeAll
    public static void setup(){
        userRand = "mateom"+new Date().getTime() +"@algo.com";
    }
    static String userRand;
    @Test
    public void verifyEjercicio3Test() throws InterruptedException {
        mainPage.signUpButton.click();
        signUpPage.fullNameTextbox.setText(userRand);
        signUpPage.emailTextbox.setText(userRand);
        signUpPage.passwordTextbox.setText("Pass12345");
        signUpPage.acceptTermsButton.click();
        signUpPage.signUpButton.click();
        Assertions.assertTrue(centralSection.openSettingsButton.isControlDisplayed(), "ERROR usuario no creado!");

        menuSection.settings.click();
        settingsSection.accountButton.click();
        settingsSection.deleteAccountButton.click();
        Session.getInstance().getBrowser().switchTo().alert().accept();

        Thread.sleep(3000);
        Assertions.assertTrue(mainPage.loginButton.isControlDisplayed(), "ERROR! usuario eliminado");

    }
}
