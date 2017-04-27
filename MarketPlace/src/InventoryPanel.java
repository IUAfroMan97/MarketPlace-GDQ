import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;

public class InventoryPanel extends JPanel{
	
	Marketplace currentMarketplace;
	AbstractUser currentUser;

	public InventoryPanel(Marketplace mk, AbstractUser user){
		
		// TODO: 
		// possibly passing every single class the same instance of marketplace
		
		super();
		this.setLayout(null);
		
		this.currentMarketplace = mk;
		this.currentUser = user;
				
		// PROBLEM : Cannot scroll !!! must fix
		
		JPanel viewer = new JPanel();
		viewer.setLayout(new BoxLayout(viewer, BoxLayout.Y_AXIS));
		
				
		Inventory invClass = currentMarketplace.getCurrentInventory();
		ArrayList<Item> inv = invClass.inventory;
		
		// this one is weird
		// so every item card takes up 100 space. by multiplying inventory size by 100, we get the right height
		int viewHeight = 100 * inv.size();
		viewer.setBounds(0, 0, 860, viewHeight);
		
		
		for(Item i : inv){
			ItemCard current = new ItemCard(i, user, currentMarketplace);
			viewer.add(current);
			viewer.add(Box.createRigidArea(new Dimension(0, 2)));
		}
		
		JScrollPane scrollPane = new JScrollPane(viewer);
		
		scrollPane.setSize(860, 500);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(scrollPane);
		
	}
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(860, 530);
		frame.setResizable(false);
//		
//		InventoryPanel panel = new InventoryPanel(currentMarketplace);
//		frame.getContentPane().add(panel, BorderLayout.CENTER);
//		frame.getContentPane().add(panel);
//		
//		
		frame.setVisible(true);
	}
	
}
