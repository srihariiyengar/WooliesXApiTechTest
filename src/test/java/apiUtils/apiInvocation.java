package apiUtils;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class apiInvocation {
    Response response;

    /**
     * HTTP get method for fetching the response from the endpoint
     * @param endPoint
     * @return
     */
    public Response getApi(String endPoint) {
        response = RestAssured.given().config(RestAssured.config.sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                .when().log().all()
                .contentType("application/xml")
                .get(endPoint)
                .then()
                .extract().response();
        return response;
    }
}