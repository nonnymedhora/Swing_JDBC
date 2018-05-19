/*********************************************************************************
File Name :			DBClient.java
Description :		Interface for sending requests and receiving responses in 
			client side
*********************************************************************************/

import java.io.*;
import java.net.*;

public class DBClient implements 	HRDBInterface, 
									HRProtocolInterface {
	private String msg;
	private final String SERVER = "localhost";
	public 	static final int PORT = 9999;	// Port through requests.
	private ObjectSocket clientSocket;


	/*
	* Method Name: sendEnvelop
	* Method Description: send HRProtocol object through ObjectSocket
	* @param - HRProtocol - the objejct to send
	* @return - void  
	*/
	private void sendEnvelop(HRProtocol env)
	{
		try
		{
			clientSocket = new ObjectSocket(SERVER, PORT);
			clientSocket.sendObject(env);	// Send to server
		}catch(UnknownHostException uhe)
		{
			uhe.printStackTrace();
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	/*
	* Method Name: receiveEnvelop
	* Method Description: receive HRProtocol object from ObjectSocket
	* @return - HRProtocol - an HRProtocol object recd from server
	*/
	private HRProtocol receiveEnvelop()
	{
		HRProtocol env = null;
		Object obj = clientSocket.receiveObject();	// Receive from server.
		if(obj instanceof HRProtocol)
		{
			env = (HRProtocol) obj;
			msg = env.getPassingMessage();
		}else
		{
			// Error in transition.
		}
		clientSocket.close();
		
		return env;
	}

	/*
	*  Method Name: findEmployee
	*  Method Description: find employee information with employee id specified.
	*  @param - (String) id - The id of the employee to look for
	*  @return - Employee - the Empployee object returned from the
	*/
	public Employee findEmployee(String id)
	{		
		HRProtocol envelop = null;
		envelop = new HRProtocol(HRPROTOCOL_FIND_EMPLOYEE_REQUEST, id, null);
		sendEnvelop(envelop);
		envelop = receiveEnvelop();
		if(envelop.getPassingCode() == HRPROTOCOL_FIND_EMPLOYEE_RESPONSE)
		{   
			int eid = ((Employee)envelop.getPassingObject()).getEmployeeId();
			String lastName = ((Employee)envelop.getPassingObject()).getLastName();
			String firstName = ((Employee)envelop.getPassingObject()).getFirstName();
			int DepNo = ((Employee)envelop.getPassingObject()).getDepartmentId();

			Employee emp = new Employee(firstName, lastName, eid, DepNo);
			return emp;
			
		}else
		{
			//Error in transition. or other error codes.
			return null;
		}
	}

	/*
	** Method Name: findDept
	** Method Desc: find department information with department id specified.
	*/
	public Department findDepartment(String num)
	{
		HRProtocol envelop = null;
		envelop = new HRProtocol(HRPROTOCOL_FIND_DEPARTMENT_REQUEST, num, null);
		sendEnvelop(envelop);
		envelop = receiveEnvelop();
		if(envelop.getPassingCode() == HRPROTOCOL_FIND_DEPARTMENT_RESPONSE)
		{
			int DepNum = ((Department)envelop.getPassingObject()).getDepartmentId();
			String DepName = ((Department)envelop.getPassingObject()).getDepartmentName();

			Department dep = new Department(DepNum, DepName);
			return dep;
			
		}else
		{
			//Error in transition. or other error codes.
			return null;
		}
	}

	/*
	** Method Name: addEmp
	** Method Desc: add employee information with information provided.
	*/
	public void add(Employee emp)
	{
		HRProtocol envelop = null;
		envelop = new HRProtocol(HRPROTOCOL_ADD_EMPLOYEE_REQUEST, emp, null);
		sendEnvelop(envelop);
		envelop = receiveEnvelop();
		if(envelop.getPassingCode() == HRPROTOCOL_ADD_EMPLOYEE_RESPONSE)
		{	//Success in transition
		}else
		{
			//Error in transition. or other error codes.
		}
	}

	/*
	** Method Name: addDept
	** Method Desc: add department with department information provided.
	*/
	public void add(Department dep)
	{
		HRProtocol envelop = null;
		envelop = new HRProtocol(HRPROTOCOL_ADD_DEPARTMENT_REQUEST, dep, null);
		sendEnvelop(envelop);
		envelop = receiveEnvelop();
		if(envelop.getPassingCode() == HRPROTOCOL_ADD_DEPARTMENT_RESPONSE)
		{	//Success in transition
		}else
		{
			//Error in transition. or other error codes.
		}
	}

	public void deleteEmployee(String empId)
	{
		HRProtocol envelop = null;
		envelop = new HRProtocol(HRPROTOCOL_DELETE_EMPLOYEE_REQUEST, empId, null);
		sendEnvelop(envelop);
		envelop = receiveEnvelop();
		if(envelop.getPassingCode() == HRPROTOCOL_DELETE_EMPLOYEE_RESPONSE)
		{	//Success in transition
		}else
		{
			//Error in transition. or other error codes.
		}
	}

	/*
	** Method Name: deleteEmp
	** Method Desc: delete employee information with employee id provided.
	*/
	public void deleteDepartment(String deptNum)
	{
		HRProtocol envelop = null;
		envelop = new HRProtocol(HRPROTOCOL_DELETE_DEPARTMENT_REQUEST, deptNum, null);
		sendEnvelop(envelop);
		envelop = receiveEnvelop();
		if(envelop.getPassingCode() == HRPROTOCOL_DELETE_DEPARTMENT_RESPONSE)
		{	//Success in transition
		}else
		{
			//Error in transition. or other error codes.
		}
	}

	/*
	** Method Name: updateEmp
	** Method Desc: update employee information with information provided
	*/
	public void update(Employee emp)
	{
		HRProtocol envelop = null;
		envelop = new HRProtocol(HRPROTOCOL_UPDATE_EMPLOYEE_REQUEST, emp, null);
		sendEnvelop(envelop);
		envelop = receiveEnvelop();
		if(envelop.getPassingCode() == HRPROTOCOL_UPDATE_EMPLOYEE_RESPONSE)
		{	//Success in transition
		}else
		{
			//Error in transition. or other error codes.
		}
	}

	/*
	** Method Name: updateDept
	** Method Desc: update department information with information provided
	*/
	public void update(Department dep)
	{
		HRProtocol envelop = null;
		envelop = new HRProtocol(HRPROTOCOL_UPDATE_DEPARTMENT_REQUEST, dep, null);
		sendEnvelop(envelop);
		envelop = receiveEnvelop();
		if(envelop.getPassingCode() == HRPROTOCOL_UPDATE_DEPARTMENT_RESPONSE)
		{	//Success in transition
		}else
		{
			//Error in transition. or other error codes.
		}
	}

	/*
	** Method Name: getMessage
	** Method Desc: get message within HRProtocol object
	*/
	public String getMessage()
	{
		return msg;
	}

}
