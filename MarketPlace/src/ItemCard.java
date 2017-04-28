import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemCard extends JPanel{
	private Item currentItem;
	private AbstractUser currentUser;
	private Marketplace currentMarketplace;
	
	public ItemCard(Item i, AbstractUser user, Marketplace mk){
		setBackground(new Color(220, 220, 220));
		this.currentItem = i;
		this.currentUser = user;
		this.currentMarketplace = mk;
		
		this.setMinimumSize(new Dimension(700, 90));
		this.setMaximumSize(new Dimension(700, 90));
		this.setPreferredSize(new Dimension(700, 90));
		
		this.setLayout(null);
		
		JLabel lblItemName = new JLabel(currentItem.getItemName());
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblItemName.setBounds(12, 13, 302, 19);
		add(lblItemName);
		
		Double price = currentItem.getItemPrice();
		JLabel lblPrice = new JLabel("$" + price.toString());
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice.setBounds(18, 33, 91, 16);
		add(lblPrice);
		
		Integer quantity = currentItem.getItemQuantity();
		JLabel lblAvailable = new JLabel(quantity.toString() + " available");
		lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAvailable.setBounds(18, 65, 114, 16);
		add(lblAvailable);
		
		JLabel lblDescription = new JLabel(currentItem.getItemDescription());
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		lblDescription.setBounds(246, 13, 454, 74);
		add(lblDescription);

		// only allows users to purchase if they are a buyer
		if(currentUser.getUserType().equalsIgnoreCase("Buyer")){
			
			JButton btnPurchase = new JButton("Purchase");
			btnPurchase.addActionListener(new ActionListener() {

				// if pressed, we go into the order confirmation
				public void actionPerformed(ActionEvent e) {

					// will fill out the drop down list
					// unnecessary but looks cool
					int quantity = currentItem.getItemQuantity();

					String[] quantityList = new String[quantity];
					for(int i = 1; i <= quantity; i++){
						quantityList[i-1] = "" + i;
					}

					// this pops out a dialog box asking user to select value they want
					// order is actually the number they select. String because that's the way dialog box works
					String order = (String) JOptionPane.showInputDialog(btnPurchase, "Select quantity desired", "Order Confirmation", JOptionPane.QUESTION_MESSAGE, (Icon) null, quantityList, quantityList[0]);

					
					// able to cast to buyer because this will only ever appear for buyers
					// starts the system of transactions
					currentMarketplace.beginTransaction((Buyer) currentUser, currentItem.getSellerID(), currentItem, quantity);

				}
			});
			btnPurchase.setBounds(595, 60, 100, 25);
			add(btnPurchase);
		}
	}
	
}
