import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Buyer extends AbstractUser{

	// history of the buyer's transactions
	private ArrayList<Item> purchased;
	
	
	private String userName;
	private String userPassword;
	private String userEmail;
	private double userBalance;
	
	

	public Buyer(String userName, String userPassword, String userEmail, double userBalance){

		super(userName, userPassword, "Buyer", userEmail, userBalance);

		this.purchased = new ArrayList<Item>();
		
		this.userName = this.getUserName();
		this.userPassword = this.getUserPassword();
		this.userEmail = this.getUserEmail();
		this.userBalance = this.getUserBalance();
		
		push();
	}

	public Buyer(String userID, String userName, String userPassword, String userEmail, double userBalance) {
		super(userID, userName, userPassword, "Buyer", userEmail, userBalance);
		
		this.purchased = new ArrayList<Item>();
	}

	private void push() {
		Connection con = null;
		try {
			con=Database.mycon();

			String query="insert into Users values(?,?,?,?,?,?)";
			PreparedStatement st=con.prepareStatement(query);

			st.setString(1, this.getUserID());
			st.setString(2, this.userName);
			st.setString(3, this.userPassword);
			st.setString(4, this.getUserType());
			st.setString(5, this.userEmail);
			st.setDouble(6, this.userBalance);
			st.executeUpdate();
			
			
			
			query="INSERT into History values(?,?,?,?,?,)";
			st=con.prepareStatement(query);
			
			st.setString(1, this.historyID);
			st.setString(2, this.itemID);
			st.setString(3, this.buyerID);
			st.setString(4, this.sellerID);
			st.executeUpdate();
			

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void update() {
		Connection con = null;
		try {
			con=Database.mycon();
			
			String query="UPDATE Users SET userName = ?, userPassword = ?, userEmail = ?, userBalance = ? WHERE userID = ? ";
			PreparedStatement st=con.prepareStatement(query);
			
			st.setString(1, this.userName);
			st.setString(2, this.userPassword);
			st.setString(3, this.userEmail);
			st.setDouble(4, this.userBalance);
			st.setString(5, this.getUserID());
			
			st.executeUpdate();
			con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Item> getPurchasedHistory() {
		return this.purchased;
	}


	@Override
	void changeUserName(String newUserName) { 
		// TODO: change userName
		this.userName = newUserName;
		this.userPassword = this.getUserPassword();
		this.userEmail = this.getUserEmail();
		this.userBalance = this.getUserBalance();
		update();
	}


	@Override
	void changeUserPassword(String newUserPassword) {
		// TODO change userName
		this.userName = this.getUserName();
		this.userPassword = newUserPassword;
		this.userEmail = this.getUserEmail();
		this.userBalance = this.getUserBalance();
		update();
	}


	@Override
	void changeUserEmail(String newUserEmail) {
		// TODO change email
		this.userName = this.getUserName();
		this.userPassword = this.getUserPassword();
		this.userEmail = newUserEmail;
		this.userBalance = this.getUserBalance();
		update();
	}


	@Override
	void alterBalance(double payment) {
		// TODO change balance
		this.userName = this.getUserName();
		this.userPassword = this.getUserPassword();
		this.userEmail = this.getUserEmail();
		this.userBalance = this.getUserBalance() - payment;
		update();
	}
}
