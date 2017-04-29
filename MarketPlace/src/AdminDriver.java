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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class AdminDriver extends JPanel{
	
	// need to have these instance variables in order to alter the greater program
	AdminDriver currentSession;
	private Administrator currentUser;
	private Marketplace currentMarketplace;
	private Users currentUsers;
	
	// necessary for refreshing userViewer
	JScrollPane userScrollPane;
	
	public AdminDriver(Administrator currentUser, Marketplace mk){
		currentSession = this;
		this.currentUser = currentUser;
		this.currentMarketplace = mk;
		
		// grabs users from marketplace
		this.currentUsers = currentMarketplace.getCurrentUsers();
		
		this.setSize(900, 600);
		this.setVisible(true);
		this.setBackground(Color.PINK);
		this.setLayout(null);
		
		
		// ---------- building the frame around the main pane ----------
		
		
		
		// displays current user ID
		JLabel lblAdminID = new JLabel("Current User: " + currentUser.getUserID());
		lblAdminID.setBounds(12, 8, 200, 20);
		this.add(lblAdminID);
		
		
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
		
		
		// ---- Tabbed Pane ---- 
		// where we will keep all the data etc
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				
				tabbedPane.setBounds(8, 30, 880, 530);
				this.add(tabbedPane);
		
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
		btnChangeUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// pops dialog box to get input
				 String inputValue = JOptionPane.showInputDialog(btnChangeUsername, "Input new username"); 
				 
				 if (inputValue != null && inputValue != "") {
					 currentUser.changeUserName(inputValue);
				 }
				 
			}
		});
		btnChangeUsername.setBounds(224, 65, 149, 25);
		overview.add(btnChangeUsername);
		
		
		
		// email stuff
		JLabel lblEmail = new JLabel("E-mail: " + currentUser.getUserEmail());
		lblEmail.setBounds(12, 107, 200, 16);
		overview.add(lblEmail);
	
		JButton btnChangeEmail = new JButton("Change E-mail");
		btnChangeEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane.showInputDialog(btnChangeEmail, "Input new email"); 
				
				if (inputValue != null && inputValue != "") {
					 currentUser.changeUserEmail(inputValue);
				 }
				
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
				
			}
		});
		btnChangePassword.setBounds(224, 141, 149, 25);
		overview.add(btnChangePassword);
		
		// creating the password label (using * so it's """secure""")
		String pwLabel = "Password: ";
		// will print out as many asterisks as there are letters for the string
		for(int i = 0; i < currentUser.getUserPassword().length(); i++){
			pwLabel += "*";
		}
		JLabel lblPassword = new JLabel(pwLabel);
		lblPassword.setBounds(12, 145, 200, 16);
		overview.add(lblPassword);
		
		
		// NOTE! Overview panel must be reloaded by logging out and back in to accept changes
		
		
		// INventory Panel
		InventoryPanel inventoryPanel = new InventoryPanel(currentMarketplace, currentUser);
		tabbedPane.add("Inventory", inventoryPanel);
		
		//------------- User view ------------!!
		
		JPanel userView = new JPanel();
		userView.setLayout(null);
		userView.setSize(860, 530);
		userView.setBackground(Color.LIGHT_GRAY);
		tabbedPane.add("User View", userView);
		
		
		
		JButton btnUserRefresh = new JButton("Refresh");
		btnUserRefresh.setBounds(305, -2, 97, 23);
		btnUserRefresh.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// pulls most recent list from the database
				currentUsers.pull();
				
				// removes old scroll pane, destroys it
				remove(userScrollPane);
				userScrollPane.invalidate();
				
				revalidate();
				
				// creates a new scrollPane, displays and adds it
				userRun(userView);
				
				
			}
		});
		userView.add(btnUserRefresh);
		
		
		// creates new scrollPane and displays users on it
		userRun(userView);





		// ------- Transaction Viewer ---------
		JPanel transView = new JPanel();
		transView.setLayout(null);
		transView.setSize(860, 530);
		transView.setBackground(Color.LIGHT_GRAY);
		tabbedPane.add("Transaction Viewer", transView);


		JPanel transViewer = new JPanel();
		transViewer.setLayout(new BoxLayout(transViewer, BoxLayout.Y_AXIS));
		currentUsers.pullTransList();

		// gets transaction list from users
		ArrayList<Transaction> transList = currentUsers.transList;
		System.out.println(transList.size());

		// creates a panel of just the right height
		int transViewHeight = 100 * transList.size();
		transViewer.setBounds(0, 0, 860, transViewHeight);




		for(Transaction t : transList){
			// creates and adds a transCard for every transaction in the database
			TransCard tc = new TransCard(t);
			transViewer.add(tc);
			transViewer.add(Box.createRigidArea(new Dimension(0, 2)));
		}



		// creates a scrolling pane to display transactions and adds it to the panel
		JScrollPane transScrollPane = new JScrollPane(transViewer);

		transScrollPane.setBounds(5, 20, 860, 460);
		transScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		transScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		
	
		transView.add(transScrollPane);

	}

	// called to create and add the userPanel
	public void userRun(JPanel parent){
		
		if(userScrollPane != null){
			// removes the old pane 
			parent.remove(userScrollPane);
		}
		
		JPanel viewer = new JPanel();
		viewer.setLayout(new BoxLayout(viewer, BoxLayout.Y_AXIS));

		
		
		ArrayList<AbstractUser> userList = currentMarketplace.getCurrentUsers().usersList;

		
		int viewHeight = 100 * userList.size();
		viewer.setBounds(0, 0, 860, viewHeight);
		


		// for user list creates a userCard and adds it to the panel
		for(AbstractUser u : userList){

			UserCard current = new UserCard(u, currentMarketplace);
			viewer.add(current);
			viewer.add(Box.createRigidArea(new Dimension(0, 2)));
		}





		userScrollPane = new JScrollPane(viewer);

		userScrollPane.setBounds(5, 20, 860, 460);
		userScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		userScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		
		// adds the scrolling pane to the given panel which will always be the userView
		parent.add(userScrollPane);
		parent.revalidate();
		


	}

}