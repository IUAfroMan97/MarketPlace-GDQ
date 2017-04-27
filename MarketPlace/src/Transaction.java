import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Transaction implements ActionListener{

	public String transID;
	public String buyerID;
	public String sellerID;
	public String itemID;
	public int quantity;
	public boolean shipped;
	
	private Timer shippingTimer;
	
	public Transaction(String buyerID, String sellerID, String itemID, int quantity){
		this.buyerID = buyerID;
		this.sellerID = sellerID;
		this.itemID = itemID;
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
	
	public void actionPerformed(ActionEvent evt) {
		
		shipped = true;
		System.out.println("Shipped: " + shipped);
	}
	

}
