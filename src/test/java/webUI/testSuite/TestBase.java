package webUI.testSuite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import webUI.pagesTodoLy.MainPage;
import webUI.session.Session;

public class TestBase {
    MainPage mainPage = new MainPage();

    @AfterEach
    public void close(){
        Session.getInstance().closeSession();
    }
    @BeforeEach
    public void open(){
        Session.getInstance().getBrowser().get("http://todo.ly/");
    }
}
