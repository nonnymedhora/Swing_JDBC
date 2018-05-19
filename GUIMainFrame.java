/********************************************************
* File Name :		GUIMainFrame.java
* Description :		Main GUI frame which launches HRFrame
*********************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JOptionPane;

public class GUIMainFrame {
	/* Constructor - GUIMainFrame
	 * Parameters - void
	 * Returns - n/a
	 * Description - Constructor GUIMainFrame is used for launching HRFrame
	 **/
	public GUIMainFrame() {
		HRFrame theHRFrame = new HRFrame();
	}
}

/***********************************************************
Class Name: HRFrame
Depscription: Class which handle GUI interface of theHRFrame
************************************************************/

class HRFrame extends JFrame {	
	 
	 /*  FIELDS
	  *  WIDTH and HEIGHT are constants to set the dimensions for the frame
	  *  bu_Employee 	- JButton to access employee records
	  *	 bu_Department 	- JButton to access department records
	  *  pa_mainPanel 	- JPanel to hold bu_Employee and bu_Department
	  *  bu_Exit 		- JButton to exit
	  *  pa_ExitPanel 	- JPanel to hold bu_Exit 
	  *  
	  */
	private static final int WIDTH = 300;
	private static final int HEIGHT = 400;
	
	private JButton 	bu_Employee;
	private JButton 	bu_Department;
	private JButton 	bu_Exit;
	private JPanel 		pa_mainPanel;
	private JPanel		pa_exitPanel;
	
	/* Constructor - HRFrame
	 * Parameters - void
	 * Returns - n/a
	 * Description - Constructor for launching HRFrame calls initUI()
	 **/
	
	public HRFrame() {
		this.initUI();
	}
	
	/** Method - iniUI()
	 *  @return - void
	 *  @param  - void
	 *  Description - to create GUI for HRFrame
	 *
	 */
	private void initUI() {
		Container cp ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle( "HR Database" );
	    this.setSize(WIDTH, HEIGHT);      
	    this.setResizable(false);
	    cp = this.getContentPane();
	    
	    this.pa_mainPanel = new JPanel();
	    
	    JLabel label = new JLabel("HR Database -- Main Window");
	    
	    this.bu_Employee = new JButton( "Employee information" );
	    this.bu_Employee.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		doEmployees();
	    	}
	    });
	    
	    this.bu_Department = new JButton ("Department Information" );
	    this.bu_Department.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		doDepartments();
	    	}
	    });
	    
	    this.pa_mainPanel.setLayout(new FlowLayout());
		this.pa_mainPanel.add(label);
	    this.pa_mainPanel.add(this.bu_Employee);
	    this.pa_mainPanel.add(this.bu_Department);
	    this.pa_mainPanel.setSize(WIDTH,HEIGHT-40);
	    this.pa_mainPanel.setVisible(true);
	    
	    this.pa_exitPanel = new JPanel();	    
	    
	    this.bu_Exit = new JButton( "EXIT" );	    
	    this.bu_Exit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		doExit(0);
	    	}
	    });
	    this.pa_exitPanel.setSize(WIDTH,40);
	    this.pa_exitPanel.add(this.bu_Exit);
	    
	    cp.setLayout( new BorderLayout());
	    
	    cp.add(this.pa_mainPanel, BorderLayout.CENTER);
	    cp.add(this.pa_exitPanel,    BorderLayout.SOUTH);
	   
	   	this.setVisible(true);    	    
	}
	
	
	/**
	 * Method - doEmployees
	 * @return -- void
	 * @param  -- void
	 * Purpose -- Launches QueryGUI to access employee info
	 */	
	public void doEmployees() {
		QueryGUI empGUI = new QueryGUI("Employee");
		empGUI.show();
	}
	
	
	/**
	 * Method - doDepartments
	 * @return -- void
	 * @param  -- void
	 * Purpose -- Launches QueryGUI to access department info
	 */	
	public void doDepartments() {
		QueryGUI depGUI = new QueryGUI("Department");
		depGUI.show();
	}
	
	
	/**
	 * Method - doExit
	 * @return -- void
	 * @param  -- void
	 * Purpose -- To exit from the application
	 */
	public void doExit (int status) {
		System.exit (status);		
	}
	
}