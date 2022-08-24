package BudgetHierarchy;

public class Savings extends Table{
	
	public double[] savingsCompounded;
	public double[] savings;
	public double initialSavings;
	public double monthlySavings;
	public int initialTs;
	private boolean initialSet;
	public static double interestRate;
	public static double yearlyInterest;

	
	Savings(){
		initialTs = -1;
		initialSet = true;
		yearlyInterest = 8;
		savings = new double[SIZE];
		savingsCompounded = new double[SIZE];
		initialSavings = 0;
	}

	/**
	 * Setsavings will set the savings across all timespans, and will calculate the compound interest
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param value
	 * @param ts
	 */
	public void setSavings(double value, double initialSavings, int ts) {
		if (initialSet = true) {
			initialSet = false;
			initialTs = ts;
		}
		monthlySavings = value;
		
		savings[ts] = initialSavings + (returnMonthlyVal(value,ts)) * tsToMonths(ts);
		savingsCompounded[ts] = initialSavings + (returnMonthlyVal(value,ts)) * tsToMonths(ts);
			
			for (int i = ts+1; i < SIZE; i++) {

				savings[i] = round(savings[i-1] + 
						((returnMonthlyVal(value,ts)) * tsToMonths(i)));
				
				savingsCompounded[i] = round(getCompoundInterest(savings[i-1], tsToMonthsTotal(i)) + 
						((returnMonthlyVal(value,ts)) * tsToMonths(i)));

			}
	}
	
//	/**
//	 * returns the compound interest given an initial value, and the time in months
//	 * 
//	 * @author Jonathan Steele jonwhsteele@gmail.com
//	 * @param principle
//	 * @param time
//	 * @return
//	 */
//	private static double getCompoundInterest(double principle, double months) {		
//		
//		interestRate = 1 + ((double)yearlyInterest/100/12);	//interest rate per month				
//        double multiplier = 1;
//        
//        for (int i = 0; i < months; i ++) {		//here I multiply the exponents out
//        	multiplier = ((double)multiplier * interestRate);
//        }
//        
//		return (principle * multiplier);
//    }
	
	/**
	 * returns the compound interest given an initial value, and the time in months
	 * 
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param principle
	 * @param time
	 * @return
	 */
	private static double getCompoundInterest(double principle, double months) {		
		
		interestRate = yearlyInterest/100;			      
        double compoundedInterest = 0;
        compoundedInterest = principle * Math.pow((1 + interestRate/12), months);
        
		return (compoundedInterest);
    }

	/**
	 * This method is used for the compound interest calculator 
	 * This method takes an input of int ts, which is then turned into the total months
	 * that that timespan spans. ex if ts is 4, it is 3 months, and last was 2, so it will return 1. 
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts
	 * @return
	 */
	private double tsToMonths(int ts) {
		
		if (ts > SIZE) { throw (new ArrayIndexOutOfBoundsException());}
		
		double months = 0;
		
		switch (ts) {
		
		case -1:
			return 0;
		case 0:
			return 1 / 4.34524;	//week
		case 1:
			return 2 / 4.34524;	//biweek
		case 2:
			return 1;				//month
		case 3:
			return 1;				//2
		case 4:
			return 1;				//3
		case 5:
			return 1;				//4
		case 6:
			return 2;				//6
		case 7:
			return 2;				//8
		case 8:
			return 4;			//year
		case 9:
			return 12;			//2
		case 10:
			return 12;			//3
		case 11:
			return 24;			//5
		case 12:
			return 24;			//7
		case 13:
			return 36;			//10
		case 14:	
			return 120;			//20
		case 15:
			return 120;			//30
		default:
			break;
			}
			
		return (Double) null;
	}
	/**
	 * Get method used to return the compounded savings of this time period
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts
	 * @return
	 */
	public double getSavingsCompounded(int ts) {
		return savingsCompounded[ts];
	}

	/**
	 * This method returns the savings of this time period, without the compounded interest.
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts
	 * @return
	 */
	public double getSavings(int ts) {
		// TODO Auto-generated method stub
		return savings[ts];
	}

	/**
	 * This method is used when we want to set the initial savings. It is done so in case
	 * we already set the monthly savings, and need to update the entire savings[] array accross time
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param input
	 * @param ts
	 */
	public void setInitialSavings(double input, int ts) {
		initialSavings = input;
		savings[ts] = input + savings[ts];
		savingsCompounded[ts] = input + savingsCompounded[ts];
		setSavings(monthlySavings, input, ts);
	}

	/**
	 * This method is used to change the interest rate of my compound interest calculator
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param interestRate
	 */
	public void setInterestRate(double interestRateParam) {
		this.yearlyInterest = interestRateParam;
		
	}

	/**
	 * This method is used to find the amount the user paid to go toward savings
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts. ts is a parameter that corresponds to a different span of time, timespan.
	 * @return returns the amount paid by the user of this time span. If they pay 1000 monthly,
	 * and the timespan is 8 (1 year), it will return 12000.
	 */
	public double getAmountPaid(int ts) {
		return monthlySavings * tsToMonthsTotal(ts);
	}

	/**
	 * This method is used when calculating the total amount allocated every month for savings.
	 * It returns the ts in the amount of total months
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param ts
	 * @return
	 */
	private double tsToMonthsTotal(int ts) {
		
		if (ts > SIZE) { throw (new ArrayIndexOutOfBoundsException());}
		
		double months = 0;
		
		switch (ts) {
		case -1:
			return 0;
		case 0:
			return 1 / 4.34524;	//week
		case 1:
			return 2 / 4.34524;	//biweek
		case 2:
			return 1;				//month
		case 3:
			return 2;				//2
		case 4:
			return 3;				//3
		case 5:
			return 4;				//4
		case 6:
			return 6;				//6
		case 7:
			return 8;				//8
		case 8:
			return 12;			//year
		case 9:
			return 24;			//2
		case 10:
			return 36;			//3
		case 11:
			return 60;			//5
		case 12:
			return 84;			//7
		case 13:
			return 120;			//10
		case 14:	
			return 240;			//20
		case 15:
			return 360;			//30
		default:
			return (Double) null;
			}
	}
	
}