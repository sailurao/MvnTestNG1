//Reference: https://www.journaldev.com/21501/rest-assured-tutorial
package org.example;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasItems;

public class TC004_Get_Test {
    @Test(priority=1)
    void simple_get_test(){
        RestAssured.baseURI="http://localhost:3000";
        RequestSpecification httpreq = RestAssured.given();

        //read the response by sending request
        Response httpresp = httpreq.request(Method.GET,"/employees");

        //print the response
        String resp_body = httpresp.getBody().asString();
        System.out.println("RESPONSE BODY: "+resp_body);

        httpresp.then().body("id",hasItems(1,2));
        httpresp.then().body("name", hasItems("Pankaj"));
    }

    @Test(priority=2)
    void Simple_post_test(){
        RestAssured.baseURI="http://localhost:3000";
        RequestSpecification httpreq = RestAssured.given();

        //Request Payload sending along with request
        JSONObject reqparams=new JSONObject();
        reqparams.put("name","SITHA4");
        reqparams.put("salary","50000");
        reqparams.put("id","10");

        //deleeting the record before adding incase if it exists to not to run into the error: record already exists
        Response httpresp1 = httpreq.request(Method.DELETE,"/employees/10");
        
        httpreq.header("Content-Type","application/json");
        httpreq.body(reqparams.toJSONString());


        //reading response by sending Request
        Response httpresp = httpreq.request(Method.POST,"/employees");

        //print the response in console window
        String responseBody=httpresp.getBody().asString();
        System.out.println(responseBody);

        //status code validation
        int status_code = httpresp.getStatusCode();
        System.out.println("Status Code: "+ status_code);
        Assert.assertEquals(status_code,201);

        //verify the json response data
        String d_name = httpresp.jsonPath().get("name");
        String d_salary = httpresp.jsonPath().get("salary");
        String d_id = httpresp.jsonPath().get("id");
        System.out.println("RESPONSE:"+d_name+","+d_salary+",");
//        Assert.assertEquals(SuccessCode,"OPERATION_SUCCESS");
    }

}
