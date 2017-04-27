import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class BuyerDriver extends JPanel{
	BuyerDriver currentSession;
	private Buyer currentUser;
	private Marketplace currentMarketplace;
	
	public BuyerDriver(Buyer currentUser, Marketplace mk){
		currentSession = this;
		this.currentUser = currentUser;
		this.currentMarketplace = mk;
		
		this.setSize(900, 600);
		this.setVisible(true);
		this.setBackground(Color.PINK);
		this.setLayout(null);
		
		
		// ---------- building the frame around the main pane ----------
		
		// where we will keep all the data etc
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(8, 30, 880, 530);
		this.add(tabbedPane);
		
		// displays current username
		JLabel lblSellID = new JLabel("Current User: " + currentUser.getUserID());
		lblSellID.setBounds(12, 8, 200, 20);
		this.add(lblSellID);
		
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// change the pane from this to login panel
				
				
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
		JLabel lblUserID = new JLabel("UserID: " + currentUser.getUserID());
		lblUserID.setBounds(12, 13, 200, 34);
		overview.add(lblUserID);
		
		// user name stuff
		JLabel lblUsername = new JLabel("Username: " + currentUser.getUserName());
		lblUsername.setBounds(12, 60, 200, 34);
		overview.add(lblUsername);
		
		JButton btnChangeUsername = new JButton("Change Username");
		btnChangeUsername.setBounds(224, 65, 149, 25);
		overview.add(btnChangeUsername);
		
		
		// user's balance
		JLabel lblBalance = new JLabel();
		lblBalance.setText("Current Account Balance:" + currentUser.getUserBalance());
		lblBalance.setBounds(663, 453, 200, 34);
		overview.add(lblBalance);
		
		
		// email malarkey
		JLabel lblEmail = new JLabel("E-mail: " + currentUser.getUserEmail());
		lblEmail.setBounds(12, 107, 200, 16);
		overview.add(lblEmail);
	
		JButton btnChangeEmail = new JButton("Change E-mail");
		btnChangeEmail.setBounds(224, 103, 149, 25);
		overview.add(btnChangeEmail);
		
		// password labels etc		
		JButton btnPassword = new JButton("Change Password");
		btnPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
			}
		});
		btnPassword.setBounds(224, 141, 149, 25);
		overview.add(btnPassword);
		
		// creating the password label (using * so it's """secure""")
		String pwLabel = "Password: ";
		// will print out as many asterisks as there are letters for the string
		for(int i = 0; i < currentUser.getUserPassword().length(); i++){
			pwLabel += "*";
		}
		JLabel lblPassword = new JLabel(pwLabel);
		lblPassword.setBounds(12, 145, 200, 16);
		overview.add(lblPassword);
		
		
		// --------- Inventory marketplace -----------
		JPanel viewer = new JPanel();
		viewer.setLayout(new BoxLayout(viewer, BoxLayout.Y_AXIS));
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setLayout(null);
		tabbedPane.add("Inventory", inventoryPanel);
		
		// gets the current inventory list 
		Inventory invClass = currentMarketplace.getCurrentInventory();
		ArrayList<Item> inv = invClass.inventory;
		
		// this one is weird
		// so every item card takes up 100 space. by multiplying inventory size by 100, we get the right height
		int viewHeight = 100 * inv.size();
		viewer.setBounds(0, 0, 860, viewHeight);
		
		
		for(Item i : inv){
			ItemCard current = new ItemCard(i, currentUser, currentMarketplace);
			viewer.add(current);
			viewer.add(Box.createRigidArea(new Dimension(0, 2)));
		}
		
		JScrollPane scrollPane = new JScrollPane(viewer);
		
		scrollPane.setSize(860, 500);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		inventoryPanel.add(scrollPane);
		
		
	}

}
