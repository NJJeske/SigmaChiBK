import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;


public class EditWindow {
	
	private JFrame frame;
	private JTable table;
	private JTextField name;
	private JTextField book;
	private JTextField phone;
	private JTextField email;
	
	
	
	public EditWindow(JTable table, String sName, String sBook, String sPhone, String sEmail) {
		
		// Create a New Window for Edit Button 
		// to Change Table Values.
		this.table = table;
		frame = new JFrame("Edit");
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setLocation(800, 400);
		frame.setSize(300, 200);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setIconImage(createImage("/res/icon=150x.png").getImage());	
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,2));
		
		Document twelveCharDoc  = new LimitedCharDocument(12);	
		Document twentyCharDoc = new LimitedCharDocument(20);
		Document twentyCharDoc1 = new LimitedCharDocument(20);
		Document twentyCharDoc2 = new LimitedCharDocument(20);
		
		name = new JTextField(twentyCharDoc, sName,  20);
		book = new JTextField(twentyCharDoc1, sBook,  20);
		phone = new JTextField(twelveCharDoc, sPhone,  12);
		email = new JTextField(twentyCharDoc2, sEmail,  20);
				                                 
			
		JLabel nameLabel = new JLabel("Name");
		JLabel bookLabel = new JLabel("Book");
		JLabel phoneLabel = new JLabel("Phone Number");
		JLabel emailLabel = new JLabel("Email");
		
		
		
		panel.add(nameLabel);
        panel.add(name);
        panel.add(bookLabel);
        panel.add(book);
        panel.add(phoneLabel);
        panel.add(phone);
        panel.add(emailLabel);
        panel.add(email);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,3));
		
		JLabel label1 = new JLabel();
		JLabel label2 = new JLabel();
		JButton saveButton = new JButton("Save");
		saveButton.setBackground(Color.GREEN);
		saveButton.addActionListener(new saveButtonListener());
		
		
		panel2.add(label1);
		panel2.add(saveButton);
		panel2.add(label2);

		
		frame.add(panel,BorderLayout.PAGE_START);
		frame.add(panel2,BorderLayout.SOUTH);
		
		
		
	}
	
	
	private class saveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int selectedRow = table.getSelectedRow();
			
			
			table.getModel().setValueAt(name.getText(), selectedRow, 0);
			table.getModel().setValueAt(book.getText(), selectedRow, 1);
			table.getModel().setValueAt(phone.getText(), selectedRow, 2);
			table.getModel().setValueAt(email.getText(), selectedRow, 3);

			
		     
			
			frame.setVisible(false);
			frame.dispose();
		}

		
	}
	
	 public ImageIcon createImage(String path)
	    {
	    	return new ImageIcon(java.awt.Toolkit.getDefaultToolkit().getClass().getResource(path));
	    }
	
	
	
	
	
	

}
