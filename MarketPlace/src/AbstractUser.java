import java.util.UUID;

abstract class AbstractUser {
	
	private final String userID;
	private String userName;
	private String userPassword;
	private String userType;
	private String userEmail;
	private double userBalance;
	
	public AbstractUser(String userName, String userPassword, String userType, String userEmail, double userBalance){
		
		this.userID = this.generateUserID();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userType = userType;
		this.userEmail = userEmail;
		this.userBalance = userBalance;
		
	}
	
	public AbstractUser(String userID, String userName, String userPassword, String userType, String userEmail, double userBalance) {
		//used for the making of buyer without adding it to the database
		this.userID = userID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userType = userType;
		this.userEmail = userEmail;
		this.userBalance = userBalance;
		
	}
	
	public String toString() {
		return "User: " + this.userID + ", " + this.userName + ", " + this.userPassword + ", " + this.userType + ", " + this.userEmail + ", " + this.userBalance;
	}
	
	private String generateUserID(){
		
		/* TODO: UUID.randomUUID().toString().substring()
		 * 
		 */
		
		return UUID.randomUUID().toString().substring(0, 6);
		
	}
	
	
	
	// abstract methods
	
	abstract void changeUserName(String newUserName);
	
	abstract void changeUserPassword(String newUserPassword);
	
	abstract void changeUserEmail(String newUserEmail);
	
	abstract void alterBalance(double payment);
	
	
	// get methods
	
	public String getUserID() { return userID; }
	
	public String getUserName() { return userName; }
	
	public String getUserPassword() { return userPassword; }
	
	public String getUserType() { return userType; }
	
	public String getUserEmail() { return userEmail; }
	
	public double getUserBalance() { return userBalance; }
	
	
}
