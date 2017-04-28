
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.BorderLayout;

public class Frame extends JFrame{

	JPanel cards;
	CardLayout cl;
	JPanel currentPanel;
	LoginPanel loginPanel;
	
	
	public Frame(String s){
		super(s);
		
		Marketplace currentMarketplace = new Marketplace();
		 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 600);
		this.setResizable(false);
		
		// adding the first panel
		loginPanel = new LoginPanel(currentMarketplace);
		this.getContentPane().add(loginPanel, BorderLayout.CENTER);
		this.add(loginPanel);
		
		currentPanel = loginPanel;
		
	
	}
	
	public void changePanel(JPanel jp){
		this.remove(currentPanel);
		this.add(jp);
		this.repaint();
		currentPanel = jp;
	}
	
	public static void main(String[] args){
		Frame frame = new Frame("GDQ Marketplace");
		
		
		frame.setVisible(true);
		
		
	}
}
