package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import BudgetHierarchy.Data;
import BudgetHierarchy.Savings;

public class Window {

	final int NUMBER_OF_EXPENSES = 30;
	Data data;
	int ts;
	
	
	//Here I declare the panels and tables needed
	//I declare them here so they can be seen by multiple methods
	
	private JFrame frame;
	
	private final JPanel incomePanel = new JPanel();
		
		private final JTable incomeTable = new JTable(3,2) {			
			//Upon initializing the JTable, we will override the isCellEditable Method, and 
			//specify which cells the user can edit.
		    public boolean isCellEditable(int row, int column) {     
		    	if (column == 0|| row == 1 || row == 2)
		    		return false;    
		    	else 
		    		return true;
		    	};	
		};
	
	private final JPanel savingsPanel = new JPanel();
		
		private final JTable savingsTable = new JTable(4,2) {			
			//Upon initializing the JTable, we will override the isCellEditable Method, and 
			//specify which cells the user can edit.
		    public boolean isCellEditable(int row, int column) {     
		    	if (column == 0|| row == 3)
		    		return false;    
		    	else 
		    		return true;
		    	};	
		};
		
	private final JPanel consolePanel = new JPanel();
		private final JTextField console = new JTextField();
		private final JComboBox timespan = new JComboBox();	
		private final JButton updateResults = new JButton("Fetch");
		
	private final JPanel expensePanel = new JPanel();
		private final JTable expenseTable = new JTable(NUMBER_OF_EXPENSES,5) {
		    public boolean isCellEditable(int row, int column) {     
		    	if (column == 4)
		    		return false;    
		    	else 
		    		return true;
		    	};
			
		};
	
	private final JPanel totalsPanel = new JPanel();
		private TotalsPanel current;
		private TotalsPanel year1;
		private TotalsPanel year2;
		private TotalsPanel year5;
	/**
	 * Launching the budget app
	 */
	public static void main(String[] args) {
				try {
					Window window = new Window();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	/**
	 * Create the application.
	 */
	public Window() {
		ts = 2;
		data = new Data();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setBounds(120,0,1200,975);
		
		intitializeIncomePanel();
		frame.getContentPane().add(incomePanel);
		
		initializeSavingsPanel();
		frame.getContentPane().add(savingsPanel);

		initializeConsolePanel();
		frame.getContentPane().add(consolePanel);
		
		initializeExpensePanel();
		frame.getContentPane().add(expensePanel);
		
		initializeTotalsPanel();
		frame.getContentPane().add(totalsPanel);
		
		frame.setVisible(true);
		addActionListeners();
	}

	private void initializeExpensePanel() {
		
		JScrollPane scrollPane = new JScrollPane(expenseTable);
		
		scrollPane.setBounds(0,0,800,650);
		expensePanel.setBounds(0,225,800,650);
		expenseTable.setBounds(0,0,800,650);
		expensePanel.setLayout(null);

		expenseTable.setRowHeight(25);
		String column_names[]= {"Name","Recurrance","Add Tax","Value", "After Tax"};
	    //THE MODEL OF OUR TABLE
	    DefaultTableModel expenseModel = new DefaultTableModel(column_names, 5){
	        public Class<?> getColumnClass(int colIndex){
	        	
	        	switch (colIndex) {
	        	
	        	case 0:
	        		return String.class;
	        	case 1:
	        		return Boolean.class;
	        	case 2:
	        		return Boolean.class;
	        	case 3:
	        		return String.class;
	        	case 4:
	        		return String.class;
	        	default:
	        		return String.class;
	        	}
	        }
        };
		
		expenseModel.setColumnCount(5);
		expenseModel.setRowCount(NUMBER_OF_EXPENSES);
		expenseModel.getColumnClass(NUMBER_OF_EXPENSES);

	    expenseTable.setModel(expenseModel);
		expensePanel.add(scrollPane);	
	}

	private void initializeConsolePanel() {

		consolePanel.setBounds(400,0,400,200);
		consolePanel.setLayout(null);
		
		console.setBounds(0,0,400,100);
		timespan.setBounds(0,100,300,100);
		
		timespan.addItem("Weekly");
		timespan.addItem("BiWeekly");
		timespan.addItem("Monthly");
		timespan.addItem("2 Months");
		timespan.addItem("3 Months");
		timespan.addItem("4 Months");
		timespan.addItem("6 Months");
		timespan.addItem("8 Months");
		timespan.addItem("1 Year");
		timespan.addItem("2 Years");
		timespan.addItem("3 Years");
		timespan.addItem("5 Years");
		timespan.addItem("7 Years");
		timespan.addItem("10 Years");
		timespan.addItem("20 Years");
		timespan.addItem("30 Years");

		updateResults.setBounds(300,100,100,100);
		updateResults.setVerticalAlignment(JLabel.CENTER);
		
		consolePanel.add(console);
		consolePanel.add(timespan);
		consolePanel.add(updateResults);
		
		timespan.setSelectedIndex(2);
		
		
	}

	private void initializeTotalsPanel() {

		totalsPanel.setLayout(null);
		totalsPanel.setBounds(800,0,400,450);
		
		current = new TotalsPanel("Current Term");
		year1 = new TotalsPanel("Year 1");
		year2 = new TotalsPanel("Year 2");
		year5 = new TotalsPanel("Year 5");
		
		current.p.setBounds(0,0,400,100);
		year1.p.setBounds(0,100,400,100);
		year2.p.setBounds(0,200,400,100);
		year5.p.setBounds(0,300,400,100);
		
		totalsPanel.add(current.p);
		totalsPanel.add(year1.p);
		totalsPanel.add(year2.p);
		totalsPanel.add(year5.p);
		
	}
	
	private void initializeSavingsPanel() {
		savingsPanel.setBounds(0,100,400,100);
		savingsTable.setBounds(0,0,400,100);
		savingsTable.setRowHeight(25);
		savingsPanel.setLayout(null);
		
		savingsTable.getModel().setValueAt("Initial Savings", 0, 0);
		savingsTable.getModel().setValueAt("Savings (Per Month)", 1, 0);
		savingsTable.getModel().setValueAt("Interest Rate", 2, 0);
		savingsTable.getModel().setValueAt("Savings and Interest", 3, 0);
	
		savingsTable.getModel().setValueAt(8, 2, 1);
		
		savingsPanel.add(savingsTable);
	}
	
	private void intitializeIncomePanel() {
		incomePanel.setBounds(0,0,400,200);
		incomeTable.setBounds(0,0,400,200);
		incomePanel.setSize(400,75);
		incomeTable.setRowHeight(25);
		incomePanel.setLayout(null);
		
		incomeTable.getModel().setValueAt("Income", 0, 0);
		incomeTable.getModel().setValueAt("Income Tax", 1, 0);
		incomeTable.getModel().setValueAt("Total Income", 2, 0);
		incomePanel.add(incomeTable);
	}
	
	private void addActionListeners() {
		
		/**
		 * This actionListener will fetch all of the totals for the current Term, year 1, 2, and 5. 
		 */
		updateResults.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				current.table.setValueAt(data.getTotalIncome(ts), 0, 1);
				current.table.setValueAt(data.expenses.getTotalExpense(ts), 1, 1);
				current.table.setValueAt(data.getTotal(ts), 2, 1);
				current.table.setValueAt(data.getSavings(ts), 3, 1);
				
				year1.table.setValueAt(data.getTotalIncome(8), 0, 1);
				year1.table.setValueAt(data.expenses.getTotalExpense(8), 1, 1);
				year1.table.setValueAt(data.getTotal(8), 2, 1);
				year1.table.setValueAt(data.getSavings(8), 3, 1);
				
				year2.table.setValueAt(data.getTotalIncome(9), 0, 1);
				year2.table.setValueAt(data.expenses.getTotalExpense(9), 1, 1);
				year2.table.setValueAt(data.getTotal(9), 2, 1);
				year2.table.setValueAt(data.getSavings(9), 3, 1);
				
				year5.table.setValueAt(data.getTotalIncome(11), 0, 1);
				year5.table.setValueAt(data.expenses.getTotalExpense(11), 1, 1);
				year5.table.setValueAt(data.getTotal(11), 2, 1);
				year5.table.setValueAt(data.getSavings(11), 3, 1);
			
			}
			
		});
		
		/**
		 * This actionListener will update all of the tables when the timespan combo box (ts) is changed.
		 */
		timespan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent p) {
				
				//First, we update the timespan (ts) variable in data
				JComboBox s = (JComboBox) p.getSource();
				String selection = (String) s.getSelectedItem();
				ts = data.changeTimeSpan(selection);

				//here we set each element in income to the correct value	
				incomeTable.getModel().setValueAt(data.income.income[ts], 0, 1);
				incomeTable.getModel().setValueAt(data.income.incomeTax[ts], 1, 1);
				incomeTable.getModel().setValueAt(data.income.totals[ts], 2, 1);
				
				//now we display the total compounded savings in savingsTable.
				double thisSavingsCompounded = data.savings.savingsCompounded[ts];
				savingsTable.getModel().setValueAt(thisSavingsCompounded, 3, 1);
				
				//now we display the correct values for all of the expenses
				for (int i = 0; i < NUMBER_OF_EXPENSES; i++) {
					expenseTable.getModel().setValueAt(data.expenses.expenses[i].getValue(ts), i, 3);//Before Tax
					expenseTable.getModel().setValueAt((data.expenses.expenses[i].getValue(ts) + 
														data.expenses.expenses[i].getTax(ts)), i, 4);//After Tax
					
				}
			}
		});
		
		/**
		 * This actionlistener handles all actions performed on the expenses table.
		 */
		expenseTable.getModel().addTableModelListener(new TableModelListener () {

			@Override
			public void tableChanged(TableModelEvent e) {
				
				double input = 0;
				String name = "";
				boolean bool = false;
				
				int col = e.getColumn();
				
				//This switch decides which actions need to be performed depending on which column in expenseTable is edited.
				switch (col) {
					
					//This is the case where the name has been altered. 
					//In this case, I just update the name of the expense at the given row.
					case 0:
						try {
							name = expenseTable.getModel().getValueAt(e.getLastRow(), e.getColumn()).toString();
						}catch(Exception o) {
							console.setText("Please enter a name");	
						}
						data.expenses.expenses[e.getLastRow()].name = name;
						break;
					
					//This is the case where the recurrence is determined. 
					//I will update the recurring boolean value stored in data.expenses.expenses[ts] to the input.
					//Then, in the event that value has already been set, I must re-set the value to see the recurring 
					//expense
					case 1:
						try {
							bool = Boolean.parseBoolean(expenseTable.getModel().getValueAt(e.getLastRow(), e.getColumn()).toString());
						}catch(Exception o) {
							console.setText("Please enter numbers only");	
						}
						data.expenses.expenses[e.getLastRow()].recurring = bool;
						
						//here we check if the Expense objects value has already been set. If it has, then the recurrance
						//has effected whether or not it adds onto itself every month, so we need to change it
						//this conditional statement reaches into the Expense object of this row, and pulls the very first ts and input
						//entered. Then, now the boolean recurrance is set correctly, we can set the value again.
						if (data.expenses.expenses[e.getLastRow()].firstTime == true) 
							data.expenses.expenses[e.getLastRow()].setValue(data.expenses.expenses[e.getLastRow()].firstVal, data.expenses.expenses[e.getLastRow()].firstTs);
						
						//now we can update the values of this row
						updateTotalExpense(e.getLastRow());
						expenseTable.getModel().setValueAt(data.expenses.expenses[e.getLastRow()].value[ts], e.getFirstRow(), 3);
						
						break;
					
					//This is the case where the user determines if the tax is included or not. 
					//we update the boolean value for inclTax inside of the given Expense object
					case 2:
						try {
							bool = Boolean.parseBoolean(expenseTable.getModel().getValueAt(e.getLastRow(), e.getColumn()).toString());
						}catch(Exception o) {
							console.setText("Please enter numbers only");	
						}
						data.expenses.expenses[e.getLastRow()].inclTax = bool;
						updateTotalExpense(e.getLastRow());
						break;
						
					//This is the case where the value is changed. 
					case 3:
						try {
							input = Double.parseDouble(expenseTable.getModel().getValueAt(e.getLastRow(), e.getColumn()).toString());
						}catch(Exception o) {
							console.setText("Please enter numbers only");	
						}
						data.expenses.expenses[e.getLastRow()].setValue(input, ts);
						
						//used to update the expenseTables' total column for this row
						updateTotalExpense(e.getLastRow());
						
						break;
					case 4:
						break;
					default:
						
						break;
				}

				
				int choice = e.getLastRow();
				
				switch(choice) {
				
					default:
						break;
				}
				
			}

			//This method updates the total expense of the given row
			private void updateTotalExpense(int row) {
				
				//This conditional statement checks if the user wanted the tax to be calculated.
				//then, it updates the total cost of the expense on the expenseTable JTable
				if (data.expenses.expenses[row].inclTax == true) {
					expenseTable.setValueAt((data.expenses.expenses[row].tax[ts] + data.expenses.expenses[row].value[ts]),row, 4);
				}
				else {
					expenseTable.setValueAt(data.expenses.expenses[row].value[ts],row, 4);
				}
				
			}
			
			
		});
		
		/**
		 * This actionListener handles all actions performed on the savings table
		 */
		savingsTable.getModel().addTableModelListener(new TableModelListener() {
			//TODO Fix the bugs in the savings table. Make it so the savings go in at the beginning timespan
			//(ts == 2)instead of the current ts
			//Weekly and biweekly results are always 0, fix that 
			
			DefaultTableModel newTable = new DefaultTableModel(3,2);
			
			@Override
			public void tableChanged(TableModelEvent e) {
				double input = 0;
				
				try {
					input = Double.parseDouble(savingsTable.getModel().getValueAt(e.getLastRow(), e.getColumn()).toString());
				}catch(Exception o) {
					console.setText("Please enter numbers only");	
				}
				
				int choice = e.getLastRow();
				
				switch(choice) {
				case 0:
					
					data.savings.setInitialSavings(input,ts);
					savingsTable.getModel().setValueAt(data.savings.getSavingsCompounded(ts)+"", 3, 1);
					break;
				case 1:
					data.savings.setSavings(input, data.savings.initialSavings, ts);
					savingsTable.getModel().setValueAt(data.savings.getSavingsCompounded(ts)+"", 3, 1);
					break;
				case 2:
					data.savings.setInterestRate(input);
					double monthly = data.savings.monthlySavings;
					double initial = data.savings.initialSavings;
					int firstTs = data.savings.initialTs;
					data.savings.setSavings(monthly, initial, firstTs);
					savingsTable.getModel().setValueAt(data.savings.getSavingsCompounded(ts)+"", 3, 1);
					break;
				default:
					break;
				}
				

			}
		});
		
		/**
		 * This actionListener handles all actions performed on the income table.
		 */
		incomeTable.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				
				double input = 0;
				
				try {
					input = Double.parseDouble(incomeTable.getModel().getValueAt(e.getLastRow(), e.getColumn()).toString());
				}catch(Exception o) {
					console.setText("Please enter numbers only");	
				}
				
				int change = e.getLastRow();
				
				switch(change) {
				
				case 0:
					data.income.setIncome(input, ts);
					incomeTable.getModel().setValueAt(data.income.getIncomeTax(ts), 1, 1);//income tax
					incomeTable.getModel().setValueAt(data.income.getTotals(ts), 2, 1);//total income
					break;
				default:
					break;
				}
			}
			
		});
	}
}
