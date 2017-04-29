import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

import javax.swing.Timer;

public class Transaction implements ActionListener{
	
	public Item currentItem; // holds a copy of the item here in transaction

	// every value needed to create a transaction
	
	public String historyID; // this is to denote each transaction individually
	public String transID;
	public String buyerID;
	public String sellerID;
	public String itemID;
	public int quantity;
	public boolean shipped;
	private Marketplace currentMarketplace;
	
	private Timer shippingTimer;
	
	public Transaction(Item givenItem, String buyerID, String sellerID, int quantity, Marketplace mk){
		
		// copies everything from a constructor
		this.currentItem = givenItem;
		
		this.buyerID = buyerID;
		this.sellerID = sellerID;
		this.itemID = currentItem.getItemID();
		this.quantity = quantity;
		this.currentMarketplace = mk;
		this.historyID = this.getHistoryID();
		
		// transID = IIDBIDSIDQ
		transID = itemID.substring(0, 3);
		transID += buyerID.substring(0, 3);
		transID += sellerID.substring(0, 3);
		transID += quantity;
		
		// starts as not shipped
		shipped = false;
		
		// pushes this transaction to the history database
		this.push();
		
		// 5 second shipping!
		// after 5 seconds, the transaction is finalized and the buyer gets his item
		shippingTimer = new Timer(5000, this);
		shippingTimer.start();
		
	}
	
	public Transaction(String transID, String itemID, String buyerID, String sellerID, int quantity, String shipped){
		
		// creates an instance of transaction to add to the master transaction list
		
		this.transID = transID;
		this.itemID = itemID;
		this.buyerID = buyerID;
		this.sellerID = sellerID;
		this.quantity = quantity;
		this.shipped = shipped.equalsIgnoreCase("true");
	}
	
	private void push() {
		
		String tempShipped = "" + this.shipped;
		
		Connection con = null;
		
		try{
			con=Database.mycon();
			
			String query="insert into History values(?,?,?,?,?,?,?)";
			PreparedStatement st=con.prepareStatement(query);
			
			st.setString(1, this.historyID);
			st.setString(2, this.itemID);
			st.setString(3, this.sellerID);
			st.setString(4, this.buyerID);
			st.setString(5, this.transID);
			st.setInt(6, this.quantity);
			st.setString(7, tempShipped);
			st.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}		

	}
	
	private String getHistoryID() {return UUID.randomUUID().toString().substring(0, 6);}

	private void update() {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con=Database.mycon();
			
			String query="UPDATE History SET itemShipped = ? WHERE historyID = ? ";
			PreparedStatement st=con.prepareStatement(query);
			
			st.setString(1, "true");
			st.setString(2, this.historyID);
			
			
			st.executeUpdate();
			con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// for testing purposes. unnecessary
	public String toString(){
		String result = "";
		
		result += "Trans ID: " + transID;
		result += ", Buyer: " + buyerID;
		result += ", Seller: " + sellerID;
		result += ", Item " + itemID;
		result += "*" + quantity;
		result += ", Shipped: " + shipped;
		
		return result;
	}
	
	// ends the transaction calls the final stuff
	public void actionPerformed(ActionEvent evt) {
		
		shipped = true;
		
		// updates the transaction
		this.update();
		
		
		shippingTimer.stop(); // only calls the timer once
		
		currentMarketplace.endTransaction(this); // ends the transaction
	}

	
	

}
