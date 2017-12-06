import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

public class AddWindow {
	private JFrame frame;
	private JTable table;
	private JTextField name;
	private JTextField book;
	private JTextField phone;
	private JTextField email;
	
	public AddWindow(JTable table) {
		
					// Create a New Window for Add Button 
					// to Change Table Values.
		
					this.table = table;
					JFrame.setDefaultLookAndFeelDecorated(true);
					frame = new JFrame("Add");
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
					
					name = new JTextField(twentyCharDoc, "",  20);
					book = new JTextField(twentyCharDoc1, "",  20);
					phone = new JTextField(twelveCharDoc, "",  12);
					email = new JTextField(twentyCharDoc2, "",  20);
								
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
					JButton saveButton = new JButton("Create");
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
			
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			
			Vector<String> newRow = new Vector<String>();
			newRow.addElement(name.getText());
			newRow.addElement(book.getText());
			newRow.addElement(phone.getText());
			newRow.addElement(email.getText());
        	
        	
        	model.addRow(newRow);
        	
			frame.setVisible(false);
			frame.dispose();
		}
    	
    }
	
	 public ImageIcon createImage(String path)
	    {
	    	return new ImageIcon(java.awt.Toolkit.getDefaultToolkit().getClass().getResource(path));
	    }

}
