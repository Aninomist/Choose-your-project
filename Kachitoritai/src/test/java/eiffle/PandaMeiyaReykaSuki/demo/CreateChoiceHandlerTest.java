package eiffle.PandaMeiyaReykaSuki.demo;

import java.io.IOException;

import com.google.gson.Gson;

import eiffle.PandaMeiyaReykaSuki.http.ChoiceRequest;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceResponse;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;


/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateChoiceHandlerTest extends LambdaTest{

    void testSuccessInput(String incoming) throws IOException {
    	CreateChoiceHandler handler = new CreateChoiceHandler();
    	ChoiceRequest req = new Gson().fromJson(incoming, ChoiceRequest.class);
       
        ChoiceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }

    @Test
    public void testCreateChoice() {
    	
    	ChoiceRequest ccr = new ChoiceRequest(
    			"testChoiceFromEcliseWithID100",
    			3,
    			3,
    			"faketime",
    			"description test");
    	
    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
    	
    	 try {
         	testSuccessInput(SAMPLE_INPUT_STRING);
         } catch (IOException ioe) {
         	Assert.fail("Invalid:" + ioe.getMessage());
         }

    }
}
