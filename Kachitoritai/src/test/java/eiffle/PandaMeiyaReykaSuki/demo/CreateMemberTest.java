package eiffle.PandaMeiyaReykaSuki.demo;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.amazonaws.services.lambda.runtime.Context;

import eiffle.PandaMeiyaReykaSuki.http.ChoiceRequest;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceResponse;
import eiffle.PandaMeiyaReykaSuki.http.RegisterOrSignInRequest;
import eiffle.PandaMeiyaReykaSuki.http.RegisterOrSignInResponse;
import eiffle.PandaMeiyaReykaSuki.demo.LambdaTest;

public class CreateMemberTest extends LambdaTest {

	void testSuccessInput(String incoming) throws IOException {
		RegisterOrSignInHandler handler = new RegisterOrSignInHandler();
		RegisterOrSignInRequest req = new Gson().fromJson(incoming, RegisterOrSignInRequest.class);

		RegisterOrSignInResponse resp = handler.handleRequest(req, createContext("create"));
		Assert.assertEquals(200, resp.httpCode);
	}

	@Test
	public void test() {
		RegisterOrSignInRequest rosir = new RegisterOrSignInRequest("f04a274d-2bd8-49b5-926b-5c221db7062f", "sdfsfadfasdfa","shjsdhf");

		String SAMPLE_INPUT_STRING = new Gson().toJson(rosir);

		try {
			testSuccessInput(SAMPLE_INPUT_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}
}
