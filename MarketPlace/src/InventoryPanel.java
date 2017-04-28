import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class InventoryPanel extends JPanel{

	Marketplace currentMarketplace;
	AbstractUser currentUser;
	JScrollPane scrollPane;
	Inventory invClass;
	ArrayList<Item> inv;
	String filterText;


	public InventoryPanel(Marketplace mk, AbstractUser user){

		// TODO: 
		// possibly passing every single class the same instance of marketplace

		super();
		this.setLayout(null);

		this.currentMarketplace = mk;
		this.currentUser = user;
		
//		viewer = new JPanel();
//		viewer.setLayout(new BoxLayout(viewer, BoxLayout.Y_AXIS));

		// drawing the inventory itself

		invClass = currentMarketplace.getCurrentInventory();
		invClass.pull();
		inv = filterItems(filterText, invClass.inventory);

		// adding the search bar
		JTextField itemSearch = new JTextField();
		itemSearch.setBounds(5, 0, 200, 20);
		add(itemSearch);
		
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(205, -2, 97, 25);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Searching...");
				// change the pane from this to login panel
				String searchInput = itemSearch.getText();
				
				
				
				filterText = searchInput;
				remove(scrollPane);
				
				run();
				revalidate();
				
			}
		});
		add(btnSearch);
		
		run();

	}

	public JPanel drawInventory(ArrayList<Item> inv, JPanel panel){
		for(Item i : inv){
			ItemCard current = new ItemCard(i, currentUser, currentMarketplace);
			panel.add(current);
			panel.add(Box.createRigidArea(new Dimension(0, 2)));
		}

		return panel;
	}
	
	public void run(){
		
		JPanel viewer = new JPanel();
		viewer.setLayout(new BoxLayout(viewer, BoxLayout.Y_AXIS));
		
		int viewHeight = 100 * inv.size();
		viewer.setBounds(0, 0, 860, viewHeight);
		
		
		
	
		invClass = currentMarketplace.getCurrentInventory();
		inv = filterItems(filterText, invClass.inventory);
		
		for(Item i : inv){
			System.out.println(i.getItemName());
			ItemCard current = new ItemCard(i, currentUser, currentMarketplace);
			viewer.add(current);
			viewer.add(Box.createRigidArea(new Dimension(0, 2)));
		}
		
		

		
		scrollPane = new JScrollPane(viewer);

		scrollPane.setBounds(5, 20, 860, 460);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scrollPane.revalidate();
		viewer.revalidate();

		
		
		add(scrollPane);	

		
	}

	public ArrayList<Item> filterItems(String searchBarText, ArrayList<Item> inventory) {
	
		ArrayList<Item> tempInv = new ArrayList<>();

		if (searchBarText == null || searchBarText.isEmpty()) {
			return invClass.inventory;
		} else {
			//display based on the name
			
			if (searchBarText.equalsIgnoreCase("Home & Garden") || searchBarText.equalsIgnoreCase("Home and Garden")) {
				for(Item element : inventory) {
					if (element.getItemCategory().equalsIgnoreCase("Home & Garden")) {
						tempInv.add(element);
					}
				}
				return tempInv;
			} else if (searchBarText.equalsIgnoreCase("Electronics")) {
				for(Item element : inventory) {
					if (element.getItemCategory().equalsIgnoreCase("Electronics")) {
						tempInv.add(element);
					}
				}
				return tempInv;
			} else if (searchBarText.equalsIgnoreCase("Books")) {
				for(Item element : inventory) {
					if (element.getItemCategory().equalsIgnoreCase("Books")) {
						tempInv.add(element);
					}
				} 
				return tempInv;
			} else {
				for(Item element : inventory) {
					if (element.getItemName().equalsIgnoreCase(searchBarText)) {
						tempInv.add(element);
					}
				}
			}
			return tempInv;
		}
	}
}