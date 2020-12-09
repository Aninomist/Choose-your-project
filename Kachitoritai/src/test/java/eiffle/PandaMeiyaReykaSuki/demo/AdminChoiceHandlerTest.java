package eiffle.PandaMeiyaReykaSuki.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import eiffle.PandaMeiyaReykaSuki.http.AdminChoiceRequest;
import eiffle.PandaMeiyaReykaSuki.http.AdminChoiceResponse;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceRequest;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceResponse;

public class AdminChoiceHandlerTest extends LambdaTest {

	  void testSuccessInput(String incoming) throws IOException {
	    	AdminChoiceHandler handler = new AdminChoiceHandler();
	    	AdminChoiceRequest req = new Gson().fromJson(incoming, AdminChoiceRequest.class);
	       
	    	AdminChoiceResponse resp = handler.handleRequest(req, createContext("create"));
	    	System.out.println(resp.toString());
	        Assert.assertEquals(200, resp.httpCode);
	    }
	
	 @Test
	    public void testGetAllChoices() {
	    	
	    	AdminChoiceRequest ccr = new AdminChoiceRequest(-1);
	    	
	    	
	    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
	    	
	    	System.out.println(SAMPLE_INPUT_STRING);
	    	
	    	 try {
	         	testSuccessInput(SAMPLE_INPUT_STRING);
	         } catch (IOException ioe) {
	         	Assert.fail("Invalid:" + ioe.getMessage());
	         }

	    }
	 
	 @Test
	    public void testDeleteChoice200DaysOld() {
	    	
	    	AdminChoiceRequest ccr = new AdminChoiceRequest(200);
	    	
	    	
	    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
	    	
	    	System.out.println(SAMPLE_INPUT_STRING);
	    	
	    	 try {
	         	testSuccessInput(SAMPLE_INPUT_STRING);
	         } catch (IOException ioe) {
	         	Assert.fail("Invalid:" + ioe.getMessage());
	         }

	    }
}

