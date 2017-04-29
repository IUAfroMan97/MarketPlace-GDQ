import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Inventory {
	
	//initial variables
	public ArrayList<Item> inventory;
	Users currentUsers;

	public Inventory(Users currentUsers) {
		inventory = new ArrayList<Item>();
		this.currentUsers = currentUsers;
		
		pull(); //populate inventory arraylist with data from database
	}
	
	public void displayItems() {
		//displayes the items in the consols... for testing purposes only
		for(Item element : inventory) {
			System.out.println(element.toString());
		}
	}
	
	public boolean isItemIdInList(String itemID) {
		//compares itemID to the elements in the arraylist
		for (Item element : inventory) {
			if (itemID.equalsIgnoreCase(element.getItemID())) {
				return true;
			}
		}
		return false;
	}

	public void push(Item target){
		// TODO
		// pushes information to database
		
		Connection con = null;
		try {
			con=Database.mycon(); //connects to the database via the address
			
			String query = "DELETE FROM Items WHERE itemID=?"; //sql query for database
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, target.getItemID());
			st.executeUpdate();
			con.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void pull(){
		// TODO
		// grabs and refreshes info from database

		Connection con = null;
		try{
			con=Database.mycon(); //connects to the database via address

			String query= "select * from Items"; //sql query for database
			PreparedStatement st=con.prepareStatement(query);

			String itemID = "";
			String sellerID = "";
			String itemName = "";
			String itemCategory = "";
			double itemPrice = 0.0;
			int itemQuantity = 0;
			String itemDescription = "";

			st=con.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				itemID = rs.getString(1);
				sellerID = rs.getString(2);
				itemName = rs.getString(3);
				itemCategory = rs.getString(4);
				itemPrice = rs.getDouble(5);
				itemQuantity = rs.getInt(6);
				itemDescription = rs.getString(7);

				
				// -----Add the correct seller to the correct Item -------
				Seller currentSeller = null;
				for(AbstractUser element : currentUsers.usersList) {
					if(element.getUserType().equalsIgnoreCase("Seller")) {
						if (element.getUserID().equalsIgnoreCase(sellerID)) {
							currentSeller = (Seller) element;
						} else {
							System.out.println("doesnt match sellerID");
						}
					} else {
						System.out.println("not a seller");
					}
				}

				if(!isItemIdInList(itemID)) {
					System.out.println("Adding item to inventory");
					Item foundItem = new Item(itemID, currentSeller, itemName, itemCategory, itemPrice, itemQuantity, itemDescription);
					inventory.add(foundItem);
				}
			}

			con.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
