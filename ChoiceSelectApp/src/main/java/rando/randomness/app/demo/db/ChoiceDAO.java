package rando.randomness.app.demo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//import edu.wpi.cs.heineman.demo.model.Constant;
import rando.randomness.app.demo.db.DatabaseUtil;
import rando.randomness.app.demo.model.Alternative;
import rando.randomness.app.demo.model.Choice;
import rando.randomness.app.demo.model.Feedback;
import rando.randomness.app.demo.model.Member;
import rando.randomness.app.demo.model.Team;
public class ChoiceDAO {
	java.sql.Connection conn;
	
	final String cName = "Choice";
	final String aName = "Alternative";
	final String apName = "APPDIS";
	final String fName = "Feedback";
	final String mName = "Members";
	final String tName = "Teams";// Exact capitalization
	
	public ChoiceDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
	//Adds team to the database and returns the ID
			public Member addMember(Member m, String tID) throws Exception
			{
				try {
		        	
		            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + mName + " (MID, name, password, admin, TID) values(?,?,?,?,?);");
		            String mID = UUID.randomUUID().toString();
		            m.setMID(mID);
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
		            return m;

		        } catch (Exception e) {
		            throw new Exception("Failed to insert team: " + e.getMessage());
		        }
			}
			
	//Adds team to the database and returns the ID
		public Team addTeam(Team t) throws Exception
		{
			try {
	        	
	            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tName + " (TID) values(?);");
	            String tID = UUID.randomUUID().toString();
	            t.setTID(tID);
	            ps.setString(1,  tID);
	            ps.execute();
	           
	            for(Member m: t.getMembers())
	            {
	            	m = addMember(m, tID);
	            }
	            Choice c = t.getChoice();
	            c = addChoice(c, tID);
	            return t;

	        } catch (Exception e) {
	            throw new Exception("Failed to insert team: " + e.getMessage());
	        }
		}
	
	//Adds Choice to the database and returns the ID
	public Choice addChoice(Choice c, String tID) throws Exception
	{
		try {
        	
			
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + cName + " (CID,description,dateOfCreation,dateOfCompletion,finalChoice,TID) values(?,?,?,?,?,?);");
            //String cID = UUID.randomUUID().toString();
            String cID = tID;
            c.setID(cID);
            ps.setString(1,  cID);
            ps.setString(2,  c.getDescription());
            ps.setString(3,  c.getCreationDate().toString());
            if(c.getCompletionDate() == null)
            {
            	 ps.setString(4, "NULL");
            }
            else
            {
            	 ps.setString(4, c.getCompletionDate().toString());
            }
            if(c.getFinalChoice() < 0)
            {
            	 ps.setInt(5, -1);
            }
            else
            {
            	ps.setInt(5, c.getFinalChoice());
            }
            ps.setString(6, tID);
            ps.execute();
            for(Alternative a: c.getAlternativeList())
            {
            	a = addAlternative(a, cID);
            }
            return c;

        } catch (Exception e) {
            throw new Exception("Failed to insert choice: " + e.getMessage());
        }
	}
	
	//Adds given alternative to the database (Does not add any feedback attached to it yet)
	public Alternative addAlternative(Alternative a, String cID) throws Exception
	{
		try {
        	
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + aName + " (AID,CID,description) values(?,?,?);");
            String aID = UUID.randomUUID().toString();
            a.setAID(aID);
            ps.setString(1,  aID);
            ps.setString(2,  cID);
            ps.setString(3,  a.getDescription());
            
            for(String ap: a.getApprovers())
            {
            	addApprover(aID, ap);
            }
            for(String di: a.getDispprovers())
            {
            	addDisapprover(aID, di);
            }
            for(Feedback f: a.getFeebackList())
            {
            	f = addFeedback(f, aID, f.getCreator());
            }
            ps.execute();
            return a;

        } catch (Exception e) {
            throw new Exception("Failed to insert alternative: " + e.getMessage());
        }
	}
	
	//Adds given feedback to the database
	//Requires the id of the alternative it belongs to
	public Feedback addFeedback(Feedback f, String aID, String creator) throws Exception
	{
		try {
           
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + fName + " (FID,AID,timestamp,description,name) values(?,?,?,?,?);");
            String fID = UUID.randomUUID().toString();
            f.setFID(fID);
            ps.setString(1,  fID);
            ps.setString(2,  aID);
            ps.setString(3,  f.getTimestamp().toString());
            ps.setString(4, f.getDescription());
            ps.setString(5, creator);
            ps.execute();
            return f;

        } catch (Exception e) {
            throw new Exception("Failed to insert feedback: " + e.getMessage());
        }
	}
	
	public void handleAppDisRequest(boolean approval, String aID, String creator) throws Exception
	{
		//How to check and update the database based on request to approve/disapprove
		ArrayList<String> approvers = retrieveApprovers(aID);
		ArrayList<String> disapprovers = retrieveDisapprovers(aID);
		//If the request is for approval
		if(approval == true) {
			//If they've already approved
			if(approvers.contains(creator))
			{
				//Remove them from approval list
				deleteAppDis(aID, creator);
			}
			//If they've already disapproved
			else if(disapprovers.contains(creator))
			{
				//Remove disapproval and add approval
				deleteAppDis(aID, creator);
				addApprover(aID, creator);
			}
			//If they haven't approved/disapproved
			else
			{
				//Add approval
				addApprover(aID, creator);
			}
		}
		//If the request is for disapproval
		else
		{
			//If they've already disapproved
			if(disapprovers.contains(creator))
			{
				//Remove them from disapproval list
				deleteAppDis(aID, creator);
			}
			//If they've already approved
			else if(approvers.contains(creator))
			{
				//Remove approval and add disapproval
				deleteAppDis(aID, creator);
				addDisapprover(aID, creator);
			}
			//If they haven't approved/disapproved
			else
			{
				//Add disapproval
				addDisapprover(aID, creator);
			}
		}
	}
	

	
	
	private void addApprover(String aID, String creator)
	{
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + apName + " (APID,AID,approved,memberName) values(?,?,?,?);");
			 String apID = UUID.randomUUID().toString();
			 int approved = 1;
			 ps.setString(1, apID);
			 ps.setString(2, aID);
			 ps.setInt(3, approved);
			 ps.setNString(4, creator);
			 ps.execute();
			 return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addDisapprover(String aID, String creator)
	{
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + apName + " (APID,AID,approved,memberName) values(?,?,?,?);");
			 String apID = UUID.randomUUID().toString();
			 int approved = 0;
			 ps.setString(1, apID);
			 ps.setString(2, aID);
			 ps.setInt(3, approved);
			 ps.setNString(4, creator);
			 ps.execute();
			 return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + cName + " WHERE TID = ?;");
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
	
	public ArrayList<String> retrieveApprovers(String aID) throws Exception
	{
		ArrayList<String> allApprovers = new ArrayList<>();
		try {
        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + apName + " WHERE AID = ? AND approved = ?;");
            ps.setString(1, aID);
            ps.setInt(2, 1);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
            	String member = resultSet.getString("memberName");
            	allApprovers.add(member);
            }
            resultSet.close();
            return allApprovers;

        } catch (Exception e) {
            throw new Exception("Failed in getting approvers: " + e.getMessage());
        }
		
	}
	public ArrayList<String> retrieveDisapprovers(String aID) throws Exception
	{
		ArrayList<String> allDisapprovers = new ArrayList<>();
		try {
        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + apName + " WHERE AID = ? AND approved = ?;");
            ps.setString(1, aID);
            ps.setInt(2, 0);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
            	String member = resultSet.getString("memberName");
            	allDisapprovers.add(member);
            }
            resultSet.close();
            return allDisapprovers;

        } catch (Exception e) {
            throw new Exception("Failed in getting disapprovers: " + e.getMessage());
        }
		
	}
	
	public boolean deleteTeam(String tID) throws Exception
	{
		 try {
	        	PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM " + tName + " WHERE TID = ?;");
	        	ps1.setString(1, tID);
	        	ResultSet resultSet = ps1.executeQuery();
	        	
	        	//Delete choice
	        	while (resultSet.next())
	        	{
	        		Team t = generateTeam(resultSet);
	        		deleteChoice(t.getTID());
	        		deleteMembers(t.getTID());
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
	public boolean deleteMembers(String tID) throws Exception
	{
		try {
       	 
            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + mName + " WHERE TID = ?;");
            ps.setString(1, tID);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected >= 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete members: " + e.getMessage());
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
        		deleteAllAppDis(a.getAID());
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
	 //Delete approval/disapproval for the given alternative and member
	 private boolean deleteAppDis(String aID, String memberName) throws Exception
	 {
		 try {
	            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + apName + " WHERE AID = ? AND memberName = ?;");
	            ps.setString(1, aID);
	            ps.setString(2, memberName);
	            int numAffected = ps.executeUpdate();
	            ps.close();
	            
	            return (numAffected >= 1);

	        } catch (Exception e) {
	            throw new Exception("Failed to delete approval/disapproval: " + e.getMessage());
	        }
	 }
	//Deletes ALL approval/disapproval for the alternative with the given ID
	 public boolean deleteAllAppDis(String aID) throws Exception {
	        try {
	        	 
	            PreparedStatement ps = conn.prepareStatement("DELETE FROM " + apName + " WHERE AID = ?;");
	            ps.setString(1, aID);
	            int numAffected = ps.executeUpdate();
	            ps.close();
	            
	            return (numAffected >= 1);

	        } catch (Exception e) {
	            throw new Exception("Failed to delete approval/disapproval: " + e.getMessage());
	        }
	    }
	
	
	private Choice generateChoice(ResultSet r) throws SQLException
	{
		
		String tID = r.getString("TID");
		String cID = r.getString("CID");
		String description = r.getString("description");
		Timestamp dateOfCreation = Timestamp.valueOf(r.getString("dateOfCreation"));
		String dOC= r.getString("dateOfCompletion");
	
		
		Integer finalChoice = r.getInt("finalChoice");
		
		
		Choice c = new Choice(description, dateOfCreation);
		c.setID(cID);
		c.setTID(tID);
		c.setFinalChoice(finalChoice);
		if(dOC.equals("NULL"))
		{
			c.setCompletionDate(null);
		}
		else
		{
			Timestamp dateOfCompletion = Timestamp.valueOf(r.getString("dateOfCompletion"));
			c.setCompletionDate(dateOfCompletion);
		}
		
		
		 try {
			for(Alternative a: retrieveAlternatives(cID))
			{
				c.addAlternative(a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	private Team generateTeam(ResultSet r) throws Exception
	{
		
		String tid = r.getString("TID");
		ArrayList<Member> m = retrieveMembers(tid);
		Choice choice = retrieveChoice(tid);
		Team t = new Team((ArrayList<Member>) m, choice);
		 
		t.setTID(tid);
		return t;
	}
	
	private Feedback generateFeedback(ResultSet r) throws SQLException
	{
		Timestamp t = Timestamp.valueOf(r.getString("timestamp"));
		String desc = r.getString("description");
		String creator = r.getString("name");
		Feedback f = new Feedback(t, desc, creator);
		f.setFID(r.getString("FID"));
		return f;
	}
	
	private Member generateMember(ResultSet r) throws SQLException
	{
		String pass = r.getString("password");
		String name = r.getString("name");
		
		Member m = new Member(name, pass);
		m.setMID(r.getString("MID"));
		
		return m;
	}
	
	
	private Alternative generateAlternative(ResultSet r) throws Exception
	{
		String desc = r.getString("description");
		String aID = r.getString("AID");
		Alternative a = new Alternative(desc);
		//Add approvers,disapprovers, and feedback
		try {
			ArrayList<String> approvers = retrieveApprovers(aID);
			ArrayList<String> disapprovers = retrieveDisapprovers(aID);
			ArrayList<Feedback> f = retrieveFeedback(aID);
			for(String ap: approvers)
			{
				a.addApprover(ap);
			}
			for(String di: disapprovers)
			{
				a.addDisapprover(di);
			}
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

