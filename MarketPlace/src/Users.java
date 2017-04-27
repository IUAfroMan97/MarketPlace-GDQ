import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Users {

	public ArrayList<AbstractUser> usersList;

	public Users() {
		usersList = new ArrayList<AbstractUser>();

		pull(); //refresh the current users in the lists
	}
	
	public boolean isUserIDInList(String userID, ArrayList<AbstractUser> listTarget) {
		for (AbstractUser element : listTarget) {
			if (userID.equalsIgnoreCase(element.getUserID())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isUserNameInList(String userName, ArrayList<AbstractUser> listTarget) {
		for (AbstractUser element : listTarget) {
			if (userName.equalsIgnoreCase(element.getUserName())) {
				return true;
			}
		}
		return false;
	}
	
	public void displayUsers() {
		pull(); //refresh the current users in the lists
		for (AbstractUser element : usersList) { //display all of the users in the targetList
			System.out.println(element.toString());
		}
	}
	
	public AbstractUser getUser(String userName) {
		for(AbstractUser element : usersList) {
			if(element.getUserName().equalsIgnoreCase(userName)) {
				return element;
			}
		}
		return null;
	}

	public void pull(){
		// pulls from database
		// in turn updates the whole program (?)

		Connection con = null;
		try{
			con=Database.mycon();

			String query= "select * from Users";
			PreparedStatement st=con.prepareStatement(query);

			String uniqueID="";
			String username="";
			String type="";
			String password="";
			String email="";
			Double balance=0.0;

			st=con.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				uniqueID = rs.getString(1);
				username = rs.getString(2);
				password = rs.getString(3);
				type = rs.getString(4);
				email = rs.getString(5);
				balance = rs.getDouble(6);

				if (!isUserIDInList(uniqueID, usersList)) {	
					if (type.equalsIgnoreCase("Buyer")) {
						usersList.add(new Buyer(uniqueID, username, password, email, balance));
					} else if (type.equalsIgnoreCase("Seller")) {
						usersList.add(new Seller(uniqueID, username, password, email, balance));
					} else {
						usersList.add(new Administrator(uniqueID, username, password, email, balance));
					}
				}
			}
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void push(AbstractUser target){
		// pushes info to database
		// TODO find how to remove specific row in database!
		
		Connection con = null;
		try {
			con=Database.mycon();
			
			String query = "DELETE FROM Users WHERE userID=?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, target.getUserID());
			st.executeUpdate();
			con.close();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
