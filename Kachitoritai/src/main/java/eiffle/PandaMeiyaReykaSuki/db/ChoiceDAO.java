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
	
	public boolean existChoice(String choiceID) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ChoiceID = ?;");
			ps.setString(1, choiceID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				return true;
	        }
			
			return false;
			
		} catch (Exception e) {
			System.out.println("exception caught in existChoice");
			throw new Exception("Falied to checking if ChoiceID exist: " + e.getMessage());
		}

	}
	
	public boolean addChoice(Choice choice) throws Exception {
		try {
			//System.out.println("prep statement 1 where choise = ");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ChoiceID = ?;");
			ps.setString(1, choice.choiceID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				System.out.println("System generated a duplicate UUID!");
				return false;
	        }
			
			
			System.out.println("prep statement 2 to insert");
			ps = conn.prepareStatement("INSERT INTO " + tblName
					+ " (choiceID, description, numberOfAlternative, dateCreated, userLimit, userRegistered, completed) values(?,?,?,?,?,?,FALSE);");
			ps.setString(1,  choice.choiceID);
			ps.setString(2,  choice.description);
            ps.setInt(3,  choice.numAlt);
            ps.setString(4,  choice.dateCreated);
            ps.setInt(5,  choice.limitMember);
            ps.setInt(6, 0);
            System.out.println("etatement 2 execute:" + ps);
            ps.execute();
            return true;	 
            
		} catch (Exception e) {
			System.out.println("exception caught in addChoice");
			throw new Exception("Falied to insert Choice: " + e.getMessage());
		}

	}
	
	public boolean deleteChoice(String choiceID) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE choiceID = ?;");
			ps.setString(1, choiceID);
			ps.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("exception caught in deleteChoice");
			throw new Exception("Falied on deleteChoice: " + e.getMessage());
		}
	}
	
	public List<Choice> getAllChoices() throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName +";");
			ResultSet resultSet = ps.executeQuery();
			
			List<Choice> listOfChoices = new ArrayList<>();
			
			while (resultSet.next() ) {
				Choice choice = constructChoice(resultSet);
				listOfChoices.add(choice);
			}
			
			return listOfChoices;

		} catch (Exception e) {
			System.out.println("exception caught in getAllChoices");
			throw new Exception("Falied to get getAllChoices: " + e.getMessage());
		}
	}
	
	public boolean getChoice(String choiceID) throws Exception {
		//TODO
		return false;
	}
	
	
	private Choice constructChoice(ResultSet result) throws Exception {
		return new Choice(
				result.getString("choiceID"),
				result.getString("description"),
				result.getInt("numberOfAlternative"),
				result.getString("dateCreated"),
				result.getInt("userLimit"),
				result.getInt("userRegistered"),
				result.getBoolean("completed"));
	}

	public String getChoiceDescription(String choiceID) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ChoiceID = ?;");
			ps.setString(1, choiceID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				return resultSet.getString("description");
	        }
			
			return resultSet.getString("description");
			
		} catch (Exception e) {
			System.out.println("exception caught in existChoice");
			throw new Exception("Falied to checking if ChoiceID exist: " + e.getMessage());
		}

	}

	public boolean checkComplete(String choiceID) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ChoiceID = ?;");
			ps.setString(1, choiceID);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				return resultSet.getBoolean("completed");
	        }
			
			return resultSet.getBoolean("completed");
			
		} catch (Exception e) {
			System.out.println("exception caught in existChoice");
			throw new Exception("Falied to check if choice Completed: " + e.getMessage());
		}
	}
	
	public boolean completeChoice(String choiceID) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE " + tblName + " SET completed = true WHERE choiceID = ?;");
			ps.setString(1, choiceID);
			ps.executeUpdate();
			return true;
		} catch (Exception  e) {
			System.out.println("exception caught in completeChoice");
			throw new Exception("Falied to completeChoice: " + e.getMessage());
		}
	}

	public boolean memeberIsFull(String choiceID) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ChoiceID = ?;");
			ps.setString(1, choiceID);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();

			int userLimit = resultSet.getInt("userLimit");
			int userRegistered = resultSet.getInt("userRegistered");
			
			return userRegistered >= userLimit;
			
		} catch (Exception e) {
			System.out.println("exception caught in memeberIsFull");
			throw new Exception("Falied to check if choice memeberIsFull: " + e.getMessage());
		}
	}

	public boolean addMember(String choiceID) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ChoiceID = ?;");
			ps.setString(1, choiceID);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();

			int userLimit = resultSet.getInt("userLimit");
			int userRegistered = resultSet.getInt("userRegistered");
			
			if(userRegistered >= userLimit) return false;
			
			userRegistered++;
			PreparedStatement ps2 = conn.prepareStatement("UPDATE " + tblName + " SET userRegistered = ? WHERE choiceID = ?;");
			ps2.setInt(1, userRegistered);
			ps2.setString(2, choiceID);
			ps2.executeUpdate();
			
			return true;
			
			
		} catch (Exception e) {
			System.out.println("exception caught in addMember");
			throw new Exception("Falied to addMember (increment userRegistered in choice): " + e.getMessage());
		}
	}
}
