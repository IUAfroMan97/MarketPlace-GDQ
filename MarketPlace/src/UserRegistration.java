import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserRegistration extends JPanel{

    Marketplace currentMarketplace;
    JPanel currentSession;
    


    public UserRegistration(Marketplace mk){
        setBackground(new Color(144, 238, 144));

        this.currentMarketplace = mk;
        this.currentSession = this;

        this.setSize(900, 600);
        this.setVisible(true);
        this.setLayout(null);
        
        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// returns you to the login panel
        		
        		Frame currentFrame = (Frame) SwingUtilities.getWindowAncestor(currentSession);
				currentFrame.changePanel(currentFrame.loginPanel);
        	}
        });
        btnReturn.setBounds(50, 50, 100, 40);
        add(btnReturn);
        
        JLabel lblWelcome = new JLabel("User Registration");
        lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setBounds(275, 50, 350, 68);
        add(lblWelcome);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(265, 171, 160, 16);
        add(lblUsername);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(265, 218, 160, 16);
        add(lblPassword);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(265, 274, 160, 16);
        add(lblEmail);
        
        JLabel lblUserType = new JLabel("User Type");
        lblUserType.setBounds(265, 325, 160, 16);
        add(lblUserType);
        
        JLabel lblStartingBalance = new JLabel("<html>Starting Balance (USD)<br>    Honor system don't take more than you need</html>");
        lblStartingBalance.setBounds(265, 375, 160, 68);
        add(lblStartingBalance);
        
        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(435, 168, 200, 22);
        add(txtUsername);
        txtUsername.setColumns(10);
        
        JTextField txtPassword = new JTextField();
        txtPassword.setBounds(435, 215, 200, 22);
        add(txtPassword);
        txtPassword.setColumns(10);
        
        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(435, 271, 200, 22);
        add(txtEmail);
        txtEmail.setColumns(10);
        
        JTextField txtBalance = new JTextField();
        txtBalance.setBounds(435, 382, 200, 22);
        add(txtBalance);
        txtBalance.setColumns(10);
        
        JComboBox typeComboBox = new JComboBox();
        typeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Buyer", "Seller", "Administrator"}));
        typeComboBox.setBounds(435, 322, 116, 22);
        add(typeComboBox);
        
        JButton btnRegister = new JButton("Register!");
        btnRegister.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		// gets the text from each of the given fields
        		String username = txtUsername.getText();
        		String password = txtPassword.getText();
        		String userType = (String) typeComboBox.getSelectedItem();
        		String email = txtEmail.getText();
        		Double balance = Double.parseDouble(txtBalance.getText());
        		
        		if(username.isEmpty() || password.isEmpty() || email.isEmpty() || balance == 0){
        			JOptionPane.showMessageDialog(btnRegister, "Error", "Please fill out every field", JOptionPane.ERROR_MESSAGE); 

        		}
        		else{
        			
        			// create the user, show completion dialog
        			currentMarketplace.createUser(username, password, userType, email, balance);
        			JOptionPane.showMessageDialog(btnRegister, "You successfully created user " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
        			
        			// clearing everything
        			txtUsername.setText("");
        			txtPassword.setText("");
        			txtEmail.setText("");
        			txtBalance.setText("");
        			typeComboBox.setSelectedIndex(0);
        			
        			
        			Frame currentFrame = (Frame) SwingUtilities.getWindowAncestor(currentSession);
    				currentFrame.changePanel(currentFrame.loginPanel);
        		}
        		 
        		 
        		
        	}
        });
        btnRegister.setBounds(350, 456, 200, 40);
        add(btnRegister);
        
        
        

    }
}