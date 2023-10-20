package ej2B;

import api.config.Configuration;
import api.factoryRequestB.FactoryRequest;
import api.testSuites.ApiBaseTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;

public class Ejercicio2B  extends ApiBaseTest {
    private final Random rnd = new Random();
    private List<Integer> itemIds = new ArrayList<>();
    @Test
    public void testing(){
        authenticate();

        for (int i = 0; i < 4; i++) {
            createItem();
        }

        getItems();

        for (Integer id: itemIds) {
            deleteItem(id);
        }

    }

    private void createItem(){
        String randomContent = "Item " + rnd.nextInt();

        JSONObject body = new JSONObject();
        body.put("Content", randomContent);

        requestInfo.setUrl(Configuration.host + "/api/items.json")
                .setBody(body.toString());
        response = FactoryRequest.make(post).send(requestInfo);
        response.then().statusCode(200).
                body("Content", equalTo(body.get("Content")));

        String id = response.getBody().path("Id").toString();

    }

    private void deleteItem(Integer id){
        requestInfo.setUrl(Configuration.host + "/api/items/" + id + ".json");
        response = FactoryRequest.make(delete).send(requestInfo);
        response.then()
                .statusCode(200)
                .body("Id", equalTo(id))
                .body("Deleted", equalTo(true));
    }

    private void getItems(){
        requestInfo.setUrl(Configuration.host + "/api/items.json");
        response = FactoryRequest.make(get).send(requestInfo);
        response.then().statusCode(200);

        itemIds = response.then().extract().path("Id");
    }
}
