import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	public static Connection mycon() throws ClassNotFoundException, SQLException {
		
		String hostOnlineDB = "jdbc:mysql://db.soic.indiana.edu:3306/c212s17_jacgood"; //address of the database
		String userNameOnlineDB = "c212s17_jacgood"; //username
		String passwordOnlineDB = "gdq2020"; //password

		Class.forName("com.mysql.jdbc.Driver");
		//"jdbc:mysql://127.0.0.1:3306/sys", "root" , "root"
		Connection con=DriverManager.getConnection(hostOnlineDB, userNameOnlineDB, passwordOnlineDB); //connection to database

		if(con!= null) {
			System.out.println("Connected");
		} else {
			System.out.println("Disconnected");
		}
		return con;
	}
}
