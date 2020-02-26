package org.example;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_POST_Request {
    @Test
    void RegisterCustomer(){
        //specify base URI
        RestAssured.baseURI="http://restapi.demoqa.com/customer";
        //Request object
        RequestSpecification httpreq = RestAssured.given();

        //Request Payload sending along with request
        JSONObject reqparams=new JSONObject();
        reqparams.put("FirstName","RAMA");
        reqparams.put("LastName","Sitha");
        reqparams.put("UserName","SRAMA");
        reqparams.put("Password","RAMA");
        reqparams.put("Email","Rama@yahoo.com");

        httpreq.header("Content-Type","application/json");
        httpreq.body(reqparams.toJSONString());


        //reading response by sending Request
        Response httpresp = httpreq.request(Method.POST,"/register");

        //print the response in console window
        String responseBody=httpresp.getBody().asString();
        System.out.println(responseBody);

        //status code validation
        int status_code = httpresp.getStatusCode();
        System.out.println("Status Code: "+ status_code);
        Assert.assertEquals(status_code,201);

       //verify the successcode from the response body
       String SuccessCode = httpresp.jsonPath().get("SuccessCode");
       System.out.println("SUCCESS CODE:"+SuccessCode);
       Assert.assertEquals(SuccessCode,"OPERATION_SUCCESS");
    }
}
