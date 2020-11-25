package eiffle.PandaMeiyaReykaSuki.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import eiffle.PandaMeiyaReykaSuki.db.AlternativeDao;
import eiffle.PandaMeiyaReykaSuki.model.Alternative;

public class AlternativeController {
	
	AlternativeController(){}

	LambdaLogger logger;
	
    public boolean createAlternatives(String choiceID, int numAlt, List<String> altDescription) throws Exception {
    	if (logger != null) { logger.log("in createAlternatives"); }
    	AlternativeDao dao = new AlternativeDao();

    	return dao.createAlternatives(numAlt, choiceID, altDescription);
    }
    	
    
    public List<Alternative> getListAlternative(String choiceID) throws Exception{
    	if (logger != null) { logger.log("in getListAlternative"); }
    	AlternativeDao dao = new AlternativeDao();
    	
    	return dao.getAlternatives(choiceID);
    }
}
