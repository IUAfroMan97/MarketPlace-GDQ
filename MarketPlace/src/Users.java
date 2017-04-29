import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Users {

	public ArrayList<AbstractUser> usersList;
	public ArrayList<Transaction> transList;
	private Marketplace currentMarketplace;

	public Users(Marketplace mk) {
		currentMarketplace = mk;
		usersList = new ArrayList<AbstractUser>();
		transList = new ArrayList<Transaction>();

		pull(); //refresh the current users in the lists
		pullTransList();
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
		pull(); //refresh the usersList
		for (AbstractUser element : usersList) { //display all of the users in the targetList
			System.out.println(element.toString());
		}
	}
	
	public AbstractUser getUserWithUserName(String userName) {
		for(AbstractUser element : usersList) {
			if(element.getUserName().equalsIgnoreCase(userName)) {
				return element;
			}
		}
		return null;
	}
	
	public AbstractUser getUserWithUserID(String userID) {
		for(AbstractUser element : usersList) {
			if(element.getUserID().equalsIgnoreCase(userID)) {
				return element;
			}
		}
		return null;
	}


	public void pullTransList() {
		// pulls from database
		// in turn updates the whole program (?)

		transList.clear(); //always start with an empty list

		Connection con = null;
		try{
			con=Database.mycon();

			String query= "select * from History";
			PreparedStatement st=con.prepareStatement(query);

			String HistoryID="";
			String itemID="";
			String sellerID="";
			String buyerID="";
			String transactionID="";
			int itemQuantity=0;
			String itemShipped="";


			st=con.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				HistoryID = rs.getString(1);
				itemID = rs.getString(2);
				sellerID = rs.getString(3);
				buyerID = rs.getString(4);
				transactionID = rs.getString(5);
				itemQuantity = rs.getInt(6);
				itemShipped = rs.getString(7);


				for(AbstractUser element : this.usersList) {
					Seller tempSeller = null;
					Buyer tempBuyer = null;
					Item tempItem = currentMarketplace.getCurrentInventory().getItemFromInventory(itemID);
					
					if(element.getUserType().equalsIgnoreCase("Seller")) {
						 tempSeller = (Seller) element;
					} else if (element.getUserType().equalsIgnoreCase("Buyer")) {
						tempBuyer = (Buyer) element;
					}
					
					if(tempSeller.getUserID() == sellerID) {
						if(buyerID == null) {
							tempSeller.getPostHistory().add(tempItem);
						} else {
							tempSeller.getSoldHistory().add(tempItem);
						}
					}
					
					if (tempBuyer.getUserID() == buyerID) {
						tempBuyer.getPurchasedHistory().add(itemID);
					}
				}	
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pull() {
		// pulls from database
		// in turn updates the whole program (?)
		
		usersList.clear(); //always start with an empty list

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
