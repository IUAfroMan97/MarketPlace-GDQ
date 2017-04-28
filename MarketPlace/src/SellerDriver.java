import java.awt.Color;

import javax.swing.*;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SellerDriver extends JPanel{
	
	SellerDriver currentSession;
	private Seller currentUser;
	private Marketplace currentMarketplace;


	
	public SellerDriver(Seller inputUser, Marketplace mk){
		currentSession = this;
		this.currentUser = inputUser;
		this.currentMarketplace = mk;
		
		
		this.setSize(900, 600);
		this.setVisible(true);
		this.setBackground(SystemColor.activeCaption);
		this.setLayout(null);
		
		
		// ---------- building the frame around the main pane ----------
		
		// where we will keep all the data etc
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(8, 30, 880, 530);
		this.add(tabbedPane);
		
		// displays current username
		JLabel lblSellID = new JLabel("Current User: " + inputUser.getUserID());
		lblSellID.setBounds(12, 8, 200, 20);
		this.add(lblSellID);
		
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// change the pane from this to login panel
				
				System.out.println("pressed");
				Frame currentFrame = (Frame) SwingUtilities.getWindowAncestor(currentSession);
				currentFrame.changePanel(currentFrame.loginPanel);
			}
		});
		btnLogOut.setBounds(791, 6, 97, 40);
		this.add(btnLogOut);
		
		
		// overview tab!
		JPanel overview = new JPanel();
		overview.setLayout(null);
		overview.setSize(860, 530);
		overview.setBackground(Color.LIGHT_GRAY);
		tabbedPane.add("Overview", overview);
		
		// user id label
		JLabel lblUserID = new JLabel("UserID: " + inputUser.getUserID());
		lblUserID.setBounds(12, 13, 200, 34);
		overview.add(lblUserID);
		
		// user name stuff
		JLabel lblUsername = new JLabel("Username: " + inputUser.getUserName());
		lblUsername.setBounds(12, 60, 200, 34);
		overview.add(lblUsername);
		
		JButton btnChangeUsername = new JButton("Change Username");
		btnChangeUsername.setBounds(224, 65, 149, 25);
		overview.add(btnChangeUsername);
		
		
		// user's balance
		JLabel lblBalance = new JLabel();
		lblBalance.setText("Current Account Balance:" + inputUser.getUserBalance());
		lblBalance.setBounds(663, 453, 200, 34);
		overview.add(lblBalance);
		
		
		// email malarkey
		JLabel lblEmail = new JLabel("E-mail: " + inputUser.getUserEmail());
		lblEmail.setBounds(12, 107, 200, 16);
		overview.add(lblEmail);
	
		JButton btnChangeEmail = new JButton("Change E-mail");
		btnChangeEmail.setBounds(224, 103, 149, 25);
		overview.add(btnChangeEmail);
		
		// password labels etc		
		JButton btnPassword = new JButton("Change Password");
		btnPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPassword.setBounds(224, 141, 149, 25);
		overview.add(btnPassword);
		
		// creating the password label (using * so it's """secure""")
		String pwLabel = "Password: ";
		// will print out as many asterisks as there are letters for the string
		for(int i = 0; i < inputUser.getUserPassword().length(); i++){
			pwLabel += "*";
		}
		JLabel lblPassword = new JLabel(pwLabel);
		lblPassword.setBounds(12, 145, 200, 16);
		overview.add(lblPassword);
		
		
		
		
		// TODO: Maybe add current listings to this tab?
		// need to fill space somehow		
		// hook up those buttons
		
		// View Seller History Pane
		// View Current Listing Tab
		
		// ------------- post new item tab -----------------
		JPanel newItemTab = new JPanel();
		newItemTab.setLayout(null);
		newItemTab.setSize(860, 530);
		newItemTab.setBackground(Color.LIGHT_GRAY);
		tabbedPane.add("Post new item", newItemTab);
		
		JLabel lblItemName = new JLabel("Item name: ");
		lblItemName.setBounds(12, 13, 89, 16);
		newItemTab.add(lblItemName);
		
		JTextField itemNameTxt = new JTextField();
		itemNameTxt.setBounds(107, 10, 200, 22);
		newItemTab.add(itemNameTxt);
		
		
		JLabel lblPrice = new JLabel("Price (USD): ");
		lblPrice.setBounds(12, 55, 89, 16);
		newItemTab.add(lblPrice);
		
		JTextField PriceTxt = new JTextField();
		PriceTxt.setBounds(107, 52, 99, 22);
		newItemTab.add(PriceTxt);
		PriceTxt.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity: ");
		lblQuantity.setBounds(12, 100, 56, 16);
		newItemTab.add(lblQuantity);
		
		JTextField quantityTxt = new JTextField();
		quantityTxt.setBounds(107, 97, 99, 22);
		newItemTab.add(quantityTxt);
		quantityTxt.setColumns(10);
		
				
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select a category:", "Home & Garden", "Electronics", "Books"}));
		comboBox.setBounds(12, 129, 131, 22);
		newItemTab.add(comboBox);
		
		JLabel lblItemDescription = new JLabel("Item Description: ");
		lblItemDescription.setBounds(12, 176, 122, 16);
		newItemTab.add(lblItemDescription);
		
		JTextField descriptionTxt = new JTextField("Limit 140 Characters");
		descriptionTxt.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionTxt.setBounds(12, 205, 303, 138);
		newItemTab.add(descriptionTxt);
		descriptionTxt.setColumns(10);
		
		JButton btnCreateListing = new JButton("Create listing");
		btnCreateListing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Seller sellerID = inputUser;
				
				String itemName = itemNameTxt.getText();
				String itemCategory = comboBox.getSelectedItem().toString();
				double itemPrice = Double.parseDouble(PriceTxt.getText());
				int itemQuantity = Integer.parseInt(quantityTxt.getText());
				String itemDescription = descriptionTxt.getText();
				if(itemCategory == "Select a category:"){
					System.out.println("wrong!");
				}
				else if(itemName.isEmpty() || itemDescription.isEmpty() || itemPrice <= 0 || itemQuantity <= 0){
					System.out.println("wrong!");
				}
				else{
					currentMarketplace.createItem(sellerID, itemName, itemCategory, itemPrice, itemQuantity, itemDescription);
				}
				
				
			}
		});
		btnCreateListing.setBounds(12, 449, 122, 25);
		newItemTab.add(btnCreateListing);
		
//		// inventory tester in case I need to look at the whole inventory as a seller
//		InventoryPanel ip = new InventoryPanel(currentMarketplace, currentUser);
//		tabbedPane.add("Inventory", ip);
			
		
		
	}
}