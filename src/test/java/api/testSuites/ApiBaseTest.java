package api.testSuites;

import api.config.Configuration;
import api.factoryRequestB.FactoryRequest;
import api.factoryRequestB.RequestInfo;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.Matchers.equalTo;

public class ApiBaseTest {
    public String post ="post";
    public String put = "put";
    public String get ="get";
    public String delete ="delete";

    public RequestInfo requestInfo;
    public Response response;
    @BeforeEach
    public void before(){
        requestInfo = new RequestInfo();
    }

    @AfterEach
    public void after(){

    }

    public void authenticate() {
        requestInfo.setBasicAuthNeeded(true)
                .setUrl(Configuration.host + "/api/authentication/token.json");

        response = FactoryRequest.make(get).send(requestInfo);
        response.then().statusCode(200).
                body("UserEmail", equalTo(Configuration.user));

        String token = response.getBody().path("TokenString").toString();

        requestInfo.setHeaders("Token", token)
                .setBasicAuthNeeded(false);
    }
}
