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
	public RegisterOrSignInResponse handleRequest(RegisterOrSignInRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		RegisterOrSignInResponse response;

		try {
			if(!checkChoiceExist(req.choiceID)) {
				response = new RegisterOrSignInResponse("Choice does not exist", 400); 
			} else {				
				/*status for checkMemeber Exist:
				 * 0: username exist, and match the given choiceID
				 * 1: username exist, but deson't mathch the given choiceID
				 * 2: username does not exist;
				 */
				int status = checkMemberExist(req.username, req.choiceID);
				if(status == 0) {
					//check password
					if (checkPassword(req.username,req.password)) {
						response = new RegisterOrSignInResponse(req.username, new AlternativeController().getListAlternative(req.choiceID));
					} else {
						response = new RegisterOrSignInResponse("Username and password does not match", 400);
					}
				} else if (status == 1) {
					response = new RegisterOrSignInResponse("Username already exist, choose a new one", 400);
				} else {
					//createMemebr
					if (createMember(req.username, req.choiceID, req.password)) {
						response = new RegisterOrSignInResponse(req.username, new AlternativeController().getListAlternative(req.choiceID));
					} else {
						response = new RegisterOrSignInResponse("Memeber Limit Already reached", 405);
					}
				}
			}
	
		} catch (Exception e) {
			response = new RegisterOrSignInResponse("unable to create user Exception caught:(" + e.getMessage() + ")", 400);
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

	
	int checkMemberExist(String username, String choiceID) throws Exception {
		/*status for checkMemeber Exist:
		 * 0: username exist, and match the given choiceID
		 * 1: username exist, but deson't mathch the given choiceID
		 * 2: username does not exist;
		 */
		if (logger != null) {
			logger.log("in checkMemberExist");
		}
		MemberDAO dao = new MemberDAO();
		if(dao.checkMemberExist(username)) {
			if(dao.getChoiceID(username).equals(choiceID)) return 0;
			return 1;
			
		} else return 2; //usename does not exist
	}
	
	
	
	boolean checkPassword(String username, String password) throws Exception {
		if (logger != null) {
			logger.log("in checkPassword");
		}
		logger.log("$$$$$$$$" + password + "$$$$$$$$$$");
		MemberDAO dao = new MemberDAO();
		if (dao.checkPassword(username, password)) {
			return true;
		}
		return false;
	}
	
	
//	boolean memberIsFull(String choiceID) throws Exception  {
//		if (logger != null) {
//			logger.log("in memberIsFull");
//		}
//		ChoiceDAO dao = new ChoiceDAO();
//		return dao.memeberIsFull(choiceID);
//	}


	
	boolean createMember(String username, String choiceID, String password) throws Exception {
		if (logger != null) {
			logger.log("in createMember");
		}
		
		ChoiceDAO daoChoice = new ChoiceDAO();
		MemberDAO daoMember = new MemberDAO();
		
		if(daoChoice.memeberIsFull(choiceID)) return false;
		daoMember.addMember(new Member(username, choiceID, password));
		return daoChoice.addMember(choiceID);

	}



}
