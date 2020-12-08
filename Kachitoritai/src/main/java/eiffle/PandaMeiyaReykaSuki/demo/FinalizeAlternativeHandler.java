package eiffle.PandaMeiyaReykaSuki.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import eiffle.PandaMeiyaReykaSuki.db.AlternativeDao;
import eiffle.PandaMeiyaReykaSuki.db.ChoiceDAO;
import eiffle.PandaMeiyaReykaSuki.http.CreateFeedBackResponse;
import eiffle.PandaMeiyaReykaSuki.http.FinalizeAlternativeRequest;
import eiffle.PandaMeiyaReykaSuki.http.FinalizeAlternativeResponse;
import eiffle.PandaMeiyaReykaSuki.http.GetAlternativeResponse;
import eiffle.PandaMeiyaReykaSuki.http.RegisterOrSignInResponse;

public class FinalizeAlternativeHandler implements RequestHandler<FinalizeAlternativeRequest, FinalizeAlternativeResponse>{
	LambdaLogger logger;
	
	boolean exist(String choiceID) throws Exception{
		if(logger != null) {logger.log("in exist");}
		ChoiceDAO daoC = new ChoiceDAO();
		//AlternativeDao daoA = new AlternativeDao();
		return (daoC.existChoice(choiceID));
	}
	
	boolean finalizeAlternative(String altID) throws Exception{
		if(logger != null) {logger.log("in finalizeAlternative");}
		AlternativeDao daoA = new AlternativeDao();
		return daoA.finalizeAlt(altID);
	}

	boolean FinalizeChoice(String choiceID) throws Exception{
		if(logger != null) {logger.log("in FinalizeChoice");}
		ChoiceDAO dao = new ChoiceDAO();
		return dao.completeChoice(choiceID);
	}
	
	boolean checkChoiceComplete(String choiceID) throws Exception{
		if(logger != null) {logger.log("in FinalizeChoice");}
		ChoiceDAO dao = new ChoiceDAO();
		return dao.checkComplete(choiceID);
	}
	
	
	@Override 
	public FinalizeAlternativeResponse handleRequest(FinalizeAlternativeRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		FinalizeAlternativeResponse response;
		
		try {
			if(!exist(req.choiceID)) {
				response = new FinalizeAlternativeResponse("Choice does not exist", 400);
			} else if(checkChoiceComplete(req.choiceID)) {
				response = new FinalizeAlternativeResponse("Choice already complete, unable to finalize", 405);
			} else if(finalizeAlternative(req.altID)) {
				if(FinalizeChoice(req.choiceID))
					response = new FinalizeAlternativeResponse("Choice Finalized");
				else 
					response = new FinalizeAlternativeResponse("Filed to set choice complete", 400);
			} else {
				response = new FinalizeAlternativeResponse("Filed to set alternative complete", 400);
			}
			
		} catch (Exception e){
			response = new FinalizeAlternativeResponse("unable to create user Exception caught:(" + e.getMessage() + ")", 400);
		}
		
		return response;
	}
}
