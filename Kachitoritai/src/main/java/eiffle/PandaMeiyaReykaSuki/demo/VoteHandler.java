package eiffle.PandaMeiyaReykaSuki.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import eiffle.PandaMeiyaReykaSuki.db.AlternativeDao;
import eiffle.PandaMeiyaReykaSuki.db.ChoiceDAO;
import eiffle.PandaMeiyaReykaSuki.db.DownVoteDAO;
import eiffle.PandaMeiyaReykaSuki.db.UpVoteDAO;
import eiffle.PandaMeiyaReykaSuki.http.ChoiceResponse;
import eiffle.PandaMeiyaReykaSuki.http.RegisterOrSignInResponse;
import eiffle.PandaMeiyaReykaSuki.http.VoteRequest;
import eiffle.PandaMeiyaReykaSuki.http.VoteResponse;
import eiffle.PandaMeiyaReykaSuki.model.Vote;


public class VoteHandler implements RequestHandler<VoteRequest , VoteResponse> {
	
	LambdaLogger logger;
	public VoteHandler() {}
	
	
	
	@Override
	public VoteResponse handleRequest(VoteRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		VoteResponse response;
		
		try {
			
			if(new AlternativeController().choiceCompleted(req.altID)) {
				response = new VoteResponse("Choice has already concluded, you can't vote anymore", 405);
			} else {
				
				Vote vote = new Vote(req.altID, req.username, req.isUpVote);
				UpVoteDAO daoUV = new UpVoteDAO();
		    	DownVoteDAO daoDV = new DownVoteDAO();
		    	boolean upVoted = daoUV.userHasUpVoted(vote);
		    	boolean downVoted = daoDV.userHasDownVoted(vote);
		    	
		    	
		    	//User has voted in both
		    	if(upVoted && downVoted) {
		    		response = new VoteResponse("Fatal Error, use has both upvoted and down voted", 400);
		    	}else
		    	
		    	
		    		
		    	//User has not voted
		    	if(!upVoted && !downVoted) {
		    		if(req.isUpVote) {
		    			if(daoUV.createUpVote(vote))
		    				response = new VoteResponse("UpVote created for user" + req.username + "on Alt: " + req.altID);
		    			else 
		    				response =  new VoteResponse("Something went wrong on user has not voted and tried to create up vote", 400);
		    		} else {
		    			if(daoDV.createDownVote(vote))
		    				response = new VoteResponse("DownVote created for user" + req.username + "on Alt: " + req.altID);
		    			else 
		    				response =  new VoteResponse("Something went wrong on user has not voted and tried to create down vote", 400);
		    		}
		    	}else
		    	
		    	
		    	
		    	
		    	//User upVoted and upVote gain to cancel it
		    	if(upVoted && req.isUpVote) {
		    		if(daoUV.deleteUpVote(vote)) {
		    			response = new VoteResponse("Up vote deleted");
		    		} else {
		    			response =  new VoteResponse("Something went wrong deleting UpVote", 400);
		    		}
		    	}else
		    	
		    	
		    	
		    	//User downVoted and downVote again to cancel it
		    	if(downVoted && !req.isUpVote) {
		    		if(daoDV.deleteDownVote(vote)) {
		    			response = new VoteResponse("Down vote deleted");
		    		} else {
		    			response =  new VoteResponse("Something went wrong deleting Down Vote", 400);
		    		}
		    	}else
		    	
		    	
		    	
		    	//User has UpVoted, and DownVote to cancel upVote and insert DownVote
		    	if(upVoted && !req.isUpVote) {
		    		if(daoUV.deleteUpVote(vote) && daoDV.createDownVote(vote)) {
		    			response = new VoteResponse("Up Vote deleted, Down Vote Created");
		    		} else 
		    			response = new VoteResponse("Something went wrong deleting Up Vote and inserting Down Vote", 400);
		    	}else
		    	
		    	
		    	
		    	//User has DownVoted, and UpVote to cancel DownVote and insert UpVote
		    	if(downVoted && req.isUpVote) {
		    		if(daoDV.deleteDownVote(vote) && daoUV.createUpVote(vote)) {
		    			response = new VoteResponse("Down Vote deleted, Up Vote Created");
		    		} else 
		    			response = new VoteResponse("Something went wrong deleting Dwon Vote and inserting Up Vote", 400);
		    	} else response = new VoteResponse("no matched conditions, weird", 404);  	
			}
	    	
		} catch (Exception e) {
			response = new VoteResponse("Unable to create vote: " + req.altID + "(" + e.getMessage() + ")", 400);
		}
		
		
		return response;
		
	}
	
	
}
