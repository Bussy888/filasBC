package ej1C;

import api.config.Configuration;
import api.factoryRequestC.FactoryRequest;
import api.factoryRequestC.RequestInfo;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;

public class Ejercicio1C {
    RequestInfo requestInfo = new RequestInfo();
    Response response;
    JSONObject body = new JSONObject();
    String auth;
    String email;

    @BeforeEach
    public void setup(){
        email = "MateoM"+new Date().getTime()+"@algo.com";
        auth = Base64.getEncoder().encodeToString((email+":"+Configuration.password).getBytes());
    }

    @Test
    public void verifyCreateUserAndItemWithToken(){
        //Create User
        body.clear();
        body.put("Email", email);
        body.put("Password", Configuration.password);
        body.put("FullName", "MateoM");
        requestInfo.setHost(Configuration.host+"/api/user.json").setBody(body.toString());
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Email", equalTo(body.get("Email")))
                .body("FullName", equalTo(body.get("FullName")));

        //verify Token
        body.clear();
        requestInfo.setHost(Configuration.host+"/api/authentication/token.json").setHeader("Authorization", "Basic "+auth);
        response = FactoryRequest.make("get").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("UserEmail", equalTo(email));

        String token = response.then().extract().path("TokenString");

        //Create Item
        body.put("Content", "MateoItem");
        requestInfo.removeHeader("Authorization").setHost(Configuration.host+"/api/items.json").setBody(body.toString()).setHeader("Token", token);
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")));

        //Delete Token
        requestInfo.setHost(Configuration.host+"/api/authentication/token.json").setHeader("Token", token);
        response = FactoryRequest.make("delete").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("TokenString", equalTo(token));

        //Create Item with deleted token
        body.clear();
        body.put("Content", "New MateoItem");
        requestInfo.setHost(Configuration.host+"/api/items.json").setBody(body.toString()).setHeader("Token", token);
        response = FactoryRequest.make("post").send(requestInfo);
        response.then()
                .log().all()
                .statusCode(200)
                .body("ErrorCode", equalTo(102));
    }
}