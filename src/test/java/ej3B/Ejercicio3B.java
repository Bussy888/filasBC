package ej3B;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webUI.pageTodoist.*;
import webUI.session.Session;

import java.util.Random;

public class Ejercicio3B  {
    MainPage mainPage = new MainPage();

    SignUpPage signUpPage = new SignUpPage();
    CreateProfileSection1Page section1Page = new CreateProfileSection1Page();
    CreateProfileSection2Page section2Page = new CreateProfileSection2Page();

    WorkspacePage workspacePage = new WorkspacePage();

    ProjectWorkspaceSection projectWorkspaceSection = new ProjectWorkspaceSection();
    AddProjectPopUp addProjectPopUp = new AddProjectPopUp();

    static String email;
    String password = "Pass12345";

    static Random rand = new Random();

    @AfterEach
    public void close(){
        Session.getInstance().closeSession();
    }

    @BeforeEach
    public void open(){
        Session.getInstance().getBrowser().get("https://todoist.com/");
    }
    @Test
    public void loginTesting() throws InterruptedException {
        email = "MateoM"+rand.nextInt(500)+rand.nextInt(500)+"@algo.com";

        mainPage.signupButton.click();
        signUpPage.emailTextBox.setText(email);
        Thread.sleep(100);
        signUpPage.passwordTextBox.setText(password);
        signUpPage.signupButton.click();

        Thread.sleep(5000);
        section1Page.profileName.setText("Mateo Michel");
        section1Page.continueButton.click();

        Thread.sleep(1000);
        section2Page.personalButton.click();
        section2Page.noHelp.click();
        Thread.sleep(1500);
        section2Page.continueButton.click();
        Thread.sleep(2000);

        workspacePage.projects.click();
        projectWorkspaceSection.addProjectButton.click();

        String projectName="Proyecto Creado con Selenium";
        addProjectPopUp.projectName.setText(projectName);
        addProjectPopUp.addButton.click();
        Thread.sleep(5000);

        Assertions.assertTrue(projectWorkspaceSection.projectCreated(projectName).isControlDisplayed(), "ERROR no se pudo crear el proyecto");
    }}
