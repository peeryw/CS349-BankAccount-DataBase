package CS349BankAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Manager {
	
    private Connection conect;
    private Statement statemnt;
    
    public Manager() throws SQLException{
    	
        String url = "jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/wspn8c";
        String userID = "wspn8c";
        String password = "tZrFLVzffV";
   
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        
        catch(java.lang.ClassNotFoundException e) {
            System.out.println(e);
            System.exit(0);
        }
       
        conect = DriverManager.getConnection(url,userID,password);
        statemnt = conect.createStatement();
    }
    
	
	public void transfer (int fromAccnt, int toAccount, int amount){
		
		try {
			conect.setAutoCommit(false);
			int debitor = (getBalance(fromAccnt) - amount);
			int creditor = (getBalance(toAccount) + amount);

			String debit = SQLUpdater.modifyBalance(fromAccnt, debitor);
			String credit = SQLUpdater.modifyBalance(toAccount, creditor);

			statemnt.executeUpdate(debit);
			statemnt.executeUpdate(credit);
			conect.commit();
			conect.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public int getBalance(int accntNum){
		
		int balance = 100;
		ResultSet temp;
		String tableBalance = SQLUpdater.getBalance(accntNum);
		
		try {
			temp = statemnt.executeQuery(tableBalance);
			temp.next();
			balance = temp.getInt(1);
		} catch (SQLException err) {
		    JOptionPane.showMessageDialog(null, err.toString(), "Database query contained an error.", JOptionPane.ERROR_MESSAGE);
		}
		
		return balance;
	}

	public ArrayList<String> refreshTable() {
		
		ResultSet tempRS = null;
		String refresh = SQLUpdater.populateTable();
		ArrayList<String> arr = new ArrayList<String>();
		
		try {
			tempRS = statemnt.executeQuery(refresh);
			java.sql.ResultSetMetaData SetMData = tempRS.getMetaData();
			int numberColumns = SetMData.getColumnCount();
			boolean next = tempRS.next();

			while (next) {
				int j = 1;
				for (int i = 1; i <= numberColumns; i++) {
					String tempString = tempRS.getString(i);

					// integer columns
					if (i == 1 || i == 3) {

						arr.add((tempString));
					} else
						arr.add((tempString));
				}
				j++;
				next = tempRS.next();
			}

		} catch (SQLException err) {
			JOptionPane.showMessageDialog(null, err.toString(), "Database error.", JOptionPane.ERROR_MESSAGE);
		}
		
		return arr;
	}
}
