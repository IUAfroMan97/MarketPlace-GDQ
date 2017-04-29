
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.BorderLayout;

// This is the main executable class. The entire GUI starts from here


public class Frame extends JFrame{

	// this is the pane that will be the one being displayed
	// will constantly be changing
	JPanel currentPanel;
	LoginPanel loginPanel;
	
	
	public Frame(String s){
		super(s);
		
		// the first and only marketplace. The main instance that all other things will stem from
		Marketplace currentMarketplace = new Marketplace();
		 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 600);
		this.setResizable(false);
		
		// adding the first panel, an instance of LoginPanel
		loginPanel = new LoginPanel(currentMarketplace);
		this.getContentPane().add(loginPanel, BorderLayout.CENTER);
		this.add(loginPanel);
		
		currentPanel = loginPanel;
		
	
	}
	
	// called whenever we need to change the current panel
	public void changePanel(JPanel jp){
		this.remove(currentPanel);
		
		// removes old panel, adds given panel 
		this.add(jp);
		this.repaint();
		currentPanel = jp;
	}
	
	public static void main(String[] args){
		
		// the one and only main method 
		Frame frame = new Frame("GDQ Marketplace");
		
		
		frame.setVisible(true);
		
		
	}
}
