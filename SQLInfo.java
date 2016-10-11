package CS349BankAccount;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLInfo {
	
	private java.sql.Connection con;
	@SuppressWarnings("unused")
	private static java.sql.Statement stmt;
	
	public SQLInfo() throws SQLException{
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (java.lang.ClassNotFoundException e) {
		System.out.println(e);
		System.exit(0);
	}

	String url = getURL();
	String userID = getUserName();
	String password = getPassword();
	con = DriverManager.getConnection(url, userID, password);
	stmt = con.createStatement();
	
	createAccount();
	createTable();
	
	}
	
	protected static String getURL(){
		return("jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/wspn8c");
	}
	
	protected static String getUserName(){
		return("wspn8c");
	}
	
	protected static String getPassword(){
		return ("QbZhr7gEjG");
	}
	
	protected static String getBalance(int AccountNum){
		return ("SELECT account_balance FROM acount WHERE account ID =" +AccountNum+";");
	}
	
	protected static String setBalance(int amount, int account){
		return ("UPDATE ACCOUNT SET account_balance = " 
					+ amount + " WHERE account_id = " + account + ";");
	}
	
	protected static String populateTable(){
		return ("SELECT * FROM account;");
	}

	protected static String createAccount() throws SQLException{
		return ("CREATE TABLE Accounts "
				+ "(AcctID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
				+ "AcctName VARCHAR(64),"
				+ "Balance INT NOT NULL);");		
	}

	protected static String createTable() throws SQLException{
		return ("create table account (account_id INT NOT NULL AUTO_INCREMENT, "
				+ "account_name VARCHAR(64), account_balance INT NOT NULL,"
				+ " PRIMARY KEY (account_id);");
	}	
}
