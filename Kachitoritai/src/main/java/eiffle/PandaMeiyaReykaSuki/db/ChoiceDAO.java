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
		
		//TODO
		return false;
	}
	
	public boolean getChoice(String choiceID) throws Exception {
		//TODO
		return false;
	}
}
