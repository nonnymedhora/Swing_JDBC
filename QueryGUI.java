/********************************************************************
* File Name :		QueryGUI.java
* Description :		QueryGUI serves as an interface to access 
*                   employee or department records
********************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class QueryGUI extends JFrame {
	/* FIELDS
	 * WIDTH and HEIGHT are constants to set the dimensions for the frame
	 * pa_InfoPanel   - JPanel housing info for QueryGUI with JTextFields and
	 *                  JLabels.
	 * pa_ButtonPanel - JPanel containing the JButtons
	 */
	private static final int WIDTH = 600;
	private static final int HEIGHT = 200;
	
	private JPanel 		pa_InfoPanel;
	private JPanel 		pa_ButtonPanel;	
	
	private JLabel 		la_empID;
	private JTextField	tf_empID;	// to hold employee id
	private JLabel 		la_empLN;
	private JTextField	tf_empLN;	// to hold employee First Name
	private JLabel 		la_empFN;
	private JTextField	tf_empFN;	// to hold employee Last Name
	private JLabel		la_DepNo;
	private JTextField	tf_DepNo;	// to hold department name

	private JLabel 		la_DepName;
	private JTextField  tf_DepName;	// to hold department numbr
		
	private JButton 	bu_Find;	// to perform "Find" operation
	private JButton 	bu_Add;		// to perform "Add" operation
	private JButton 	bu_Update;	// to perform "Update" operation
	private JButton 	bu_Delete;	// to perform "Delete" operation
	private JButton 	bu_Exit;	// to perform "Exit" operation	
	
	private JButton 	bu_Reset;	// to perform "Reset" operation
	
	/************************************************************
	* Constructor 	-  QueryGUI
	* @param 		- a string that set the title of QueryGUI.
	* @return       - N/A
	* Depscription: Constructor for constructing QueryGUI calls initUI.
	***************************************************************/	
	public QueryGUI(String theQuery) {
		this.initUI(theQuery);
	}
	
	/** Method - iniUI(String)
	 *  @return - void
	 *  @param  - String theQ -- to set the title for the frame
	 *  Description - to create GUI for QueryGUI
	 *
	 */	
	private void initUI(String theQ){
		this.setTitle (theQ);
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);

		Container cp = this.getContentPane();
		this.pa_InfoPanel = new JPanel();
		this.pa_InfoPanel.setSize(WIDTH,HEIGHT-40);

	// for employees
		if (this.getTitle().indexOf("Employee") != -1) {
			
			this.la_empID = new JLabel( "Employee ID#" );
			this.tf_empID = new JTextField(40);
			
			this.la_empLN = new JLabel( "  Last Name  " );
			this.tf_empLN = new JTextField(40);
			
			this.la_empFN = new JLabel( " First Name  " );
			this.tf_empFN = new JTextField(40);
			
			
			this.pa_InfoPanel.add(this.la_empID);
			this.pa_InfoPanel.add(this.tf_empID);
			
			this.pa_InfoPanel.add(this.la_empLN);
			this.pa_InfoPanel.add(this.tf_empLN);
			
			this.pa_InfoPanel.add(this.la_empFN);
			this.pa_InfoPanel.add(this.tf_empFN);
			
		}
			
		this.la_DepNo = new JLabel( "Department#" );
		this.tf_DepNo = new JTextField(40);
		
		this.pa_InfoPanel.add(this.la_DepNo);
		this.pa_InfoPanel.add(this.tf_DepNo);
		
		if (this.getTitle().indexOf("Department") != -1) {
		
			this.la_DepName = new JLabel( "    Department " );
			this.tf_DepName = new JTextField(40);
			this.pa_InfoPanel.add(this.la_DepName);
			this.pa_InfoPanel.add(this.tf_DepName);
		}
		
		this.pa_ButtonPanel = new JPanel();
		this.pa_ButtonPanel.setSize(WIDTH,40);
		
		this.bu_Find = new JButton("Find", new ImageIcon("find.gif"));
		this.bu_Find.setSize(20,20);
		this.bu_Find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doFind();
			}
		});
		
		this.bu_Add  = new JButton("Add", new ImageIcon("add.gif"));
		this.bu_Add.setSize(20,20);
		this.bu_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAdd();
			}
		});
		
		this.bu_Delete = new JButton("Delete", new ImageIcon("remove.gif"));
		this.bu_Delete.setSize(20,20);
		this.bu_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});
		this.bu_Update = new JButton("Update", new ImageIcon("update.gif"));
		this.bu_Update.setSize(20,20);
		this.bu_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUpdate();
			}
		});
		this.bu_Exit = new JButton("Exit", new ImageIcon("exit.gif"));
		this.bu_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doExit();
			}
		});
		this.bu_Reset = new JButton("Reset", new ImageIcon("reset.gif"));
		this.bu_Reset.setSize(20,20);
		this.bu_Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doReset();
			}
		});
		
		this.pa_ButtonPanel.setLayout(new BoxLayout(this.pa_ButtonPanel, 
													BoxLayout.X_AXIS));
		this.pa_ButtonPanel.add(this.bu_Find);
		this.pa_ButtonPanel.add(this.bu_Add);
		this.pa_ButtonPanel.add(this.bu_Delete);
		this.pa_ButtonPanel.add(this.bu_Update);		
		this.pa_ButtonPanel.add(this.bu_Reset);
		this.pa_ButtonPanel.add(this.bu_Exit);
		
			
		cp.setLayout(new BorderLayout());
		cp.add(this.pa_InfoPanel, "Center");
		cp.add(this.pa_ButtonPanel, "South");
		
		this.setVisible(true);			
	}
	

	/**
	 * Method 	- doFind()
	 * @param 	- void
	 * @return 	- void
	 * Purpose  - Perform a "Find" operation
	 */	
	public void doFind() {
		if(getTitle().indexOf("Employee") != -1)
		{
			DBClient dbc = new DBClient();
			Employee emp = dbc.findEmployee(this.tf_empID.getText());

			JOptionPane.showMessageDialog(null, dbc.getMessage(), "Message", JOptionPane.PLAIN_MESSAGE);
			this.tf_empID.setText(String.valueOf(emp.getEmployeeId()));
			this.tf_empLN.setText(emp.getLastName());
			this.tf_empFN.setText(emp.getFirstName());
			this.tf_DepNo.setText(String.valueOf(emp.getDepartmentId()));
		}else// User is querying department information
		{
			DBClient dbc = new DBClient();
			Department dep = dbc.findDepartment(this.tf_DepNo.getText());

			JOptionPane.showMessageDialog(null, dbc.getMessage(), "Message", JOptionPane.PLAIN_MESSAGE);
			this.tf_DepNo.setText(String.valueOf(dep.getDepartmentId()));
			this.tf_DepName.setText(dep.getDepartmentName());
		}
	}	
	
	/**
	 * Method 	- doAdd()
	 * @param 	- void
	 * @return 	- void
	 * Purpose  - Perform an "Add" operation
	 **/	
	public void doAdd() {
		if(getTitle().indexOf("Employee") != -1)
		{
			DBClient dbc = new DBClient();
			int eID 		= Integer.parseInt(this.tf_empID.getText().trim());;
			String lName 	= this.tf_empLN.getText();
			String fName 	= this.tf_empFN.getText();
			int depNum 		= Integer.parseInt(this.tf_DepNo.getText().trim());
			
			Employee emp = new Employee(fName, lName, eID, depNum);
			dbc.add(emp);

			JOptionPane.showMessageDialog(null, dbc.getMessage(), "Message", JOptionPane.PLAIN_MESSAGE);
		}else// User is querying department information
		{
			DBClient dbc = new DBClient();
			int DepNum = Integer.parseInt(this.tf_DepNo.getText().trim());
			String DepName = this.tf_DepName.getText();
			Department dep = new Department(DepNum, DepName);

			dbc.add(dep);

			JOptionPane.showMessageDialog(null, dbc.getMessage(), "Message", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	/**
	 * Method 	- doUpdate()
	 * @param 	- void
	 * @return 	- void
	 * Purpose  - Perform an "Update" operation
	 */
	public void doUpdate() {
		if(getTitle().indexOf("Employee") != -1)
		{
			DBClient dbc = new DBClient();
			int eID 		= Integer.parseInt(this.tf_empID.getText().trim());
			String lName 	= this.tf_empLN.getText();
			String fName 	= this.tf_empFN.getText();
			int depNum 	= Integer.parseInt(this.tf_DepNo.getText().trim());
			
			Employee emp = new Employee(fName, lName, eID, depNum);
			dbc.update(emp);

			JOptionPane.showMessageDialog(null, dbc.getMessage(), "Message", JOptionPane.PLAIN_MESSAGE);
		}else// User is querying department information
		{
			DBClient dbc = new DBClient();
			int DepNum = Integer.parseInt(this.tf_DepNo.getText().trim());
			String DepName = this.tf_DepName.getText();
			Department dep = new Department(DepNum, DepName);
			
			dbc.update(dep);

			JOptionPane.showMessageDialog(null, dbc.getMessage(), "Message", JOptionPane.PLAIN_MESSAGE);
		}
	}


	/**
	 * Method 	- doDelete()
	 * @param 	- void
	 * @return 	- void
	 * Purpose  - Perform a "Delete" operation
	 */
	public void doDelete() {
		if(getTitle().indexOf("Employee") != -1)
					{
						DBClient dbc = new DBClient();
						dbc.deleteEmployee(this.tf_empID.getText());

						JOptionPane.showMessageDialog(null, dbc.getMessage(), "Message", JOptionPane.PLAIN_MESSAGE);
					}else// User is querying department information
					{
						DBClient dbc = new DBClient();
						dbc.deleteDepartment(this.tf_DepNo.getText());

						JOptionPane.showMessageDialog(null, dbc.getMessage(), "Message", JOptionPane.PLAIN_MESSAGE);
					}
	}
	
	/**
	 * Method 	- doReset()
	 * @param 	- void
	 * @return 	- void
	 * Purpose  - Perform a "Reset" operation - empty all textfields
	 */
	public void doReset() {
		if (this.getTitle().indexOf("Employee") != -1) {		
			this.tf_empLN.setText("");
			this.tf_empFN.setText("");
			this.tf_empID.setText("");
			this.tf_DepNo.setText("");
			
		}
		else {
			this.tf_DepName.setText("");
			this.tf_DepNo.setText("");
		}
		
	}	
	/**
	 * Method 	- doExit()
	 * @param 	- void
	 * @return 	- void
	 * Purpose  - Perform an "Exit" operation, disposes the active frame
	 */
	public void doExit() {
		this.dispose();
	}

	
}