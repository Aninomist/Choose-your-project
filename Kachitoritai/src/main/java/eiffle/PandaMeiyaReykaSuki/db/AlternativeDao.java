package eiffle.PandaMeiyaReykaSuki.db;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import eiffle.PandaMeiyaReykaSuki.model.Alternative;

public class AlternativeDao {

	java.sql.Connection conn;
	
	final String tblName = "Alternative"; 
	
	public AlternativeDao() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
	public boolean createAlternatives(int numAlt, String choiceID) throws Exception {
		try {
			for (int i = 0; i < numAlt; i++) {
				String altID = UUID.randomUUID().toString();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName +
						" (altID, choiceID, isFinalAlternative) values (?,?,FALSE)");
				ps.setString(1, altID);
				ps.setString(2, choiceID);
				ps.execute();
			}
			return true;
			
		} catch(Exception e) {
			throw new Exception("Falied to create Alternatives: " + e.getMessage());
		}
	}
	
	public List<Alternative> getAlternatives(int numAlt, String choiceID) throws Exception {
		List<Alternative> allAlternatives = new ArrayList<>();
		try {
	
	
			
		} catch(Exception e) {
			throw new Exception("Falied to create Alternatives: " + e.getMessage());
		}
	}
}
