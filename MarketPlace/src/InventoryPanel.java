import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class InventoryPanel extends JPanel{

	Marketplace currentMarketplace;
	AbstractUser currentUser;
	JPanel viewer;
	Inventory invClass;
	ArrayList<Item> inv;


	public InventoryPanel(Marketplace mk, AbstractUser user){

		// TODO: 
		// possibly passing every single class the same instance of marketplace

		super();
		this.setLayout(null);

		this.currentMarketplace = mk;
		this.currentUser = user;
		
		viewer = new JPanel();
		viewer.setLayout(new BoxLayout(viewer, BoxLayout.Y_AXIS));

		// drawing the inventory itself

		invClass = currentMarketplace.getCurrentInventory();
		inv = invClass.inventory;

		// adding the search bar
		JTextField itemSearch = new JTextField();
		itemSearch.setBounds(5, 0, 200, 20);
		add(itemSearch);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(205, -2, 97, 25);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// change the pane from this to login panel
				String searchInput = itemSearch.getText();
				
				System.out.println("Search Input: " + searchInput);
				
				inv = filterItems(searchInput, inv);
				//viewer = drawInventory(filterItems(searchInput, inv), viewer);
				
				for(Item i : inv){
					System.out.println(i.getItemName());
				}
				repaint();
			}
		});
		add(btnSearch);
		
		// this one is weird
		// so every item card takes up 100 space. by multiplying inventory size by 100, we get the right height
		int viewHeight = 100 * inv.size();
		viewer.setBounds(0, 0, 860, viewHeight);

		/*
		 * JTextField buyerPasswordField = new JTextField();
		buyerPasswordField.setBounds(400, 271, 299, 40);
		buyerLogin.add(buyerPasswordField);
		buyerPasswordField.setColumns(10);
		 */

		viewer = drawInventory(inv, viewer);


		JScrollPane scrollPane = new JScrollPane(viewer);

		scrollPane.setBounds(5, 20, 860, 460);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane);

	}

	public JPanel drawInventory(ArrayList<Item> inv, JPanel panel){
		for(Item i : inv){
			ItemCard current = new ItemCard(i, currentUser, currentMarketplace);
			panel.add(current);
			panel.add(Box.createRigidArea(new Dimension(0, 2)));
		}

		return panel;
	}

	public ArrayList<Item> filterItems(String textBar, ArrayList<Item> inventory) {
		String searchBarText = textBar;
		ArrayList<Item> tempInv = new ArrayList<>();

		if (searchBarText == null) {
			return invClass.inventory;
		} else {
			//display based on the name
			
			if (searchBarText.equalsIgnoreCase("Home & Garden") || searchBarText.equalsIgnoreCase("Home and Garden")) {
				for(Item element : inventory) {
					if (element.getItemCategory().equalsIgnoreCase("Home & Garden")) {
						tempInv.add(element);
					}
				}
				System.out.println("here");
				return tempInv;
			}
		}
		System.out.println("hopefully not here");
		return inventory;
	}
}