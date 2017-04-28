import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Transaction implements ActionListener{
	
	public Item currentItem; // holds a copy of the item here in transaction

	public String transID;
	public String buyerID;
	public String sellerID;
	public String itemID;
	public int quantity;
	public boolean shipped;
	
	private Timer shippingTimer;
	
	public Transaction(Item givenItem, String buyerID, String sellerID, int quantity){
		
		this.currentItem = givenItem;
		
		this.buyerID = buyerID;
		this.sellerID = sellerID;
		this.itemID = currentItem.getItemID();
		this.quantity = quantity;
		
		// transID = IIDBIDSIDQ
		transID = itemID.substring(0, 3);
		transID += buyerID.substring(0, 3);
		transID += sellerID.substring(0, 3);
		transID += quantity;
		
		shipped = false;
		shippingTimer = new Timer(5000, this);
		shippingTimer.start();
		
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
		System.out.println("Shipped: " + shipped);
		shippingTimer.stop();
	}
	

}
