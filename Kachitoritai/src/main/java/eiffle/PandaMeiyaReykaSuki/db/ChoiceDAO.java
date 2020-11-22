package eiffle.PandaMeiyaReykaSuki.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import eiffle.PandaMeiyaReykaSuki.model.Choice;



public class ChoiceDAO {



	java.sql.Connection conn;
	
	final String tblName = "Choice"; 
	
	public ChoiceDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
	public boolean addChoice(Choice choice) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ChoiceID = ?;");
			ps.setString(1, choice.choiceID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {            
				return false;
	        }
			
			ps = conn.prepareStatement("INSERT INTO " + tblName + " "
					+ "(choiceID, numberOfAlternative, description, userLimit, dateCreated, userRegistered, completed) "
					+ "values(?,?,?,?,?,?,?);");
			ps.setString(1,  choice.choiceID);
            ps.setDouble(2,  choice.numAlt);
            ps.setString(3,  choice.description);
            ps.setInt(4,  choice.limitMember);
            ps.setString(5,  choice.localDateTime);
            ps.setInt(6, 0);
            ps.setBoolean(7, false);
            ps.execute();
            return true;
			 
		} catch (Exception e) {
			throw new Exception("Falied to inser Choice: " + e.getMessage());
		}

	}
	
	public boolean getChoice(String choiceID) throws Exception {
		//TODO
		return false;
	}
}
