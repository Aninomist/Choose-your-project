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
	
	public boolean createFeedBack(FeedBack feedBack) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE feedBackID = ?;");
			ps.setString(1, feedBack.feedBackID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				System.out.println("System generated a duplicate UUID!");
				return false;
	        }
			
			
			ps = conn.prepareStatement("INSERT INTO " + tblName
					+ " (feedBackID, altID, username, dateCreated, description) values(?,?,?,?,?);");
			ps.setString(1,  feedBack.feedBackID);
			ps.setString(2,  feedBack.altID);
            ps.setString(3,  feedBack.username);
            ps.setString(4,  feedBack.dateCreated);
            ps.setString(5,  feedBack.description);
            ps.execute();
            return true;	 
            
		} catch (Exception e) {
			System.out.println("exception caught in createFeedBack");
			throw new Exception("Falied to insert feedBack: " + e.getMessage());
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
				result.getString("dateCreated"),
				result.getString("altID"),
				result.getString("description")
				);
	}
}
