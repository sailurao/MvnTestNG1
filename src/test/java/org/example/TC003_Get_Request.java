package org.example;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_Get_Request {
    @Test
    void GoogleMapTest(){

        //specify base URI
        RestAssured.baseURI="https://maps.googleapis.com";
        //Request object
        RequestSpecification httpreq = RestAssured.given();

        //response object
        Response httpresp = httpreq.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

        //print the response in console window
        String responseBody=httpresp.getBody().asString();
        System.out.println(responseBody);

        //verify the header part
        //capture details of headers
        String contentType = httpresp.header("Content-type");
        System.out.println("Content Type is "+contentType);
        Assert.assertEquals(contentType,"application/xml; charset=UTF-8");

        String contentEncoding = httpresp.header("Content-Encoding");
        System.out.println("Content Encoding is "+contentEncoding);
        Assert.assertEquals(contentEncoding,"gzip");



    }

}
