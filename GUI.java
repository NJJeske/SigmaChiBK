import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class GUI extends JFrame {
	
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;
	
	private JTable table;
	private DefaultTableModel model;
	private Vector<String> columns;
	private boolean isSelected = false;
	private String sName;
	private String sBook;
	private String sPhone;
	private String sEmail;

	

	
	
	// GUI used to store contact info for people who have checked out a book.
	
	public GUI(){
		 	setTitle("Sigma Chi Book Keeper");
	        setSize(WIDTH, HEIGHT);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	    	setIconImage(createImage("/res/icon=150x.png").getImage());
	       
	        createContent();
	        newFile();
	        
	        setResizable(false);
	        setLocationRelativeTo(null);
	        setVisible(true);
	        
	}
	
	
	
	
	
	
	
	
	
	
	// Create the starting conditions of the GUI uses a JTable object to keep info together.
	// Starts out with one empty row for the JTable
	// Data is stored into a Vector of Strings
	
	private void createContent() {
        createMenu();
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout());
        
        
        
        //Empty row
        Vector<String> rowOne = new Vector<String>();
        rowOne.addElement("");
        rowOne.addElement("");
        rowOne.addElement("");
        rowOne.addElement("");
     
        Vector<Vector> data = new Vector<Vector>();
        data.addElement(rowOne);
         
         // Column Labels for the JTable
         columns = new Vector<String>();
         columns.addElement("Name");
         columns.addElement("Book");
         columns.addElement("Phone Number");
         columns.addElement("Email");
        
   
        
        // Creation of the JTable with set features
        table = new JTable(data,columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setCellSelectionEnabled(true);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Listener for the JTable
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
               
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow > -1)
                {
                   isSelected = true;
                   sName = table.getModel().getValueAt(selectedRow, 0).toString();
                   sBook = table.getModel().getValueAt(selectedRow, 1).toString();
                   sPhone = table.getModel().getValueAt(selectedRow, 2).toString();
                   sEmail = table.getModel().getValueAt(selectedRow, 3).toString();
                   
                }
                else
                {
                   isSelected = false;
                }
                
                
            }
        });
        
        panel1.add(scrollPane);
        add(panel1);
        
        // --------------------------------------------------------------------
        // Add Buttons for JTable 
        // --------------------------------------------------------------------
     
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 3));
        add(panel2, BorderLayout.SOUTH);
        
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        
        panel2.add(panel3);
        panel2.add(panel4);
        panel2.add(panel5);
        
        JButton button1 = new JButton("Add");
        JButton button2 = new JButton("Edit");
        JButton button3 = new JButton("Delete");
        	
        button1.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
        button1.setBackground(Color.GREEN);
        button1.addActionListener(new AddButtonListener());
        
        button2.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
        button2.setBackground(Color.GREEN);
        button2.addActionListener(new EditButtonListener());
        
        button3.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
        button3.setBackground(Color.RED);
        button3.addActionListener(new DeleteButtonListener());
            
            
        panel3.add(button1);
        panel4.add(button2);
        panel5.add(button3);
        
        
       
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        // --------------------------------------------------------------------
        // New File
        // --------------------------------------------------------------------

        JMenuItem newMenu = new JMenuItem("New");
        menu.add(newMenu);
        newMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	newFile();
            }
        });

        menu.addSeparator();

        // --------------------------------------------------------------------
        // Saving and Loading
        // --------------------------------------------------------------------

        final TableSerializer serializer = new TableSerializer();

        JMenuItem load = new JMenuItem("Open");
        menu.add(load);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                JDialog.setDefaultLookAndFeelDecorated(true);
               

                if (chooser.showOpenDialog(GUI.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                    	
                    	model = (DefaultTableModel) table.getModel();
                    	TableState state = serializer.load(chooser.getSelectedFile(), table);
                    	
                    	model = state.model;
                    	
                    	
                    	
                      
                    } catch (Exception ex) {
                    	JDialog.setDefaultLookAndFeelDecorated(true);
                        JOptionPane.showMessageDialog(GUI.this, ex.getMessage());
                    }
                }

               
            }
        });

        JMenuItem saveGame = new JMenuItem("Save");
        menu.add(saveGame);
        saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                JDialog.setDefaultLookAndFeelDecorated(true);
              
                if (chooser.showSaveDialog(GUI.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                    	
                    	model = (DefaultTableModel) table.getModel();
                    	serializer.save(chooser.getSelectedFile(), new TableState(model));
                    	
                    } catch (Exception ex) {
                    	JDialog.setDefaultLookAndFeelDecorated(true);
                        JOptionPane.showMessageDialog(GUI.this, ex.getMessage());
                    }
                }

               
            }
        });
    }
   // Creates a new JTable (Like a reset back to its initial state)
    private void newFile() {
    	
    	model = (DefaultTableModel) table.getModel();
    	
    	Vector<String> rowOne = new Vector<String>();
        rowOne.addElement("");
        rowOne.addElement("");
        rowOne.addElement("");
        rowOne.addElement("");
 
        
        Vector<Vector> data = new Vector<Vector>();
        data.addElement(rowOne);
    	
        model.setDataVector(data, columns);
        table.setModel(model);
    	
    }
    
    // Opens new window if row is to be added into JTable
    private class AddButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AddWindow add = new AddWindow(table);

			 
		}
    	
    }
    
    
    // Opens selected row with an Edit Window and puts string data from Vector into the Edit Window
    private class EditButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			

				
			 	if (isSelected)
			 	{
			    EditWindow edit = new EditWindow(table, sName, sBook, sPhone, sEmail);
			    
			 	}
			 	else
			 	{
			 		JDialog.setDefaultLookAndFeelDecorated(true);
			 		JOptionPane.showMessageDialog(null, "Please select a row to be edited.");
			 	}
	
	}
	
    	
  }
    
   
    // Deletes JTable row that was selected
    private class DeleteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (isSelected)
		 	{
				
				JDialog.setDefaultLookAndFeelDecorated(true);
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (response == JOptionPane.YES_OPTION)
				{
					int selectedRow = table.getSelectedRow();
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
                	model.removeRow(selectedRow);
				}
				
		 	}
		 	else
		 	{
		 		JDialog.setDefaultLookAndFeelDecorated(true);
		 		JOptionPane.showMessageDialog(null, "Please select a row to be deleted.");
		 	}
		}
    	
    }
 

    public static void main(String[] args) {
    	try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    	GUI.setDefaultLookAndFeelDecorated(true);
		new GUI();
		
		

	}
    
    public ImageIcon createImage(String path)
    {
    	return new ImageIcon(java.awt.Toolkit.getDefaultToolkit().getClass().getResource(path));
    }
    
    
    
 
}
