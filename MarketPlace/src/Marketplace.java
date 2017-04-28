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
		Item i = new Item(sellerID, itemName, itemCategory, itemPrice, itemQuantity, itemDescription);
		
		// adding the given item to the seller's postHistory
		for(AbstractUser element : currentUsers.usersList) {
			if(element.getUserID().equalsIgnoreCase(sellerID.getUserID())) {
				Seller tempSeller = (Seller) currentUsers.getUserWithUserID(element.getUserID()); 
				
				
				
				System.out.println(tempSeller.getPostHistory() == null);
				
				ArrayList<Item> tempHistory = tempSeller.getPostHistory();
				
				tempHistory.add(i);
			}
		}
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
	

	public void beginTransaction(Buyer buyer, String sellerID, Item givenItem, int quantity){
		//called by itemCard
		
		//public Transaction(String buyerID, String sellerID, String itemID, int quantity){
		String buyerID = buyer.getUserID();
		
		Transaction newTrans = new Transaction(givenItem, buyerID, sellerID, quantity);
		System.out.println(newTrans.toString());
		
	}

	public void endTransaction(Transaction trans){
		// using the given transaction, gets all the necessary information
		
		// altering user balances
		String sellerID = trans.sellerID;
		String buyerID = trans.buyerID;
		
		Seller thisSeller = (Seller) currentUsers.getUserWithUserID(sellerID);
		Buyer thisBuyer = (Buyer) currentUsers.getUserWithUserID(buyerID);
		
		Item thisItem = trans.currentItem;
		
		int totalCost = ((int) thisItem.getItemPrice()) * trans.quantity;
		
		// takes money from the buyer, gives to seller
		thisBuyer.alterBalance(totalCost);
		thisSeller.alterBalance(totalCost);
		
		
		// now changing item quantity
		thisItem.setItemQuantity(thisItem.getItemQuantity() - trans.quantity);
		
		// adding to the seller's sold history and buyers history
		
	//	thisSeller.soldHistory.add(thisItem);
		thisBuyer.purchased.add(thisItem);
		
		// PUSHING THESE CHANGES TO THE DATABASE
		
//		currentUsers.push(thisSeller);
//		currentUsers.push(thisBuyer);
//		currentInventory.push(thisItem);
	}
	
	public void test() {
		
		
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
	
		
	}


}
