import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class Item {

	private String itemName;
	private final String itemID;
	private String itemCategory;
	private String itemDescription;
	private double itemPrice;
	private int itemQuantity;
	private String sellerID;
	
	public Item(Seller sellerID, String itemName,String itemCategory, double itemPrice, int itemQuantity, String itemDescription){
		
		this.itemID = generateItemID();
		this.sellerID = sellerID.getUserID();
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
	
		push();
	}
	
	public Item(String itemID, Seller sellerID, String itemName, String itemCategory, double itemPrice, int itemQuantity, String itemDescription) {
		this.itemID = itemID;
		this.sellerID = sellerID.getUserID();
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.itemDescription = itemDescription;
	}
	
	public String toString() {
		return "Item: " + this.itemID + ", " + sellerID + ", " + this.itemName + ", " + this.itemCategory + ", " + this.itemPrice + ", " + this.itemQuantity + ", " + itemDescription;
	}

	private String generateItemID() {return UUID.randomUUID().toString().substring(0, 6);}
	
	public void push(){
		// TODO give data to the database
		Connection con = null;
		try{
			con=Database.mycon();
			
			String query="insert into Items values(?,?,?,?,?,?,?)";
			PreparedStatement st=con.prepareStatement(query);
			
			st.setString(1, this.itemID);
			st.setString(2, this.sellerID);
			st.setString(3, this.itemName);
			st.setString(4, this.itemCategory);
			st.setDouble(5, this.itemPrice);
			st.setInt(6, this.itemQuantity);
			st.setString(7, this.itemDescription);
			st.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getSellerID() {
		return sellerID;
	}

	public void setSellerID(String sellerID) {
		this.sellerID = sellerID;
	}

	public String getItemID() {
		return itemID;
	}
}
