package BudgetHierarchy;

public class Table {
	
	protected final int SIZE = 16;
	
	/**
	 * rounds a type double to 2 decimal places.
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param value
	 * @return returns a rounded number of 2 decimal places
	 */
	public static double round(double value) {
		
		int places = 2;
		
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	/**
	 * This method accepts a monthlyValue, and will return the correct value per ts
	 * It will be used when trying to convert a monthly value when updating/setting tables
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param monthlyVal
	 * @param ts
	 * @return
	 */
	public double convertThis(double monthlyVal, int ts) {

		if (ts > SIZE) { throw (new ArrayIndexOutOfBoundsException());}
		
		double val = 0;
		
		switch(ts) {
			case 0: {
				val = monthlyVal * 12/52.1249;
				break;
			}
			case 1: {
				val = monthlyVal * 12/52.1249 * 2;
				break;
			}
			case 2: {
				val = monthlyVal;
				break;
			}
			case 3: {
				val = monthlyVal*2;
				break;
			}
			case 4: {
				val = monthlyVal*3;
				break;
			}
			case 5: {
				val = monthlyVal*4;
				break;
			}
			case 6: {
				val = monthlyVal*6;
				break;
			}
			case 7: {
				val = monthlyVal*8;
				break;
			}
			case 8: {
				val = monthlyVal*12;
				break;
			}
			case 9: {
				val = monthlyVal*24;
				break;
			}
			case 10: {
				val = monthlyVal*36;
				break;
			}
			case 11: {
				val = monthlyVal*12*5;
				break;
			}
			case 12: {
				val = monthlyVal*12*7;
				break;
			}
			case 13: {
				val = monthlyVal*12*10;
				break;
			}
			case 14: {
				val = monthlyVal*12*20;
				break;
			}
			case 15: {
				val = monthlyVal*12*30;
				break;
			}
		}
		return val;
		
	}
	
	/**
	 * This method accepts an input and a timespan, then will return the monthly value
	 * if projected.
	 * 
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param input
	 * @param ts
	 * @return
	 */
	public double returnMonthlyVal(double input, int ts) {
		
		if (ts > SIZE) { throw (new ArrayIndexOutOfBoundsException());}
		
		double monthlyVal = 0;
		switch(ts) {
			case 0: {
				monthlyVal = input * 52.1249 / 12;
				break;
			}
			case 1: {
				monthlyVal = input * 52.1249 / 2 / 12;
				break;
			}
			case 2: {
				monthlyVal = input;
				break;
			}
			case 3: {
				monthlyVal = input/2;
				break;
			}
			case 4: {
				monthlyVal = input/3;
				break;
			}
			case 5: {
				monthlyVal = input/4;
				break;
			}
			case 6: {
				monthlyVal = input/6;
				break;
			}
			case 7: {
				monthlyVal = input/8;
				break;
			}
			case 8: {
				monthlyVal = input/12;
				break;
			}
			case 9: {
				monthlyVal = input/12/2;
				break;
			}
			case 10: {
				monthlyVal = input/12/3;
				break;
			}
			case 11: {
				monthlyVal = input/12/5;
				break;
			}
			case 12: {
				monthlyVal = input/12/7;
				break;
			}
			case 13: {
				monthlyVal = input/12/10;
				break;
			}
			case 14: {
				monthlyVal = input/12/20;
				break;
			}
			case 15: {
				monthlyVal = input/12/30;
				break;
			}
		}
		return monthlyVal;
		
	}
}
