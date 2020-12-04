package eiffle.PandaMeiyaReykaSuki.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import eiffle.PandaMeiyaReykaSuki.model.Alternative;
import eiffle.PandaMeiyaReykaSuki.model.FeedBack;

public class FeedBackDAO {

java.sql.Connection conn;
	
	final String tblName = "Feedback"; 
	
	public FeedBackDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
	public List<FeedBack> getFeedBacks(String altID) throws Exception {
		try {
			List<FeedBack> feedBacks = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE altID = ?;");
			ps.setString(1, altID);
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				FeedBack feedback = generateFeedBack(resultSet);
				feedBacks.add(feedback);
			}
			
			return feedBacks;
			
		} catch (Exception e) {
			System.out.println("exception caught in getFeedBacks");
			throw new Exception("Falied to get FeedBacks: " + e.getMessage());
		}
	}
	
	
	private FeedBack generateFeedBack(ResultSet result) throws Exception {
		return new FeedBack(
				result.getString("feedBackID"),
				result.getString("username"),
				result.getString("dateCreated")
				);
	}
}
