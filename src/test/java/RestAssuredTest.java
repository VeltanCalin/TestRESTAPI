import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredTest {


     private String URL = "http://ec2-34-251-162-89.eu-west-1.compute.amazonaws.com/peps";

    @Test
    public void simpleJSONRestAssuredExample(){

        RestAssured.
                when().
                get(URL).
                then().assertThat().
                body("name[0]",
                        equalTo("Nixon"));
    }

    @Test
    public void testResponseBodytoGetMethod()
    {


        RestAssured.baseURI = "http://ec2-34-251-162-89.eu-west-1.compute.amazonaws.com";

        RequestSpecification request = RestAssured.given();

        Response response = request.request(Method.GET, "/peps");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

    }

    @Test
    public void testResponseCodeGetMethod() {

        Response resp = RestAssured.get(URL);

        int code = resp.getStatusCode();
        System.out.println("Status code is:" + code);

        Assert.assertEquals(code, 200);
    }


    @Test (enabled = false)
    public void testResponseCodetoPostMethod(){

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");

        JSONObject json = new JSONObject();
        json.put("name", "Nixon");
        //json.put("country", "SUA");
        json.put("yob", "1960");
        json.put("position", "PRESIDENT");
        json.put("risk", "5");

        request.body(json.toJSONString());

        Response response = request.post(URL);

        int code = response.getStatusCode();
        Assert.assertEquals(code , 201);

    }


    @Test
    public void testResponseCodetoDeleteMethod(){

        RequestSpecification request = RestAssured.given();

        Response response = request.delete("http://ec2-34-251-162-89.eu-west-1.compute.amazonaws.com/peps/5bfb015c17b9b5008eb49f39");

        int code = response.getStatusCode();
        Assert.assertEquals(code , 405);

    }

    @Test
    public void testResponseCodetoGETMethodById(){

        RequestSpecification request = RestAssured.given();

        Response response = request.get("http://ec2-34-251-162-89.eu-west-1.compute.amazonaws.com/peps/5bfb070717b9b5008eb49f3a");

        int code = response.getStatusCode();
        Assert.assertEquals(code , 200);

    }


}
