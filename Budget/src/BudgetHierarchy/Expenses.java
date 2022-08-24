package BudgetHierarchy;

public class Expenses extends Table{

	final static int NUMBER_OF_ELEMENTS = 30;
	public Expense[] expenses;
	
	/**
	 * constructor creates an expense object for each element in expenses[]
	 */
	Expenses(){
		expenses = new Expense[NUMBER_OF_ELEMENTS];
		for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
			expenses[i] = new Expense();
		}
	}
	
	/**
	 * returns total expenses (not inclding tax) for this given timespan
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts
	 * @return
	 */
	public double getTotalExpense(int ts) {
		double sum = 0;
		
		for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
				sum = sum + expenses[i].getValue(ts) + expenses[i].getTax(ts);
		}
		return (sum);
	}
	
	/**
	 * Returns total tax for a given timespan
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts
	 * @return
	 */
	public double getTotalTax(int ts) {
		double sum = 0;
		
		for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
			if (expenses[i].inclTax == true)
				sum  = sum + expenses[i].getTax(ts);
		}
		return (sum);
	}
	
	/**
	 * returns tax and expenses together
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts
	 * @return
	 */
	public double getTotalSum(int ts) {
		return getTotalExpense(ts) + getTotalTax(ts);
	}
	
}

