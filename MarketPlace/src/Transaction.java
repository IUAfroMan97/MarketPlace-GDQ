import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

import javax.swing.Timer;

public class Transaction implements ActionListener{
	
	public Item currentItem; // holds a copy of the item here in transaction

	public String historyID;
	public String transID;
	public String buyerID;
	public String sellerID;
	public String itemID;
	public int quantity;
	public boolean shipped;
	private Marketplace currentMarketplace;
	
	private Timer shippingTimer;
	
	public Transaction(Item givenItem, String buyerID, String sellerID, int quantity, Marketplace mk){
		
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
		
		shipped = false;
		
		this.push();
		
		shippingTimer = new Timer(5000, this);
		shippingTimer.start();
		
	}
	
	public Transaction(String transID, String itemID, String buyerID, String sellerID, int quantity, String shipped){
		this.transID = transID;
		this.itemID = itemID;
		this.buyerID = buyerID;
		this.sellerID = sellerID;
		this.quantity = quantity;
		this.shipped = shipped.equalsIgnoreCase("true");
	}
	
	private void push() {
		// TODO Auto-generated method stub
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
	
	public void actionPerformed(ActionEvent evt) {
		
		shipped = true;
		
		this.update();
		
		System.out.println("Shipped: " + shipped);
		shippingTimer.stop();
		
		currentMarketplace.endTransaction(this);
	}

	
	

}
