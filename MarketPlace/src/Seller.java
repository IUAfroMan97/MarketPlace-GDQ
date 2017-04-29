import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Seller extends AbstractUser {
	
	private String userName;
	private String userPassword;
	private String userEmail;
	private double userBalance;
	
	
	public Seller(String userName, String userPassword, String userEmail, double userBalance){
	
		super(userName, userPassword, "Seller", userEmail, userBalance);
		
		
		this.userName = this.getUserName();
		this.userPassword = this.getUserPassword();
		this.userEmail = this.getUserEmail();
		this.userBalance = this.getUserBalance();
		
		push();
	}
	
	public Seller(String userID, String userName, String userPassword, String userEmail, double userBalance) {
		super(userID, userName, userPassword, "Seller", userEmail, userBalance);
		
	}
	
	private void push(){
		// pushes information to the database
		Connection con = null;
		try {
			con=Database.mycon(); //connects to the database via address
			
			String query="insert into Users values(?,?,?,?,?,?)"; //sql for the database
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
	
	private void update() {
		Connection con = null;
		try {
			con=Database.mycon(); //connects to the database via address
			
			//sql query for the database
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
		this.userBalance += payment;
		update();
	}
	
	@Override
	void setBalance(double amount) {
		this.userName = this.getUserName();
		this.userPassword = this.getUserPassword();
		this.userEmail = this.getUserEmail();
		this.userBalance = amount;
		update();
		
	}
}
	

