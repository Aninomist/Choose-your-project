package eiffle.PandaMeiyaReykaSuki.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    	ChoiceHandler handler = new ChoiceHandler();
    	ChoiceRequest req = new Gson().fromJson(incoming, ChoiceRequest.class);
       
        ChoiceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }

    
    
    @Test
    public void testCreateChoice() {
    	
    	List<String> altDescription = new ArrayList<>();
    	altDescription.add("Alt 1");
    	altDescription.add("Alt 2");
    	altDescription.add("Alt 3");
    	
    	ChoiceRequest ccr = new ChoiceRequest(
    			"newChoice",
    			3,
    			3,
    			"faketime",
    			altDescription);
    	
    	
    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
    	
    	System.out.println(SAMPLE_INPUT_STRING);
    	
    	 try {
         	testSuccessInput(SAMPLE_INPUT_STRING);
         } catch (IOException ioe) {
         	Assert.fail("Invalid:" + ioe.getMessage());
         }

    }
}
