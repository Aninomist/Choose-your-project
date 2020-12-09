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
import eiffle.PandaMeiyaReykaSuki.http.FinalizeAlternativeRequest;
import eiffle.PandaMeiyaReykaSuki.http.FinalizeAlternativeResponse;

public class FinalizeAlternativeAndChoiceHandlerTest extends LambdaTest {

	  void testSuccessInput(String incoming) throws IOException {
		    FinalizeAlternativeHandler handler = new FinalizeAlternativeHandler();
	    	FinalizeAlternativeRequest req = new Gson().fromJson(incoming, FinalizeAlternativeRequest.class);
	       
	    	FinalizeAlternativeResponse resp = handler.handleRequest(req, createContext("create"));
	    	System.out.println(resp.toString());
	        Assert.assertEquals(200, resp.httpCode);
	    }
	
	 @Test
	    public void test() {
	    	
		 FinalizeAlternativeRequest ccr = new FinalizeAlternativeRequest("ab7cdd14-7301-4930-81f6-1dfb6c648651","378d6e3b-aa96-42af-8b71-3939217b22e2");
	    	
	    	
	    	String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
	    	
	    	System.out.println(SAMPLE_INPUT_STRING);
	    	
	    	 try {
	         	testSuccessInput(SAMPLE_INPUT_STRING);
	         } catch (IOException ioe) {
	         	Assert.fail("Invalid:" + ioe.getMessage());
	         }

	    }
}
