package BudgetHierarchy;

public class Income extends Table{

	public double[] income;
	public double[] incomeTax;
	public double[] otherIncome;
	public double[] totals;
	public double taxRate;
	
	Income(){
		
		income = new double[SIZE];
		incomeTax = new double[SIZE];
		otherIncome = new double[SIZE];
		totals = new double[SIZE];
		taxRate = 0;
	}
	
	/**
	 * This method will accept an input, and the timespan variable.
	 * It will then use these values to set all the values for th
	 * income[] array, converting the values to each timespan
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param input
	 * @param ts
	 */
	public void setIncome(double input, int ts) {
		
		income[ts] = input;

		for (int i = 0; i < SIZE; i++) {		
			income[i] = round(convertThis(returnMonthlyVal(input, ts), i));
		}
		
		setIncomeTax();
		setTotals();
	}
	
	/**
	 * THis method will accept an input and timespan variable.
	 * It will insert the value into the otherIncome[] array, and then
	 * project it across the entire array, so we can grab whatever timespan we want.
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param input
	 * @param ts
	 */
	public void setOtherIncome(double input, int ts) {
		
		otherIncome[ts] = input;

		for (int i = 0; i < SIZE; i++) {		
			otherIncome[i] = round(convertThis(returnMonthlyVal(input, ts), i));
		}
		setTotals();
	}
	
	/**
	 * This method will set the income tax for each timespan in the
	 * incomeTax[] array. income[8] must be initialized before calling. 
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 */
	public void setIncomeTax() {
		
		double monthlyIncomeTax = getIncomeTax(income[8])/12;
		
		for (int i = 0; i < SIZE; i++) {
			incomeTax[i] = round(convertThis(monthlyIncomeTax, i));
		}
	}
	
	/**
	 * Sets the values in array totals[], based off of income[], otherIncome[],
	 * incomeTax[]. You should set these up before calling this.
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 */
	public void setTotals() {
		for (int i = 0; i < SIZE; i ++) {
			totals[i] = round(income[i] + otherIncome[i] - incomeTax[i]);	
		}
	}
	
	/**
	 * This method will return annual tax based on yearly income
	 * @author Jonathan Steele jonwhsteele@gmail.com
	 * @param yearlyIncome
	 * @return
	 */
	public double getIncomeTax(double yearlyIncome) {
		
		double provincialTax = 0; double federalTax = 0;
	
		
		//provincial taxes
		if (yearlyIncome <= 43070) {
			provincialTax = (yearlyIncome * 5.06 / 100);
		}
		else if (yearlyIncome > 43070 && yearlyIncome <= 86141) {
			provincialTax = (43070 * 5.06 / 100);
			provincialTax = provincialTax + ((yearlyIncome - 43070) * 7.70 / 100);
		}
		else if (yearlyIncome > 86141 && yearlyIncome <= 98901) {
			provincialTax = (43070 * 5.06 / 100);
			provincialTax = provincialTax + (43070 * 7.70 / 100);
			provincialTax = provincialTax + ((yearlyIncome - 86141) * 10.50 / 100);
		}
		else if (yearlyIncome > 90901 && yearlyIncome <= 120094) {	
			provincialTax = (43070 * 5.06 / 100);
			provincialTax = provincialTax + (43070 * 7.70 / 100);
			provincialTax = provincialTax + ((12760) * 10.50 / 100);
			provincialTax = provincialTax + ((yearlyIncome - 90901) * 12.29 / 100);
		}
		else if (yearlyIncome > 120094 && yearlyIncome <= 162832) {
			provincialTax = (43070 * 5.06 / 100);
			provincialTax = provincialTax + (43070 * 7.70 / 100);
			provincialTax = provincialTax + ((12760) * 10.50 / 100);
			provincialTax = provincialTax + ((29193) * 12.29 / 100);
			provincialTax = provincialTax + ((yearlyIncome - 120094) * 14.70 / 100);
		}
		else if (yearlyIncome > 162832 && yearlyIncome <= 227091) {
			provincialTax = (43070 * 5.06 / 100);
			provincialTax = provincialTax + (43070 * 7.70 / 100);
			provincialTax = provincialTax + ((12760) * 10.50 / 100);
			provincialTax = provincialTax + ((29193) * 12.29 / 100);
			provincialTax = provincialTax + ((42738) * 14.70 / 100);
			provincialTax = provincialTax + ((yearlyIncome - 162832) * 16.80 / 100);
		}
		//(yearlyIncome > 227091) 
		else{
			provincialTax = (43070 * 5.06 / 100);
			provincialTax = provincialTax + (43070 * 7.70 / 100);
			provincialTax = provincialTax + ((12760) * 10.50 / 100);
			provincialTax = provincialTax + ((29193) * 12.29 / 100);
			provincialTax = provincialTax + ((42738) * 14.70 / 100);
			provincialTax = provincialTax + ((64259) * 16.80 / 100);
			provincialTax = provincialTax + ((yearlyIncome - 227091) * 20.5 / 100);
		}
		
		
		//federal taxes
		if (yearlyIncome <= 49020) {
			federalTax = (yearlyIncome * 15 / 100);
		}
		else if (yearlyIncome > 49020 && yearlyIncome <= 98040) {
			federalTax = 49020 * 15 / 100;
			federalTax = federalTax + ((yearlyIncome - 49020) * 20.5 / 100);
		}
		else if (yearlyIncome > 98040 && yearlyIncome <= 151978) {
			federalTax = 49020 * 15 / 100;
			federalTax = federalTax + (49020 * 20.5 / 100);
			federalTax = federalTax + ((yearlyIncome - 98040) * 26 / 100);
		}
		else if (yearlyIncome > 151978 && yearlyIncome <= 216511) {
			federalTax = 49020 * 15 / 100;
			federalTax = federalTax + (49020 * 20.5 / 100);
			federalTax = federalTax + ((53938) * 26 / 100);
			federalTax = federalTax + ((yearlyIncome - 151978) * 29 / 100);
		}
		//(yearlyIncome >= 216511) 
		else {
			federalTax = 49020 * 15 / 100;
			federalTax = federalTax + (49020 * 20.5 / 100);
			federalTax = federalTax + ((53938) * 26 / 100);
			federalTax = federalTax + ((64533) * 29 / 100);
			federalTax = federalTax + ((yearlyIncome - 216511) * 33 / 100);
		}
		
		
		return provincialTax + federalTax;
	}
		
		public double getIncomeTax(int ts) {
			return incomeTax[ts];
		}
	
		public void setIncomeTax(double[] incomeTax) {
			this.incomeTax = incomeTax;
		}
	
		public double getTotals(int ts) {
			return totals[ts];
		}
	
		public void setTotals(double[] totals) {
			this.totals = totals;
		}
	
		public double getTaxRate() {
			return taxRate;
		}
	
		public void setTaxRate(double taxRate) {
			this.taxRate = taxRate;
		}
	
}
