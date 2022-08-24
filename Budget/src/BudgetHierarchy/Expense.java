package BudgetHierarchy;

public class Expense extends Table{
	
	final static double SALES_TAX = 0.12;

	public String name;
	public double[] value;
	public boolean recurring;
	public boolean inclTax;
	public int type;
	public double tax[];
	public boolean firstTime;
	public int firstTs;
	public double firstVal;
	
	Expense(){
		type = 9;
		name = "";
		recurring = false;
		inclTax = false;
		tax = new double[SIZE];
		value = new double[SIZE];
		firstTime = false;
		firstTs = -1;
		firstVal = 0;
	}
	
	/**
	 * Parameterized constructor for initializing without the value yet. If a value is to be entered,
	 * use setValue()
	 * @param type
	 * @param name
	 * @param recurring
	 * @param inclTax
	 */
	Expense(int type, String name, boolean recurring, boolean inclTax){
		this.type = type;
		this.name = name;
		this.recurring = recurring;
		this.inclTax = inclTax;
		tax = new double[SIZE];
		value = new double[SIZE];
	}

	/**
	 * This method will set the values of value[] given an input, and the time span of that
	 * input. 
	 * Afterwards, it will set its tax[] array if inclTax is true, if it is false, it sets
	 * all tax[] values as true
	 * 
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param input
	 * @param ts
	 */
	public void setValue(double input, int ts) {

		//this conditional statement is used to find the first timespan and first value entered,
		//in the event we need to go back and recalculate, compensating for a change in recurrance.
		if (firstTime == false) {
			firstTs = ts;
			firstVal = input;
		}
		
		firstTime = true;
		
		for (int i = 0; i < SIZE; i++) {
			
			//if the expense entered is recurring, it will project values across time
			if (recurring == true)
				value[i] = round(convertThis(returnMonthlyVal(input, ts), i));
			//if the expense entered is not recurring, it remains the same throughout time
			else
				value[i] = round(input);
		}
		
			for (int i = 0; i < SIZE; i++) {
				tax[i] = round(value[i] * SALES_TAX);
			}
	}
	
	/**
	 * This method simply returns the value of this expense at a given ts (timespan)
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts
	 * @return
	 */
	public double getValue(int ts) {

		if (ts > SIZE) { throw (new ArrayIndexOutOfBoundsException());}
		
		return value[ts];
	}
	
	/**
	 * simply returns the tax at a given timespan
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts
	 * @return
	 */
	public double getTax(int ts) {

		if (ts > SIZE) { throw (new ArrayIndexOutOfBoundsException());}
		
		if (inclTax == true)
			return tax[ts];
		else
			return 0;
	}
	
}
