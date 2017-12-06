import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.TreeSelectionModel;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class TableState {
	
	
	public DefaultTableModel model;
	
	
	public TableState(DefaultTableModel model)
	{
		this.model = model;
	}

}
