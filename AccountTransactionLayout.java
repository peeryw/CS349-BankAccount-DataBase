package CS349BankAccount;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AccountTransactionLayout extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private JTextField amountField = new JTextField("",8);
	private JTextField fromField;
	private JTextField toField;

	private String[] columnNames = { "Account ID", "Account Name", "Balance" };
	private Object[][] data = { { new Integer(3), "Savings", new Integer(500) },
			{ new Integer(4), "Checking", new Integer(270) },
			{ new Integer(5), "Retirement", new Integer(1000)}};

	public AccountTransactionLayout() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());

		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
		table = new JTable(dtm);
		Dimension smallerSize = new Dimension(450, 50);
		table.setPreferredScrollableViewportSize(smallerSize);

		JScrollPane scrollPaneForTable = new JScrollPane(table);

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(4, 4, 4, 4);
		constraints.fill = GridBagConstraints.BOTH;

		contentPane.add(scrollPaneForTable, constraints);

		constraints.gridx = 0;
		constraints.weighty = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.insets = new Insets(2, 4, 2, 4);
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		JLabel toLabel = new JLabel("From:");
		contentPane.add(toLabel, constraints);

		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		fromField = new JTextField("", 8);
		// Workaround, because of:
		// http://bugs.java.com/bugdatabase/view_bug.do?bug_id=4247013
		fromField.setMinimumSize(fromField.getPreferredSize());
		contentPane.add(fromField, constraints);

		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		JLabel fromLabel = new JLabel("To:");
		contentPane.add(fromLabel, constraints);

		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		toField = new JTextField("", 8);
		toField.setMinimumSize(toField.getPreferredSize());
		contentPane.add(toField, constraints);

		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		JLabel amountLabel = new JLabel("Amount:");
		contentPane.add(amountLabel, constraints);

		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		amountField.setMinimumSize(amountField.getPreferredSize());
		contentPane.add(amountField, constraints);

		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		JButton clearButton = new JButton("Clear");
		contentPane.add(clearButton, constraints);
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				fromField.setText("");
				toField.setText("");
				amountField.setText("");
			}
		});

		constraints.gridx = 1;
		// constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		JButton transferButton = new JButton("Transfer");
		transferButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				String toString, fromString, amountString;
				int to, from, amount;
				Boolean transferred;
				AccountManager mngr = null;

				try {
				    toString = toField.getText();
				    fromString = fromField.getText();
				    amountString = amountField.getText();

				    to = Integer.parseInt(toString);
				    from = Integer.parseInt(fromString);
				    amount = Integer.parseInt(amountString);

				    transferred =  mngr.transfer(to - 1, from - 1, amount);

				    if (transferred) {
					table.setModel(new DefaultTableModel(mngr.displayData, columnNames));
				    }
				    else {
					JOptionPane.showMessageDialog(null, "Invalid transfer", "Error",
						JOptionPane.ERROR_MESSAGE);
				    }
				}
				catch (NumberFormatException nfe) {
				    JOptionPane.showMessageDialog(null, "Numbers ONLY in TO, FROM and AMMOUNT"
				    		+ " fields", "Error", JOptionPane.ERROR_MESSAGE);
				}
		}

		});
		contentPane.add(transferButton, constraints);
	}

	public static void main(String[] args) {
		JFrame frame = new AccountTransactionLayout();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public JTextField getToField(){
		return toField;
	}
	
	public JTextField getFromField(){
		return fromField;
	}

	public JTextField getAmountField() {
		return amountField;
	}

	public void setAmountField(JTextField amountField) {
		this.amountField = amountField;
	}
}