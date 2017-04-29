import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class InventoryPanel extends JPanel{

	// used to affect the greater program
	Marketplace currentMarketplace;
	AbstractUser currentUser;
	Inventory currentInventory;
	
	// will always hold the inventory as it should be seen
	ArrayList<Item> inv;
	
	// used to filter and only show the items we want
	JScrollPane scrollPane;
	String filterText;


	public InventoryPanel(Marketplace mk, AbstractUser user){

		
		super();
		this.setLayout(null);

		this.currentMarketplace = mk;
		this.currentUser = user;
		
		// drawing the inventory itself

		// gets current inventory and the list of items it has
		currentInventory = currentMarketplace.getCurrentInventory();
		inv = filterItems(filterText, currentInventory.inventory);

		// adding the search bar
		JTextField itemSearch = new JTextField();
		itemSearch.setBounds(5, 0, 200, 20);
		add(itemSearch);
		

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(205, -2, 97, 23);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// change the pane from this to login panel
				String searchInput = itemSearch.getText();

				
				filterText = searchInput;
				remove(scrollPane);
				
				// the code will now recompile, and create the scrollpane again
				// this time, the filter text will be the same
				revalidate();
				
				run();
				
			}
		});
		add(btnSearch);
		
		// we need this here. if not, it'll never draw the new canvas
		run();
		
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(305, -2, 97, 23);
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				currentInventory.pull();
				
				// updates to newest inventory
				remove(scrollPane);
				
				// updates and redoes the item viewer with the newest inventory
				// saves any changes done in the itemCard
				revalidate();
				run();
				
			}
		});
		add(btnRefresh);
		

	}


	public void run(){

		JPanel viewer = new JPanel();
		viewer.setLayout(new BoxLayout(viewer, BoxLayout.Y_AXIS));

		// viewer will aways be the exact right height, enough to fit all cards
		int viewHeight = 100 * inv.size();
		viewer.setBounds(0, 0, 860, viewHeight);

		// just to make sure this has the most up to date inventory
		currentInventory = currentMarketplace.getCurrentInventory();
		inv = filterItems(filterText, currentInventory.inventory);

		for(Item i : inv){
			// creates an item card for every item and adds it to the viewer
			ItemCard current = new ItemCard(i, currentUser, currentMarketplace);
			viewer.add(current);
			viewer.add(Box.createRigidArea(new Dimension(0, 2)));
		}




		scrollPane = new JScrollPane(viewer);

		// creates a scrollpane that will house viewer, allowing it to fit on a panel smaller than it
		scrollPane.setBounds(5, 20, 860, 460);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// adds to the panel
		add(scrollPane);	


	}

	public ArrayList<Item> filterItems(String searchBarText, ArrayList<Item> inventory) {
	
		ArrayList<Item> tempInv = new ArrayList<>();

		if (searchBarText == null || searchBarText.isEmpty()) {
			return currentInventory.inventory;
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