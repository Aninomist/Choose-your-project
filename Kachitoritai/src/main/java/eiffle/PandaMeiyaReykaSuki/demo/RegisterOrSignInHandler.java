package eiffle.PandaMeiyaReykaSuki.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import eiffle.PandaMeiyaReykaSuki.db.*;
import eiffle.PandaMeiyaReykaSuki.http.RegisterOrSignInRequest;
import eiffle.PandaMeiyaReykaSuki.http.RegisterOrSignInResponse;
import eiffle.PandaMeiyaReykaSuki.model.Member;

public class RegisterOrSignInHandler implements RequestHandler<RegisterOrSignInRequest, RegisterOrSignInResponse> {

	LambdaLogger logger;

	String choiceID;

	public RegisterOrSignInHandler() {
	}

	@Override
	public RegisterOrSignInResponse handleRequest(RegisterOrSignInRequest input, Context context) {
		logger = context.getLogger();
		logger.log(input.toString());
		RegisterOrSignInResponse response;

		try {
			if (checkMemberExist(input.username)) {
				if (checkPassword(input.username,input.password)) {
					response = new RegisterOrSignInResponse(input.username);
					return response;
				} else {
					response = new RegisterOrSignInResponse("Username and password does not match");
					return response;
				}
			}
			if (input.password == null) {
				if (createMember(input.username, input.choiceID)) {
					response = new RegisterOrSignInResponse(input.username);
				} else {
					response = new RegisterOrSignInResponse("unable to create user");
				}
			} else {
				if (createMember(input.username, input.choiceID, input.password)) {
					response = new RegisterOrSignInResponse(input.username);
				} else {
					response = new RegisterOrSignInResponse("unable to create user");
				}
			}
		} catch (Exception e) {
			response = new RegisterOrSignInResponse("unable to create user");
		}

		return response;
	}

	boolean checkChoiceExist(String choiceID) throws Exception {
		if (logger != null) {
			logger.log("in checkChoiceExist");
		}
		ChoiceDAO dao = new ChoiceDAO();
		return dao.existChoice(choiceID);
	}

	boolean createMember(String username, String choiceID, String password) throws Exception {
		if (logger != null) {
			logger.log("in createChoice");
		}
		if (!checkChoiceExist(choiceID)) {
			return false; // Choice do not exist
		}

		MemberDAO dao = new MemberDAO();

		Member member = new Member(username, choiceID, password);
		return dao.addMember(member);
	}

	boolean createMember(String username, String choiceID) throws Exception {
		if (logger != null) {
			logger.log("in createChoice");
		}
		if (!checkChoiceExist(choiceID)) {
			return false; // Choice do not exist
		}

		MemberDAO dao = new MemberDAO();

		Member member = new Member(username, choiceID);
		return dao.addMember(member);
	}

	boolean checkMemberExist(String username) throws Exception {
		if (logger != null) {
			logger.log("in checkMemberExist");
		}
		MemberDAO dao = new MemberDAO();
		if (dao.checkMemberExist(username)) {
			return true;
		}

		return false;
	}

	boolean checkPassword(String username, String password) throws Exception {
		if (logger != null) {
			logger.log("in checkPassword");
		}
		MemberDAO dao = new MemberDAO();
		if (dao.checkPassword(username, password)) {
			return true;
		}
		return false;
	}

}
