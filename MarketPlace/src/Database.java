import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	public static Connection mycon() throws ClassNotFoundException, SQLException {
		
		String hostOnlineDB = "jdbc:mysql://db.soic.indiana.edu:3306/c212s17_jacgood";
		String hostOfflineDB = "jdbc:mysql://127.0.0.1:3306/sys";
		String userNameOnlineDB = "c212s17_jacgood";
		String passwordOnlineDB = "gdq2020";
		String userNameOfflineDB = "root";
		String passwordOfflineDB = "root";

		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(hostOfflineDB, userNameOfflineDB , passwordOfflineDB);

		if(con!= null) {
			System.out.println("Connected");
		} else {
			System.out.println("Disconnected");
		}

		return con;

	}
}
