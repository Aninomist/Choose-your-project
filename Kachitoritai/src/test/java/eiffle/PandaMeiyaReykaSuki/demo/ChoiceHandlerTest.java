package eiffle.PandaMeiyaReykaSuki.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import eiffle.PandaMeiyaReykaSuki.http.ChoiceRequest;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceResponse;
import eiffle.PandaMeiyaReykaSuki.http.RegisterOrSignInRequest;
import eiffle.PandaMeiyaReykaSuki.http.RegisterOrSignInResponse;
import eiffle.PandaMeiyaReykaSuki.model.Alternative;

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
public class ChoiceHandlerTest extends LambdaTest{

    String testSuccessCreateChoice(String incoming) throws IOException {
    	ChoiceHandler handler = new ChoiceHandler();
    	ChoiceRequest req = new Gson().fromJson(incoming, ChoiceRequest.class);
       
        ChoiceResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
        return resp.response;
    }
    
    List<Alternative> testSuccessCreateMember(String incoming) throws IOException {
    	RegisterOrSignInHandler handler = new RegisterOrSignInHandler();
    	RegisterOrSignInRequest req = new Gson().fromJson(incoming, RegisterOrSignInRequest.class);
       
    	RegisterOrSignInResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
        return resp.alternatives;
    }
    
    void testFailCreateMember(String incoming) throws IOException {
    	RegisterOrSignInHandler handler = new RegisterOrSignInHandler();
    	RegisterOrSignInRequest req = new Gson().fromJson(incoming, RegisterOrSignInRequest.class);
       
    	RegisterOrSignInResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(405, resp.httpCode);
    }

    
    
    @Test
    public void testCreateChoice() {
    	
    	List<String> altDescription = new ArrayList<>();
    	altDescription.add("Alt 1d kjasdlhfl kjsad");
    	altDescription.add("Alt 2 ajsdfhalkjsdfhsalkfd");
    	altDescription.add("Alt 3 Dlka;lfdjlkfas");
    	
    	ChoiceRequest CRR_CHOICE = new ChoiceRequest(
    			"newChoice",
    			3,
    			3,
    			"This is test Descritpion in test",
    			altDescription);
    	
    	
    	String CHOICE_REQUEST = new Gson().toJson(CRR_CHOICE);
    	
    	System.out.println(CHOICE_REQUEST);
    	
    	 try {
         	String choiceID = testSuccessCreateChoice(CHOICE_REQUEST);
         	if(choiceID != null) {
         		List<String> users = new ArrayList<>();
         		List<Alternative> alts = new ArrayList<>();
         		for(int i = 0; i < 3; i++) {
         			users.add(choiceID.substring(0, 7) + "TEST-" + i);
         			RegisterOrSignInRequest CRR_REGISTER = new RegisterOrSignInRequest(choiceID, users.get(i));
         			String REGISTER_REQUEST = new Gson().toJson(CRR_REGISTER);
         			alts = testSuccessCreateMember(REGISTER_REQUEST);
         		}
         		RegisterOrSignInRequest CRR_REGISTER_FAIL = new RegisterOrSignInRequest(choiceID, "shold not be created");
     			String REGISTER_FAIL_REQUEST = new Gson().toJson(CRR_REGISTER_FAIL);
     			testFailCreateMember(REGISTER_FAIL_REQUEST);
     			
         		
         	}
         } catch (IOException ioe) {
         	Assert.fail("Invalid:" + ioe.getMessage());
         }

    }
}
