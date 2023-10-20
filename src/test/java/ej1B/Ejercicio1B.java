package ej1B;

import api.config.Configuration;
import api.factoryRequestB.FactoryRequest;
import api.testSuites.ApiBaseTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.Matchers.equalTo;

public class Ejercicio1B  extends ApiBaseTest {

    private final Random rnd = new Random();

    @Test
    public void testing() {
        createUser();
        authenticate();
        createProject();
        deleteToken();
        createProjectWithoutToken();
    }

    private void createUser() {
        String randomEmail = "enrique" + rnd.nextInt() + "@gmail.com";
        String randomPassword = "pwd" + rnd.nextInt();

        Configuration.user = randomEmail;
        Configuration.password = randomPassword;

        JSONObject body = new JSONObject();
        body.put("Email", randomEmail);
        body.put("Password", randomPassword);
        body.put("FullName", "Enrique");

        requestInfo.setUrl(Configuration.host + "/api/user.json")
                .setBody(body.toString());

        response = FactoryRequest.make(post).send(requestInfo);

        response.then().statusCode(200)
                .body("Email", equalTo(body.get("Email")))
                .body("FullName", equalTo(body.get("FullName")));
    }

    private void createProject() {
        String randomContent = "Project " + rnd.nextInt();

        JSONObject body = new JSONObject();
        body.put("Content", randomContent);

        requestInfo.setUrl(Configuration.host + "/api/projects.json")
                .setBody(body.toString());
        response = FactoryRequest.make(post).send(requestInfo);
        response.then().statusCode(200).
                body("Content", equalTo(body.get("Content")));
    }

    private void deleteToken() {
        requestInfo.setUrl(Configuration.host + "/api/authentication/token.json");
        response = FactoryRequest.make(delete).send(requestInfo);
        response.then()
                .statusCode(200)
                .body("UserEmail", equalTo(Configuration.user))
                .body("TokenString", equalTo(requestInfo.getHeaders().get("Token")));
    }

    private void createProjectWithoutToken() {
        String randomContent = "Project " + rnd.nextInt();

        JSONObject body = new JSONObject();
        body.put("Content", randomContent);

        requestInfo.setUrl(Configuration.host + "/api/projects.json")
                .setBody(body.toString());
        response = FactoryRequest.make(post).send(requestInfo);
        response.then().statusCode(200).
                body("ErrorMessage", equalTo("Not Authenticated"))
                .body("ErrorCode", equalTo(102));
    }
}