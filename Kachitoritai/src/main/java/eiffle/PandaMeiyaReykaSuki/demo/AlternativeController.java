package eiffle.PandaMeiyaReykaSuki.demo;

import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import eiffle.PandaMeiyaReykaSuki.db.AlternativeDao;
import eiffle.PandaMeiyaReykaSuki.db.FeedBackDAO;
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
    	
    	return dao.getListAlternatives(choiceID);
    }
    
    
    public List<Alternative> getListAlternativesWithFeedback(String choiceID) throws Exception {
    	if (logger != null) { logger.log("in getListAlternativewWithFeedback"); }
    	
    	AlternativeDao daoAlt = new AlternativeDao();
    	List<Alternative> altList =  daoAlt.getListAlternatives(choiceID);
    	
    	FeedBackDAO daoFB = new FeedBackDAO();
    	for(Alternative alt : altList) {
    		alt.feedBacks = daoFB.getFeedBacks(alt.altID);
    	}
    	
    	return altList;
    }
}
