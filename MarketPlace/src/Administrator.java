import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Administrator extends AbstractUser{

	public Administrator(String userName, String userPassword, String userEmail){
		
		// administrator will always be of type admin and balance 0
		
		super(userName, userPassword, "Administrator", userEmail, 0);
		
		push();
		
	}
	
	public Administrator(String userID, String userName, String userPassword, String userEmail, double userBalance) {
		super(userID, userName, userPassword, "Administrator", userEmail, userBalance);
	}
	
	public void push(){
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
	
	public ArrayList<String> viewPurchaseHistory(Buyer target){
		
		// TODO: 
		// using the given buyer, call/clone their purchased variable

		return null;
	}

	public ArrayList<String> viewPostHistory(Seller target){

		// TODO: 
		// using the given seller, call/clone their postHistory variable

		return null;
	}

	public ArrayList<String> viewSoldHistory(Seller target){

		// TODO: 
		// using the given seller, call/clone their soldHistory variable

		return null;
	}
	
	public ArrayList<String> viewTransactionHistory(){
		
		// TODO: 
		// returns transaction history for the entire marketplace
		
		return null;
	}
	
	
	@Override
	void changeUserName(String newUserName) {
		// TODO able to change own username
		
	}
	
	void changeUserName(AbstractUser target, String newUserName){
		// TODO search for instance of the targeted user
		// change userName for that user
		
	}

	
	@Override
	void changeUserPassword(String newUserPassword) {
		// TODO change own password
		
	}
	
	void changeUserPassword(AbstractUser target, String newUserPassword){
		// TODO changes targeted user's password
	}

	
	@Override
	void changeUserEmail(String newUserEmail) {
		// TODO change own email
		
	}
	
	void changeUserEmail(AbstractUser target, String newUserEmail){
		// TODO change targeted user's email
	}

	
	@Override
	void alterBalance(double payment) {
		// this method won't return anything as administrator's balance will always be 0
		
	}
	
	

}
