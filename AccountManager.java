package CS349BankAccount;

import java.sql.*;

import javax.swing.JOptionPane;


public class AccountManager {

	private java.sql.Connection con;
	private java.sql.Statement stmt;	

	public Boolean transfer(int fromAccnt, int toAccount, int amount){
		
			try {
				con.setAutoCommit(false);
				int debt = (getBalance(fromAccnt) - amount);
				int credit = (getBalance(toAccount) + amount);

				String debit = SQLInfo.setBalance(fromAccnt, debt);
				String credits = SQLInfo.setBalance(toAccount, credit);

				stmt.executeUpdate(debit);
				stmt.executeUpdate(credits);
				con.commit();
				con.setAutoCommit(true);
			} 
			catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
			
	}
	public int getBalance(int accntNum){
		int balance = 0;
		ResultSet temp;
		String tableBalance = SQLInfo.getBalance(accntNum);
		try {
			temp = stmt.executeQuery(tableBalance);
			temp.next();
			balance = temp.getInt(1);
		} catch (SQLException err) {
		    JOptionPane.showMessageDialog(null, err.toString(), 
		    		"Request contained an error.", JOptionPane.ERROR_MESSAGE);
		}
		return balance;
	}
}



	