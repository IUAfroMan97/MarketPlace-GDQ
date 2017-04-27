import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Seller extends AbstractUser {

	// history of the seller's activity
	public ArrayList<Item> postHistory; 
	public ArrayList<Item> soldHistory;
	
	
	public Seller(String userName, String userPassword, String userEmail, double userBalance){
	
		super(userName, userPassword, "Seller", userEmail, userBalance);
		
		postHistory = new ArrayList<Item>();
		soldHistory = new ArrayList<Item>();
		
		push();
	}
	
	public Seller(String userID, String userName, String userPassword, String userEmail, double userBalance) {
		super(userID, userName, userPassword, "Seller", userEmail, userBalance);
	}
	
	private void push(){
		// pushes information to the database
		Connection con = null;
		try {
			con=Database.mycon();
			
			String query="insert into Users values(?,?,?,?,?,?)";
			PreparedStatement st=con.prepareStatement(query);
			
			st.setString(1, this.getUserID());
			st.setString(2, this.getUserName());
			st.setString(3, this.getUserPassword());
			st.setString(4, this.getUserType());
			st.setString(5, this.getUserEmail());
			st.setDouble(6, this.getUserBalance());
			st.executeUpdate();
			
			con.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createItem(){
		
		// TODO: 
		// called with every attribute of an item
		// issues a call to marketplace to create an item	
		
	}

	@Override
	void changeUserName(String newUserName) { 
		
		// TODO: change userName
	}


	@Override
	void changeUserPassword(String newUserPassword) {
		// TODO change userName
		
	}


	@Override
	void changeUserEmail(String newUserEmail) {
		// TODO change email
		
	}


	@Override
	void alterBalance(double payment) {
		// TODO change balance
		
	}
	
	
}
	

