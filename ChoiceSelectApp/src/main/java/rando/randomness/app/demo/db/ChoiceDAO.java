package rando.randomness.app.demo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//import edu.wpi.cs.heineman.demo.model.Constant;
import rando.randomness.app.demo.db.DatabaseUtil;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Feedback;
import rando.randomness.app.demo.model.Member;
import rando.randomness.app.demo.model.Team;
import rando.randomness.app.demo.model.Alternative;
public class ChoiceDAO {
	java.sql.Connection conn;
	
	final String cName = "Choice";
	final String aName = "Alternative";
	final String apName = "APP/DIS";
	final String fName = "Feedback";
	final String mName = "Member";
	final String tName = "Team";// Exact capitalization
	
	public ChoiceDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
	//Adds team to the database and returns the ID
			public String addMember(Member m, String tID) throws Exception
			{
				try {
		        	
		            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + mName + " (MID, name, password, admin, TID) values(?,?,?,?,?);");
		            String mID = UUID.randomUUID().toString();
		            ps.setString(1,  mID);
		            ps.setString(2, m.getName());
		            ps.setString(3, m.getPassword());
		            ps.setInt(4, 0);
		            ps.setString(5, tID);
		            ps.execute();
		           
		            //for(Alternative a: c.getAlternatives())
		            //{
		            //	addAlternative(a, cID);
		            //}
		            return tID;

		        } catch (Exception e) {
		            throw new Exception("Failed to insert team: " + e.getMessage());
		        }
			}
	//Adds team to the database and returns the ID
		public String addTeam(Team t) throws Exception
		{
			try {
	        	
	            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tName + " (TID) values(?);");
	            String tID = UUID.randomUUID().toString();
	            ps.setString(1,  tID);
	            ps.execute();
	           
	            for(Member m: t.getMembers())
	            {
	            	addMember(m, tID);
	            }
	            return tID;

	        } catch (Exception e) {
	            throw new Exception("Failed to insert team: " + e.getMessage());
	        }
		}
	
	//Adds Choice to the database and returns the ID
	public Choice addChoice(Choice c, String tID) throws Exception
	{
		try {
        	
			Choice updatedChoice = c;
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + cName + " (CID,description,dateOfCreation,dateOfCompletion,finalChoice,TID) values(?,?,?,?,?,?);");
            //String cID = UUID.randomUUID().toString();
            String cID = tID;
            updatedChoice.setID(cID);
            ps.setString(1,  cID);
            ps.setString(2,  c.getDescription());
            ps.setString(3,  c.getCreationDate().toString());
            ps.setString(4, null);
            ps.setInt(5, (Integer) null);
            ps.setString(6, tID);
            ps.execute();
            for(Alternative a: c.getAlternativeList())
            {
            	updatedChoice.addAlternative(addAlternative(a, cID));
            }
            return updatedChoice;

        } catch (Exception e) {
            throw new Exception("Failed to insert choice: " + e.getMessage());
        }
	}
	
	//Adds given alternative to the database (Does not add any feedback attached to it yet)
	public Alternative addAlternative(Alternative a, String cID) throws Exception
	{
		try {
        	Alternative newAlternative = a;
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + aName + " (AID,CID,description) values(?,?,?);");
            String aID = UUID.randomUUID().toString();
            newAlternative.setAID(aID);
            ps.setString(1,  aID);
            ps.setString(2,  cID);
            ps.setString(3,  a.getDescription());
            for(Feedback f: a.getFeebackList())
            {
            	newAlternative.addFeedback(addFeedback(f, aID, f.getCreator()));
            }
            ps.execute();
            return newAlternative;

        } catch (Exception e) {
            throw new Exception("Failed to insert alternative: " + e.getMessage());
        }
	}
	
	//Adds given feedback to the database
	//Requires the id of the alternative it belongs to
	public Feedback addFeedback(Feedback f, String aID, String creator) throws Exception
	{
		try {
            Feedback newFeedback = f;
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + fName + " (FID,AID,timestamp,description,MID) values(?,?,?,?,?);");
            String fID = UUID.randomUUID().toString();
            newFeedback.setFID(fID);
            ps.setString(1,  fID);
            ps.setString(2,  aID);
            ps.setString(3,  f.getTimestamp().toString());
            ps.setString(4, f.getDescription());
            ps.setString(5, creator);
            ps.execute();
            return newFeedback;

        } catch (Exception e) {
            throw new Exception("Failed to insert feedback: " + e.getMessage());
        }
	}
	
	
		public Team retrieveTeam(String tID) throws Exception
		{
			 
		        try {
		        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tName + " WHERE TID = ?;");
		            ps.setString(1, tID);
		            ResultSet resultSet = ps.executeQuery();

		            while (resultSet.next()) {
		            	Team t = generateTeam(resultSet);
		            	return t;
		            }
		            resultSet.close();
		            
		            return null;

		        } catch (Exception e) {
		            throw new Exception("Failed in getting team: " + e.getMessage());
		        }
		    
		}
	//Retrieves all members for the team with the given id
	public Choice retrieveChoice(String tID) throws Exception
	{
		 
	        try {
	        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + mName + " WHERE TID = ?;");
	            ps.setString(1, tID);
	            ResultSet resultSet = ps.executeQuery();

	            while (resultSet.next()) {
	            	Choice c = generateChoice(resultSet);
	            	return c;
	            }
	            resultSet.close();
	            
	            return null;

	        } catch (Exception e) {
	            throw new Exception("Failed in getting choice: " + e.getMessage());
	        }
	    
	}

	//Retrieves all alternatives for the choice with the given id
			public ArrayList<Alternative> retrieveAlternatives(String cID) throws Exception
			{
				 ArrayList<Alternative> allAlternatives = new ArrayList<>();
			        try {
			        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + aName + " WHERE CID = ?;");
			            ps.setString(1, cID);
			            ResultSet resultSet = ps.executeQuery();

			            while (resultSet.next()) {
			            	Alternative a = generateAlternative(resultSet);
			                allAlternatives.add(a);
			            }
			            resultSet.close();
			            
			            return allAlternatives;

			        } catch (Exception e) {
			            throw new Exception("Failed in getting alternatives: " + e.getMessage());
			        }
			    
			}
		
	//Retrieves all members for the team with the given id
		public ArrayList<Member> retrieveMembers(String tID) throws Exception
		{
			 ArrayList<Member> allMembers = new ArrayList<>();
		        try {
		        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + mName + " WHERE TID = ?;");
		            ps.setString(1, tID);
		            ResultSet resultSet = ps.executeQuery();

		            while (resultSet.next()) {
		            	Member m = generateMember(resultSet);
		                allMembers.add(m);
		            }
		            resultSet.close();
		            
		            return  allMembers;

		        } catch (Exception e) {
		            throw new Exception("Failed in getting feedback: " + e.getMessage());
		        }
		    
		}
	
	//Retrieves all feedback for the alternative with the given id
	public ArrayList<Feedback> retrieveFeedback(String aID) throws Exception
	{
		 ArrayList<Feedback> allFeedback = new ArrayList<>();
	        try {
	        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + fName + " WHERE AID = ?;");
	            ps.setString(1, aID);
	            ResultSet resultSet = ps.executeQuery();

	            while (resultSet.next()) {
	            	Feedback f = generateFeedback(resultSet);
	                allFeedback.add(f);
	            }
	            resultSet.close();
	            
	            return allFeedback;

	        } catch (Exception e) {
	            throw new Exception("Failed in getting feedback: " + e.getMessage());
	        }
	    
	}
	
	public boolean deleteTeam(String tID) throws Exception
	{
		 try {
	        	PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM " + tName + " WHERE TID = ?;");
	        	ps1.setString(1, tID);
	        	ResultSet resultSet = ps1.executeQuery();
	        	
	        	//Delete all alternatives for each choice
	        	while (resultSet.next())
	        	{
	        		Team t = generateTeam(resultSet);
	        		deleteChoice(t.getTID());
	        	}
	        	ps1.close();
	        	//Delete the team
	            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tName + " WHERE TID = ?;");
	            ps.setString(1, tID);
	            int numAffected = ps.executeUpdate();
	            ps.close();
	            
	            return (numAffected >= 1);

	        } catch (Exception e) {
	            throw new Exception("Failed to delete alternative: " + e.getMessage());
	        }
	}
	public boolean deleteMember(String tID) throws Exception
	{
		try {
       	 
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + mName + " WHERE TID = ?;");
            ps.setString(1, tID);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected >= 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete feedback: " + e.getMessage());
        }
	}
	
	public boolean deleteChoice(String tID) throws Exception
	{
		 try {
	        	PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM " + cName + " WHERE TID = ?;");
	        	ps1.setString(1, tID);
	        	ResultSet resultSet = ps1.executeQuery();
	        	
	        	//Delete all alternatives for each choice
	        	while (resultSet.next())
	        	{
	        		Choice c = generateChoice(resultSet);
	        		deleteAlternatives(c.getID());
	        	}
	        	ps1.close();
	        	//Delete the choice
	            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + cName + " WHERE TID = ?;");
	            ps.setString(1, tID);
	            int numAffected = ps.executeUpdate();
	            ps.close();
	            
	            return (numAffected >= 1);

	        } catch (Exception e) {
	            throw new Exception("Failed to delete alternative: " + e.getMessage());
	        }
	}
	//Deletes all alternatives for the choice with the given id
	public boolean deleteAlternatives(String cID) throws Exception {
        try {
        	PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM " + aName + " WHERE CID = ?;");
        	ps1.setString(1, cID);
        	ResultSet resultSet = ps1.executeQuery();
        	
        	//Delete all feedback for each alternative
        	while (resultSet.next())
        	{
        		Alternative a = generateAlternative(resultSet);
        		deleteFeedback(a.getAID());
        	}
        	ps1.close();
        	//Delete the alternatives
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + aName + " WHERE CID = ?;");
            ps.setString(1, cID);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected >= 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete alternative: " + e.getMessage());
        }
    }

	 public boolean deleteFeedback(String aID) throws Exception {
	        try {
	        	 
	            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + fName + " WHERE AID = ?;");
	            ps.setString(1, aID);
	            int numAffected = ps.executeUpdate();
	            ps.close();
	            
	            return (numAffected >= 1);

	        } catch (Exception e) {
	            throw new Exception("Failed to delete feedback: " + e.getMessage());
	        }
	    }

	
	public Choice generateChoice(ResultSet r) throws SQLException
	{
		Timestamp dateOfCreation = Timestamp.valueOf(r.getString("dateOfCreation"));
		String cID = r.getString("CID");
		String description = r.getString("description");
		Timestamp dateOfCompletion = Timestamp.valueOf(r.getString("dateOfCompletion"));
		Integer finalChoice = r.getInt("finalChoice");
		String tID = r.getString("TID");
		
		Choice c = new Choice(cID, description, dateOfCreation);
		c.setTID(tID);
		if(finalChoice != null)
		{
			c.completeChoice(finalChoice);
		}
		
		 //for(Alternative a: c.getAlternatives())
        //{
        //	c.setAlternatives(retrieveAlternatives(cID));
        //}
		return c;
	}
	
	public Team generateTeam(ResultSet r) throws Exception
	{
		Timestamp ti = Timestamp.valueOf(r.getString("timestamp"));
		String tid = r.getString("TID");
		ArrayList<Member> m = retrieveMembers(tid);
		Choice choice = retrieveChoice(tid);
		Team t = new Team((ArrayList<Member>) m, choice);
		 
		t.setTID(tid);
		return t;
	}
	
	public Feedback generateFeedback(ResultSet r) throws SQLException
	{
		Timestamp t = Timestamp.valueOf(r.getString("timestamp"));
		String desc = r.getString("description");
		String creator = r.getString("name");
		Feedback f = new Feedback(t, desc, creator);
		f.setFID(r.getString("FID"));
		return f;
	}
	
	public Member generateMember(ResultSet r) throws SQLException
	{
		String pass = r.getString("password");
		String name = r.getString("name");
		
		Member m = new Member(name, pass);
		m.setMID(r.getString("MID"));
		
		return m;
	}
	
	
	public Alternative generateAlternative(ResultSet r) throws SQLException
	{
		String desc = r.getString("description");
		String aID = r.getString("AID");
		Alternative a = new Alternative(desc);
		//Add approvers
		//Add disapprovers
		try {
			ArrayList<Feedback> f = retrieveFeedback(aID);
			for(Feedback element: f)
			{
				a.addFeedback(element);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		a.setAID(r.getString("AID"));
		
		return a;
	}
}	

