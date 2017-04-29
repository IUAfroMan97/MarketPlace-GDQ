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
		

		// For the change value buttons!
		// 1. Pops out a dialog box asking for user to input new value
		// 2. Saves the value to a temporary string
		// 3. Calls the outer functions in AbstractUser with temporary String to change value
		// 4. (Optional) User must log in and out in order to save changes. Can only save one change at a time
		
		// user id label
		JLabel lblUserID = new JLabel("UserID: " + inputUser.getUserID());
		lblUserID.setBounds(12, 13, 200, 34);
		overview.add(lblUserID);
		
		// user name stuff
		JLabel lblUsername = new JLabel("Username: " + inputUser.getUserName());
		lblUsername.setBounds(12, 60, 200, 34);
		overview.add(lblUsername);
		
		JButton btnChangeUsername = new JButton("Change Username");
		btnChangeUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String inputValue = JOptionPane.showInputDialog(btnChangeUsername, "Input new username"); 
				 System.out.println(inputValue);
				 if (inputValue != null && inputValue != "") {
					 currentUser.changeUserName(inputValue);
				 }
				 //revalidate();
			}
		});
		btnChangeUsername.setBounds(224, 65, 149, 25);
		overview.add(btnChangeUsername);
		
		
		// user's balance
		JLabel lblBalance = new JLabel();
		lblBalance.setText("Current Account Balance:" + inputUser.getUserBalance());
		lblBalance.setBounds(12, 174, 361, 34);
		overview.add(lblBalance);
		
		
		// email malarkey
		JLabel lblEmail = new JLabel("E-mail: " + inputUser.getUserEmail());
		lblEmail.setBounds(12, 107, 200, 16);
		overview.add(lblEmail);
	
		JButton btnChangeEmail = new JButton("Change E-mail");
		btnChangeEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String inputValue = JOptionPane.showInputDialog(btnChangeEmail, "Input new email"); 
				 System.out.println(inputValue);
				 if (inputValue != null && inputValue != "") {
					 currentUser.changeUserEmail(inputValue);
				 }
				 //revalidate();
			}
		});
		btnChangeEmail.setBounds(224, 103, 149, 25);
		overview.add(btnChangeEmail);
		
		// password labels etc		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String inputValue = JOptionPane.showInputDialog(btnChangePassword, "Input new password"); 
				 System.out.println(inputValue);
				 if (inputValue != null && inputValue != "") {
					 currentUser.changeUserPassword(inputValue);
				 }
				 //revalidate();
			}
			});
		btnChangePassword.setBounds(224, 141, 149, 25);
		overview.add(btnChangePassword);
		
		// creating the password label (using * so it's """secure""")
		String pwLabel = "Password: ";
		// will print out as many asterisks as there are letters for the string
		for(int i = 0; i < inputUser.getUserPassword().length(); i++){
			pwLabel += "*";
		}
		JLabel lblPassword = new JLabel(pwLabel);
		lblPassword.setBounds(12, 145, 200, 16);
		overview.add(lblPassword);
		
		
		
		
		// ------------- post new item tab -----------------
		JPanel newItemTab = new JPanel();
		newItemTab.setLayout(null);
		newItemTab.setSize(860, 530);
		newItemTab.setBackground(Color.LIGHT_GRAY);
		tabbedPane.add("Post new item", newItemTab);
		
		// fields for inputting item information
		
		JLabel lblDisclaimer = new JLabel("<html>Once you have posted your item, only an administrator can change the values</html>");	
		lblDisclaimer.setBounds(400, 13, 200, 100);
		newItemTab.add(lblDisclaimer);
		
		JLabel lblItemName = new JLabel("Item name: ");
		lblItemName.setBounds(12, 13, 89, 16);
		newItemTab.add(lblItemName);
		
		JTextField txtItemName = new JTextField();
		txtItemName.setBounds(107, 10, 200, 22);
		newItemTab.add(txtItemName);
		
		
		JLabel lblPrice = new JLabel("Price (USD): ");
		lblPrice.setBounds(12, 55, 89, 16);
		newItemTab.add(lblPrice);
		
		JTextField txtPrice = new JTextField();
		txtPrice.setBounds(107, 52, 99, 22);
		newItemTab.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity: ");
		lblQuantity.setBounds(12, 100, 56, 16);
		newItemTab.add(lblQuantity);
		
		JTextField txtQuantity = new JTextField();
		txtQuantity.setBounds(107, 97, 99, 22);
		newItemTab.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		// this box holds all three possible categories for the items
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select a category:", "Home & Garden", "Electronics", "Books"}));
		comboBox.setBounds(12, 129, 131, 22);
		newItemTab.add(comboBox);
		
		JLabel lblItemDescription = new JLabel("Item Description: ");
		lblItemDescription.setBounds(12, 176, 122, 16);
		newItemTab.add(lblItemDescription);
		
		JTextField txtDescription = new JTextField("Limit 140 Characters");
		txtDescription.setHorizontalAlignment(SwingConstants.LEFT);
		txtDescription.setBounds(12, 205, 303, 138);
		newItemTab.add(txtDescription);
		txtDescription.setColumns(10);
		
		JButton btnCreateListing = new JButton("Create listing");
		btnCreateListing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Seller seller = currentUser;
				
				// grabs the info from each field of input
				String itemName = txtItemName.getText();
				String itemCategory = comboBox.getSelectedItem().toString(); // gets the selected index of the comboBox
				double itemPrice = Double.parseDouble(txtPrice.getText());
				int itemQuantity = Integer.parseInt(txtQuantity.getText());
				String itemDescription = txtDescription.getText();
				
				
				
				if(itemCategory == "Select a category:"){
					JOptionPane.showMessageDialog(btnCreateListing, "Select a type!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(itemName == ""|| itemDescription == ""|| itemPrice <= 0 || itemQuantity <= 0){
					JOptionPane.showMessageDialog(btnCreateListing, "Fill out every field", "Error", JOptionPane.ERROR_MESSAGE);				}
				else{
					currentMarketplace.createItem(seller, itemName, itemCategory, itemPrice, itemQuantity, itemDescription);
					
					// prints a success message and clears all values
					JOptionPane.showMessageDialog(btnCreateListing, "Your item has been posted!", "Success", JOptionPane.INFORMATION_MESSAGE);
					
					txtItemName.setText("");
					txtPrice.setText("");
					txtQuantity.setText("");
					txtDescription.setText("");
					comboBox.setSelectedIndex(0);
					
				}
				
				
			}
		});
		btnCreateListing.setBounds(12, 449, 122, 25);
		newItemTab.add(btnCreateListing);

			
		
		
	}
}
