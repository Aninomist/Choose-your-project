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
}
