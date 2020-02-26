//Reference: https://www.youtube.com/watch?v=yDdBOspPp_c
package org.example;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_GET_Request {
    @Test
    void GetWeatherDetails(){

        //specify base URI
        RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
        //Request object
        RequestSpecification httpreq = RestAssured.given();

        //response object
        Response httpresp = httpreq.request(Method.GET,"/Hyderabad");

        //print the response in console window
        String responseBody=httpresp.getBody().asString();
        System.out.println(responseBody);

        //status code validation
        int status_code = httpresp.getStatusCode();
        System.out.println("Status Code: "+ status_code);
        Assert.assertEquals(status_code,200);

        //Status Line verification
        String stat_line = httpresp.getStatusLine();
        System.out.println(stat_line);
        Assert.assertEquals(stat_line,"HTTP/1.1 200 OK");
    }

}
