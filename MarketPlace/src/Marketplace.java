import java.sql.Connection;
import java.util.ArrayList;

public class Marketplace {

	public ArrayList<Transaction> transList;
	private Users currentUsers; //the only instance of new Users() in the entire program
	private Inventory currentInventory; //the only instance of new Inventory() in the entire program

	public Marketplace() {
		transList = new ArrayList<Transaction>();
		currentUsers = new Users();
		currentInventory = new Inventory(this.getCurrentUsers());
		
		test();	
	}

	public void createUser(String userName, String userPassword, String userType, String userEmail, double userBalance) {
		//completed Jacob Good
		if (userType.equalsIgnoreCase("Buyer")) {
			new Buyer(userName, userPassword, userEmail, userBalance);
		} else if (userType.equalsIgnoreCase("Seller")) {
			new Seller(userName, userPassword, userEmail, userBalance);
		} else {
			new Administrator(userName, userPassword, userEmail);
		}
	}
	
	public void deleteUser(String userID) {
		//completed Jacob Good
		boolean found = false;
		for(AbstractUser element : currentUsers.usersList) {
			if(element.getUserID().equalsIgnoreCase(userID)) {
				System.out.println("Delete " + element);
				currentUsers.usersList.remove(element);
				currentUsers.push(element);
				System.out.println("Deleted " + element);
				found = true;
				break;
			}
		}
		
		if(!found) System.out.println("Could not delete. User ID: " + userID + " not found");
	}

	public void createItem(Seller sellerID, String itemName, String itemCategory, double itemPrice, int itemQuantity, String itemDescription) {
		//completed Jacob Good
		new Item(sellerID, itemName, itemCategory, itemPrice, itemQuantity, itemDescription);
	}
	
	public void deleteItem(String itemID) {
		//TODO: delete an item and remove from database
		boolean found = false;
		for(Item element : currentInventory.inventory) {
			if(element.getItemID().equalsIgnoreCase(itemID)) {
				System.out.println("Delete " + element);
				currentInventory.inventory.remove(element);
				currentInventory.push(element);
				System.out.println("Deleted " + element);
				found = true;
				break;
			}
		}
		
		if(!found) System.out.println("Could not delete. Item ID: " + itemID + " not found");
	}
	
	public Users getCurrentUsers() {
		return currentUsers;
	}
	
	public Inventory getCurrentInventory() {
		// TODO Auto-generated method stub
		return currentInventory;
	}
	

	public void beginTransaction(){
		//called by itemCard
		

	}

	public void endTransaction(){

	}
	
	public void test() {
		
		this.getCurrentInventory().displayItems();
		
		this.getCurrentInventory().getItemWithItemID("4a4abe").setItemQuantity(5);
		
		this.getCurrentInventory().displayItems();
		
	}

	public static void main(String[] args) {
		
		Connection con = null;
		try {
			con=Database.mycon();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Marketplace testMarketplace = new Marketplace();

		//Already in the database
		//Buyer jacob = new Buyer("anotherBuyer", "738291", "buyme@gmail.edu" ,6666.66);
		//Seller lukas = new Seller("lhamman", "54321", "lhamman@iu.edu", 10000.00);
		//Administrator justin = new Administrator("jdrinkall", "15243", "jdrinkall@iu.edu");
		//Administrator nick = new Administrator("ndquigle", "56789", "ndquigle@iu.edu");
	}


}
