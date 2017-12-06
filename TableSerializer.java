import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableSerializer {
	
	
	
	public TableState load(File loadFile, JTable table){
	 try (Scanner scanner = new Scanner(loadFile)) {
         try {
        	 
        	 DefaultTableModel model = (DefaultTableModel) table.getModel();
        	 Vector<Vector> data = new Vector<Vector>();
        	 
        	 Vector<String> columns = new Vector<String>();
             columns.addElement("Name");
             columns.addElement("Book");
             columns.addElement("Phone Number");
             columns.addElement("Email");
        	 
        	
        	 
        	  while (scanner.hasNextLine()) {
        		  
        		  String e1 = scanner.next();
        		  String e2 = scanner.next();
        		  String e3 = scanner.next();
        		  String e4 = scanner.next();
        		  scanner.nextLine();
        		  
        		 data.addElement(createNewDataRow(e1,e2,e3,e4));
        		 
        		  
        		  
        	  }
        	  
        	  model.setDataVector(data, columns);
        	  
        	  return new TableState(model);
         
         }
         catch (NoSuchElementException | IllegalArgumentException ex) {
             throw new RuntimeException("Malformed line.");
         }
         
	 }
	 
	 catch (FileNotFoundException e) {
        throw new RuntimeException("Problem opening file.");
    }
	 
	}
	
	public void save(File saveFile, TableState state) {
        try (PrintWriter writer = new PrintWriter(saveFile)) {
        	
        	Vector<Vector> data = state.model.getDataVector();
        	
        	for (Vector<String> rows : data)
        	{
        		
        		writer.println(rows);
        		
        	}
            
            }
         catch (FileNotFoundException e) {
            throw new RuntimeException("Problem writing file.");
        }
    }
	
	public Vector<String> createNewDataRow(String c1, String c2, String c3, String c4)
	{
		 Vector<String> rows = new Vector<String>();
		 
		 rows.addElement(trimDataString(c1,0));
		 rows.addElement(trimDataString(c2,1));
		 rows.addElement(trimDataString(c3,2));
		 rows.addElement(trimDataString(c4,3));
		 
		 return rows;
		 
		 
	}
	
	public String trimDataString(String input, int index)
	{
		String result = "";
		
		if (index == 0)
		{
			input = input.replace("[", "");
		}
		
		if (index == 3)
		{
			input = input.replace("]", "");
		}
		
		input = input.replace(",", "");
		
		result = input;
		
		return result;
		
	}

}
