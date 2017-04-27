import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Buyer extends AbstractUser{

	// history of the buyer's transactions
	public ArrayList<String> purchased;

	public Buyer(String userName, String userPassword, String userEmail, double userBalance){

		super(userName, userPassword, "Buyer", userEmail, userBalance);

		purchased = new ArrayList<String>();

		push();
	}

	public Buyer(String userID, String userName, String userPassword, String userEmail, double userBalance) {
		super(userID, userName, userPassword, "Buyer", userEmail, userBalance);
	}

	private void push() {
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

	private boolean buyItem(){
		// TODO: 
		// issues a call to marketplace to initialize a transaction
		// returns false if user has insufficient funds

		return false;

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
