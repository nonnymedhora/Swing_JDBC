/*********************************************************************************
File Name :			DBServerThread.java
Description :		implements run function for DBServer class
*********************************************************************************/

import java.io.*;
import java.net.*;

public class DBServerThread implements Runnable, HRProtocolInterface
{
	public DBServerThread(ObjectSocket s)
	{
		clientSock = s;
	}

	public void run()
	{
		HRProtocol envelop = null;
		Object obj = clientSock.receiveObject();
		if(obj instanceof HRProtocol)
		{
			envelop = (HRProtocol) obj;
			envelop = processEnvelop(envelop);										
			clientSock.sendObject(envelop);
		}else
		{
			// Error in transition.
		}
	}
	
	public void finalize()
	{
		clientSock.close();
		System.out.println("Socket finalized");
	}

	/*
	** Method Name: processEnvelop
	** Method Desc: Screen out the requesting code and take action for each function
	*/
	private HRProtocol processEnvelop(HRProtocol env)
	{
		int code = 0;
		Object obj = null;
		String msg = null;
		HRDatabase hrdb = new HRDatabase();
		HRProtocol newEnvelop = null;

		code = env.getPassingCode();
		obj = env.getPassingObject();
		switch(code)
		{
			case HRPROTOCOL_FIND_EMPLOYEE_REQUEST:
				Employee emp = hrdb.findEmployee( (String) obj);										
				newEnvelop = new HRProtocol(HRPROTOCOL_FIND_EMPLOYEE_RESPONSE, emp, hrdb.getMessage()); 
				break;
			case HRPROTOCOL_FIND_DEPARTMENT_REQUEST:
				Department dep = hrdb.findDepartment((String) obj);
				newEnvelop = new HRProtocol(HRPROTOCOL_FIND_DEPARTMENT_RESPONSE, dep, hrdb.getMessage());
				break;
			case HRPROTOCOL_ADD_EMPLOYEE_REQUEST:
				hrdb.add((Employee) obj);
				newEnvelop = new HRProtocol(HRPROTOCOL_ADD_EMPLOYEE_RESPONSE, null, hrdb.getMessage());
				break;
			case HRPROTOCOL_ADD_DEPARTMENT_REQUEST:
				hrdb.add((Department) obj);
				newEnvelop = new HRProtocol(HRPROTOCOL_ADD_DEPARTMENT_RESPONSE, null, hrdb.getMessage());
				break;
			case HRPROTOCOL_UPDATE_EMPLOYEE_REQUEST:
				hrdb.update((Employee) obj);
				newEnvelop = new HRProtocol(HRPROTOCOL_UPDATE_EMPLOYEE_RESPONSE, null, hrdb.getMessage());
				break;
			case HRPROTOCOL_UPDATE_DEPARTMENT_REQUEST:
				hrdb.update((Department) obj);
				newEnvelop = new HRProtocol(HRPROTOCOL_UPDATE_DEPARTMENT_RESPONSE, null, hrdb.getMessage());
				break;
			case HRPROTOCOL_DELETE_EMPLOYEE_REQUEST:
				hrdb.deleteEmployee((String) obj);
				newEnvelop = new HRProtocol(HRPROTOCOL_DELETE_EMPLOYEE_RESPONSE, null, hrdb.getMessage());
				break;
			case HRPROTOCOL_DELETE_DEPARTMENT_REQUEST:
				hrdb.deleteDepartment((String) obj);
				newEnvelop = new HRProtocol(HRPROTOCOL_DELETE_DEPARTMENT_RESPONSE, null, hrdb.getMessage());
				break;
			default:
				break;
		}
		
		return newEnvelop;
	}

	ObjectSocket clientSock = null;
}