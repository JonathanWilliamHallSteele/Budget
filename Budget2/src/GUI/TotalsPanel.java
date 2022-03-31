package GUI;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TotalsPanel {

	JPanel p = new JPanel();
	JLabel label = new JLabel();;
	JScrollPane sp;
	JTable table = new JTable(4,2) {
	    public boolean isCellEditable(int row, int column) {     
	    	if (column == 0 || row == 2)
	    		return false;    
	    	else 
	    		return true;
	    	};
	};
	
	
	
	TotalsPanel(String name){
		
		table.setValueAt("Income:", 0, 0);
		table.setValueAt("Expenses:", 1, 0);
		table.setValueAt("Total:", 2, 0);
		table.setValueAt("Savings:", 3, 0);
		
		table.setRowHeight(20);
		table.setBounds(0,20,400,80);
		
		label.setText(name);
		label.setHorizontalAlignment( JLabel.CENTER );
		label.setBounds(0,0,400,20);
		
		p.setBounds(0,0,400,100);
		p.setLayout(null);

		p.add(label, BorderLayout.NORTH);
		p.add(table, BorderLayout.CENTER);
		
		
	}
	
	
	
}
