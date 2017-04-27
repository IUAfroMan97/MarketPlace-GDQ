import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;

public class LoginPanel extends JPanel{

	
	CardLayout cl;
	JPanel card; // this can be used to change the state of the JPanel inside event handlers
	Marketplace currentMarketplace; 
	Users currentUsers; // will be used for login and user creation
	
	public LoginPanel(Marketplace mk){
		super(new CardLayout());
		
		card = this;
		
		this.setSize(900, 600);
		this.setVisible(true);
		
		cl = (CardLayout)(this.getLayout());
		this.currentMarketplace = mk;
		currentUsers = currentMarketplace.getCurrentUsers();
		
		// ---------- log in panel ----------
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		this.add(loginPanel, "welcome");
		loginPanel.setBackground(Color.CYAN);
		
		JLabel lblWelcome = new JLabel("Welcome to GDQ Marketplace!");
		loginPanel.add(lblWelcome);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblWelcome.setBounds(250, 50, 500, 100);
		
		
		JButton btnAdmin = new JButton("Log in as Administrator");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cl.show(card, "adminlogin");
			}
		});
		btnAdmin.setBounds(100, 400, 200, 40);
		loginPanel.add(btnAdmin);
		
		JButton btnBuyer = new JButton("Log in as a Customer");
		btnBuyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cl.show(card, "buyerlogin");
			}
		});
		btnBuyer.setBounds(350, 400, 200, 40);
		loginPanel.add(btnBuyer);
		
		JButton btnSeller = new JButton("Log in as a Vendor");
		btnSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cl.show(card, "sellerlogin");
			}
		});
		btnSeller.setBounds(600, 400, 200, 40);
		loginPanel.add(btnSeller);
		
		
		
		
		
		// ---------- buyer panels ---------- 
		
		JPanel buyerLogin = new JPanel(new BorderLayout());
		this.add(buyerLogin, "buyerlogin");
		buyerLogin.setBackground(Color.PINK);
		buyerLogin.setLayout(null);
		
		
		
		JLabel lblBuyerUsername = new JLabel("Username");
		lblBuyerUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBuyerUsername.setBounds(318, 222, 80, 28);
		buyerLogin.add(lblBuyerUsername);
		
		JTextField buyerUserField = new JTextField();
		buyerUserField.setBounds(400, 217, 299, 40);
		buyerLogin.add(buyerUserField);
		buyerUserField.setColumns(10);
		
		JLabel lblBuyerPassword = new JLabel("Password");
		lblBuyerPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBuyerPassword.setBounds(320, 281, 72, 28);
		buyerLogin.add(lblBuyerPassword);
		
		JTextField buyerPasswordField = new JTextField();
		buyerPasswordField.setBounds(400, 271, 299, 40);
		buyerLogin.add(buyerPasswordField);
		buyerPasswordField.setColumns(10);
		
		JButton btnBuyerEnter = new JButton("Enter");
		btnBuyerEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Buyer buy = null;
                AbstractUser tempUser;

                String tempUserName = buyerUserField.getText();
                String tempPW = buyerPasswordField.getText();

                if(currentUsers.isUserNameInList(tempUserName, currentUsers.usersList)) {
                    if(currentUsers.getUser(tempUserName).getUserPassword().equalsIgnoreCase(tempPW)) {
                        tempUser = currentUsers.getUser(tempUserName);
                        if(tempUser.getUserType().equalsIgnoreCase("Buyer")) {
                            buy = (Buyer) tempUser;
                        } else {
                        	// called if user exists, pw exists, just the wrong userType
                        	JOptionPane.showMessageDialog(btnBuyerEnter, "Incorrect user type\nYou are not a buyer", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                    } else {
                    	// called if can't find the password
                    	JOptionPane.showMessageDialog(btnBuyerEnter, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                   
                } else {
                	// called if can't find the user
                	JOptionPane.showMessageDialog(btnBuyerEnter, "User " + tempUserName + " does not exist!", "Error", JOptionPane.ERROR_MESSAGE); 
       
                }

                // this is called if all parts of the login are successful!
                if(buy != null) {
                	
                	// removes the username and password from textboxes
                	buyerUserField.setText("");
                    buyerPasswordField.setText("");
                	
                	// returns loginPanel to the first login screen
                	cl.first(card);
                	
                	// changes to the seller panels
                    BuyerDriver bd = new BuyerDriver(buy, currentMarketplace);
                    Frame currentFrame = (Frame) SwingUtilities.getWindowAncestor(card);
                    currentFrame.changePanel(bd);
                    
                    
                } else {
                	// called if login fails. simply deletes password field
                	buyerPasswordField.setText(""); 
                }
            }
        });
		btnBuyerEnter.setBounds(400, 475, 100, 40);
        buyerLogin.add(btnBuyerEnter);
        
        // the return function
        JButton btnBuyerBack = new JButton("Return");
        btnBuyerBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// returns to the first card
				cl.first(card);
				
				// also empties the user entry fields
				buyerUserField.setText("");
                buyerPasswordField.setText("");
			}
		});
		btnBuyerBack.setBounds(50, 50, 100, 40);
		buyerLogin.add(btnBuyerBack);
		
		
		// ---------- seller panels ----------
		
		JPanel sellerLogin = new JPanel();
		this.add(sellerLogin, "sellerlogin");
		sellerLogin.setLayout(null);
		sellerLogin.setBackground(SystemColor.activeCaption);
		
		JLabel lblSellUsername = new JLabel("Username");
		lblSellUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSellUsername.setBounds(318, 222, 80, 28);
		sellerLogin.add(lblSellUsername);
		
		JTextField SellUserField = new JTextField();
		SellUserField.setBounds(400, 217, 299, 40);
		sellerLogin.add(SellUserField);
		SellUserField.setColumns(10);
		
		JLabel lblSellPassword = new JLabel("Password");
		lblSellPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSellPassword.setBounds(320, 281, 72, 28);
		sellerLogin.add(lblSellPassword);
		
		JTextField SellPasswordField = new JTextField();
		SellPasswordField.setBounds(400, 271, 299, 40);
		sellerLogin.add(SellPasswordField);
		SellPasswordField.setColumns(10);
		
		JButton btnSellEnter = new JButton("Enter");
        btnSellEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Seller sell = null;
                AbstractUser tempUser;

                String tempSellUserName = SellUserField.getText();
                String tempSellPW = SellPasswordField.getText();

                if(currentUsers.isUserNameInList(tempSellUserName, currentUsers.usersList)) {
                    if(currentUsers.getUser(tempSellUserName).getUserPassword().equalsIgnoreCase(tempSellPW)) {
                        tempUser = currentUsers.getUser(tempSellUserName);
                        if(tempUser.getUserType().equalsIgnoreCase("Seller")) {
                            sell = (Seller) tempUser;
                        } else {
                        	// called if user exists, pw exists, just the wrong userType
                        	JOptionPane.showMessageDialog(btnSellEnter, "Incorrect user type\nYou are not a vendor", "Error", JOptionPane.ERROR_MESSAGE); 
                        }
                    } else {
                    	// called if can't find the password
                    	JOptionPane.showMessageDialog(btnSellEnter, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                   
                } else {
                	// called if can't find the user
                	JOptionPane.showMessageDialog(btnSellEnter, "User " + tempSellUserName + " does not exist!", "Error", JOptionPane.ERROR_MESSAGE); 
       
                }

                // this is called if all parts of the login are successful!
                if(sell != null) {
                	
                	// removes the username and password from textboxes
                	SellUserField.setText("");
                    SellPasswordField.setText("");
                	
                	// returns loginPanel to the first login screen
                	cl.first(card);
                	
                	// changes to the seller panels
                    SellerDriver sd = new SellerDriver(sell, currentMarketplace);
                    Frame currentFrame = (Frame) SwingUtilities.getWindowAncestor(card);
                    currentFrame.changePanel(sd);
                    
                    
                } else {
                	// called if login fails. simply deletes password field
                	SellPasswordField.setText(""); 
                }
            }
        });
        btnSellEnter.setBounds(400, 475, 100, 40);
        sellerLogin.add(btnSellEnter);
        
        // the return function
        JButton btnSellBack = new JButton("Return");
		btnSellBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// returns to the first card
				cl.first(card);
				
				// also empties the user entry fields
				SellUserField.setText("");
                SellPasswordField.setText("");
			}
		});
		sellerLogin.setLayout(null);
		btnSellBack.setBounds(50, 50, 100, 40);
		sellerLogin.add(btnSellBack);
	
		
		
		// ---------- admin panels ----------
		JPanel adminLogin = new JPanel(new BorderLayout());
		this.add(adminLogin, "adminlogin");
		adminLogin.setLayout(null);
		adminLogin.setBackground(Color.LIGHT_GRAY);
		
		JButton btnAdminBack = new JButton("Return");
		btnAdminBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cl.first(card);
			}
		});
		btnAdminBack.setBounds(50, 50, 100, 40);
		adminLogin.add(btnAdminBack);

	}
}
