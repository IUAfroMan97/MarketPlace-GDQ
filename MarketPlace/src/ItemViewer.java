import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

public class ItemViewer extends JPanel{

	ArrayList<Item> inv;
	
	public ItemViewer(){
		this.setVisible(true);
		this.setLayout(new FlowLayout());
		this.setBackground(Color.CYAN);
			
		
		
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);
		frame.setResizable(false);
		
		ItemViewer panel = new ItemViewer();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.add(panel);
		
		//frame.pack();
		frame.setVisible(true);
	}
}
