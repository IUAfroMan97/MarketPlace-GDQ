import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UserCard extends JPanel{

	private AbstractUser currentUser;
	private Marketplace currentMarketplace;

	public UserCard(AbstractUser user, Marketplace mk){

		this.currentUser = user;
		this.currentMarketplace = mk;

		this.setMinimumSize(new Dimension(800, 100));
		this.setMaximumSize(new Dimension(800, 100));
		this.setPreferredSize(new Dimension(800, 100));

		this.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username: " + currentUser.getUserName());
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(12, 13, 250, 16);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: " + currentUser.getUserPassword());
		lblPassword.setBounds(12, 36, 250, 16);
		add(lblPassword);
		
		JLabel lblUserType = new JLabel("User Type: " + currentUser.getUserType());
		lblUserType.setBounds(274, 36, 199, 16);
		add(lblUserType);
		
		JLabel lblCurrentBalance = new JLabel("Current Balance: " + currentUser.getUserBalance());
		lblCurrentBalance.setBounds(274, 61, 193, 16);
		add(lblCurrentBalance);
		
		JLabel lblEmail = new JLabel("Email: " + currentUser.getUserEmail());
		lblEmail.setBounds(12, 61, 250, 16);
		add(lblEmail);
		
		JLabel lblUserId = new JLabel("User ID: " + currentUser.getUserID());
		lblUserId.setBounds(274, 14, 300, 16);
		add(lblUserId);
		
		JButton btnChangeUsername = new JButton("Change Username");
		btnChangeUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String inputValue = JOptionPane.showInputDialog(btnChangeUsername, "Input new username"); 
				 
				 if (inputValue != null && inputValue != "") {
					 currentUser.changeUserName(inputValue);
				 }
				
			}
		});
		btnChangeUsername.setBounds(600, 1, 150, 23);
		add(btnChangeUsername);
		
		JButton btnPassword = new JButton("Change Password");
		btnPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog(btnPassword, "Input new password"); 
				
				if (inputValue != null && inputValue != "") {
					 currentUser.changeUserPassword(inputValue);
				 }
				//revalidate();
			}
		});
		btnPassword.setBounds(600, 26, 150, 23);
		add(btnPassword);
		
		JButton btnChangeEmail = new JButton("Change Email");
		btnChangeEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog(btnChangeEmail, "Input new email"); 
				
				if (inputValue != null && inputValue != "") {
					 currentUser.changeUserEmail(inputValue);
				 }
				
			}
		});
		btnChangeEmail.setBounds(600, 51, 150, 23);
		add(btnChangeEmail);
		
		JButton btnChangeBalance = new JButton("Change Balance");
		btnChangeBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String inputValue = JOptionPane.showInputDialog(btnChangeBalance, "Input new balance"); 
				Double newBalance = Double.parseDouble(inputValue);
				 if (inputValue != null && inputValue != "") {
					 
					 if(currentUser.getUserType().equalsIgnoreCase("Buyer")){
						 // the buyer change value defaults to subtracting money. therefore, we need to subtract old from new
						 // then give this (usually) negative value to the subtraction method to add it
						 double tempBalance = currentUser.getUserBalance() - newBalance;
						 currentUser.alterBalance(tempBalance);
						 						 
					 }
					 else{
						 // these user methods add the value. so new - old will give the value we need to add
						 double tempBalance = newBalance - currentUser.getUserBalance();
						 currentUser.alterBalance(tempBalance);
					 }
					 
				 }
				 //revalidate();
				 //overview.repaint();
			}
		});
		btnChangeBalance.setBounds(600, 76, 150, 23);
		add(btnChangeBalance);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int delete = JOptionPane.showConfirmDialog(btnDeleteUser, "Are you sure you want to delete?");
			
				 // if they choose yes, delete will be 0. if no, delete will be 1
				 
				 if(delete==0){
					 currentMarketplace.deleteUser(currentUser.getUserID());
				 }
				 
				 revalidate();
			}
		});
		btnDeleteUser.setBounds(475, 76, 100, 23);
		add(btnDeleteUser);
	}
}
