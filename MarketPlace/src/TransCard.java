import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class TransCard extends JPanel{
	
	
	// given transaction and every field for each transaction
	private Transaction currentTransaction;
	
	public String transID;
	public String buyerID;
	public String sellerID;
	public String itemID;
	public int quantity;
	public boolean shipped;


	public TransCard(Transaction t){
		setBackground(new Color(220, 220, 220));

		this.setMinimumSize(new Dimension(800, 100));
		this.setMaximumSize(new Dimension(800, 100));
		this.setPreferredSize(new Dimension(800, 100));

		this.setLayout(null);
		
		this.currentTransaction = t;
		
		this.transID = currentTransaction.transID;
		this.buyerID = currentTransaction.buyerID;
		this.sellerID = currentTransaction.sellerID;
		this.itemID = currentTransaction.itemID;
		this.quantity = currentTransaction.quantity;
		this.shipped = currentTransaction.shipped;
		
		// JLabels hold the item information
		
		JLabel lblTransID = new JLabel("Transaction ID: " + transID);
		lblTransID.setBounds(12, 13, 400, 16);
		add(lblTransID);
		
		JLabel lblSellerid = new JLabel("SellerID: " + sellerID);
		lblSellerid.setBounds(22, 37, 156, 16);
		add(lblSellerid);
		
		JLabel lblBuyerid = new JLabel("BuyerID: " + buyerID);
		lblBuyerid.setBounds(22, 53, 156, 16);
		add(lblBuyerid);
		
		JLabel lblItemPurchased = new JLabel("Item Purchased: " + itemID);
		lblItemPurchased.setBounds(333, 37, 156, 16);
		add(lblItemPurchased);
		
		JLabel lblQuantity = new JLabel("Quantity: " + quantity);
		lblQuantity.setBounds(333, 53, 156, 16);
		add(lblQuantity);
		
		String shipping = "Shipping Status: ";
		
		// if it has shipped
		if(currentTransaction.shipped){
			shipping += "Shipped!";
		}
		else{
			shipping += "In transit";
		}
		JLabel lblShippingStatus = new JLabel("Shipping Status: " + shipped);
		lblShippingStatus.setBounds(333, 13, 156, 16);
		add(lblShippingStatus);
		
	}
}
