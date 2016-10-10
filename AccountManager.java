package CS349BankAccount;

import javax.swing.JOptionPane;

public class AccountManager {
	
	protected Object[][] displayData;
	AccountTransactionLayout info;

	public AccountManager (){
		;
	}

	public Boolean transfer(int i, int j, int amount) {
		String toAmount = toField.getAmountField();
		String fromAmount = j;
		String transfAmount = info.getAmountField();
		
		if(transfAmount.getAmountField() < 0 fromAcount.getFromField() ){
			String accountID = "1";
			JOptionPane.showMessageDialog(null, "Amount excedes balance of" + accountID  , "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return null;		
	}

}
