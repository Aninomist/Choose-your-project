package eiffle.PandaMeiyaReykaSuki.db;

import java.sql.*;
import eiffle.PandaMeiyaReykaSuki.model.*;
import eiffle.PandaMeiyaReykaSuki.db.ChoiceDAO;


public class MemberDAO {

java.sql.Connection conn;
	
	final String tblName = "User"; 
	
	public MemberDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	public boolean checkMemberExist(String username) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE username = ?;");
			ps.setString(1, username);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				System.out.println("exist in db");
				return true;
	        }
			
			return false;
			
		} catch (Exception e) {
			System.out.println("exception caught in existChoice");
			throw new Exception("Falied to checking if Member exist: " + e.getMessage());
		}
	}
	
	public boolean addMember(Member member) throws Exception {
		try {
			/*System.out.println("prep statement 1 to check if choice exists ");
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + "Choice" + " WHERE ChoiceID = ?;");
			ps.setString(1, choice.choiceID);
			ResultSet resultSet = ps.executeQuery();
			
			while (!resultSet.next()) {
				System.out.println("Does not exist in db");
				return false;
	        }
			ChoiceDAO cdao = new ChoiceDAO();
			if(!new ChoiceDAO().existChoice(choice.choiceID)) {
				System.out.println("Does not exist in db");
				return false;
			}*/
			
			System.out.println("prep statement 2 to insert");
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName
					+ " (username, choiceID, password) values(?,?,?);");
			ps.setString(1,  member.username);
			ps.setString(2,  member.choiceID);
            ps.setString(3,  member.password);

            System.out.println("etatement 2 execute:" + ps);
            ps.execute();
            return true;	 
            
		} catch (Exception e) {
			System.out.println("exception caught in addMember");
			throw new Exception("Falied to insert Member: " + e.getMessage());
		}

	}
	public boolean checkPassword(String username, String password) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE username = ? and password IS NULL;");
			ps.setString(1, username);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				System.out.println("User without password exist in db");
				return true;
	        }
			
			ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE username = ?;");
			ps.setString(1, username);
			resultSet = ps.executeQuery();
			
			while (!resultSet.next()) {
				System.out.println("User does not exist in db");
				return false;
	        }
			// TODO compare password here.
			if (resultSet.getString(password).equals(password)) {
				
				return true;
			}
			return false;
			
		} catch (Exception e) {
			System.out.println("Other exception caught in checkPassword");
			throw new Exception("Falied to checking if Member exist: " + e.getMessage());
		}
	}
}
