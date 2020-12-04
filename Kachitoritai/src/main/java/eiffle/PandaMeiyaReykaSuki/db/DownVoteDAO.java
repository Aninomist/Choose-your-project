package eiffle.PandaMeiyaReykaSuki.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eiffle.PandaMeiyaReykaSuki.model.FeedBack;
import eiffle.PandaMeiyaReykaSuki.model.Vote;

public class DownVoteDAO {
	java.sql.Connection conn;
	
	final String tblName = "DownVote"; 
	
	public DownVoteDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
	
	public List<String> getDownVotes(String altID) throws Exception {
		
		try {
			List<String> downVotes = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE altID = ?;");
			ps.setString(1, altID);
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				downVotes.add(resultSet.getString("username"));
			}
			
			return downVotes;
		} catch (Exception e) {
			System.out.println("exception caught in getUpVotes");
			throw new Exception("Falied to get up votes: " + e.getMessage());
		}
	}
	
	
	public boolean createDownVote(Vote vote) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE voteID = ?;");
			ps.setString(1, vote.voteID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				System.out.println("System generated a duplicate UUID!");
				return false;
	        }
			
			ps = conn.prepareStatement("INSERT INTO " + tblName
					+ " (voteID, altID, username, dateCreated) values(?,?,?,?);");
			ps.setString(1,  vote.voteID);
			ps.setString(2,  vote.altID);
            ps.setString(3,  vote.username);
            ps.setString(4,  vote.dateCreated);
            ps.execute();
            return true;	 
		} catch (Exception e) {
			System.out.println("exception caught in createDownVote");
			throw new Exception("Falied to createDownVote: " + e.getMessage());
		}
	}
	
	public boolean userHasDownVoted(Vote vote) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE altID = ? AND username = ?;");
			ps.setString(1, vote.altID);
			ps.setString(2, vote.username);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				System.out.println("User has already Down Voted in this alternative");
				resultSet.close();
				return true;
	        }
			
			return false;
		} catch (Exception e) {
			System.out.println("exception caught in userHasDownVoted");
			throw new Exception("Falied check userHasDownVoted: " + e.getMessage());
		}
	}
	
	public boolean deleteDownVote(Vote vote) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE altID = ? AND username = ?;");
			ps.setString(1, vote.altID);
			ps.setString(2, vote.username);
			ps.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("exception caught in deleteDownVote");
			throw new Exception("Falied on deleteDownVote: " + e.getMessage());
		}
	}
	
}
