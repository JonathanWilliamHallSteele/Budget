package BudgetHierarchy;
import java.util.Stack;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Data {
	
	public Income income;
	public Savings savings;
	public Expenses expenses;
	
	static final int NUMBER_OF_ELEMENTS = 15;
	static final double SALES_TAX = 0.12;
	
	
	//ts aka timespan is used to calculate monthly, weekly, and yearly values
	int ts;
	//lts is used to store the last timespan (used for updates)
	int lts;
	
	public Data(){
		income = new Income();
		savings = new Savings();
		expenses = new Expenses();
	}
	
	/**
	 * This method is used when the user changes the combo box containing weekly, monthly, and yearly.
	 * It will change the timespan value (int ts) to 0,1, or 2 respectively.
	 * This value is then used to update the arrays to reflect weekly, monthly, or yearly values.
	 * @param selection
	 */
	public int changeTimeSpan(String selection) {
		
		switch(selection){

			case "Weekly":
				return 0;
			case "BiWeekly":
				return 1;
			case "Monthly":
				return 2;
			case "2 Months":
				return 3;
			case "3 Months":
				return 4;
			case "4 Months":
				return 5;
			case "6 Months":
				return 6;
			case "8 Months":
				return 7;
			case "1 Year":
				return 8;
			case "2 Years":
				return 9;
			case "3 Years":
				return 10;
			case "5 Years":
				return 11;
			case "7 Years":
				return 12;
			case "10 Years":
				return 13;
			case "20 Years":
				return 14;
			case "30 Years":
				return 15;
			default:
				return 16;
				
		}
	}
	/**
	 * This method is used when calculating totals of income 
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts
	 * @return the total income - income tax
	 */
	public double getTotalIncome(int ts) {

		return income.income[ts] - income.incomeTax[ts];
	}

	/**
	 * This method is used when calculating totals of expense, savings, and income.
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts timespan
	 * @return the net total from this timespan. 
	 */
	public double getTotal(int ts) {
		return (getTotalIncome(ts) - expenses.getTotalExpense(ts) - savings.getAmountPaid(ts));
	}
	/**
	 * This method is used to find the total expenses of this time period
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts
	 * @return returns the total expenses of this time period
	 */
	public double getSavings(int ts) {
		return savings.getSavingsCompounded(ts);
	}
}
