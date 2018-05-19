/*********************************************************************************
File Name :			HRProtocolInterface.java
Description :		Define protocol code for protocol usage
*********************************************************************************/

public interface HRProtocolInterface
{
	// Find operations
	final int HRPROTOCOL_FIND_EMPLOYEE_REQUEST = 101;
	final int HRPROTOCOL_FIND_EMPLOYEE_RESPONSE = 102;
	final int HRPROTOCOL_FIND_DEPARTMENT_REQUEST = 103;
	final int HRPROTOCOL_FIND_DEPARTMENT_RESPONSE = 114;

	// Add operations
	final int HRPROTOCOL_ADD_EMPLOYEE_REQUEST = 105;
	final int HRPROTOCOL_ADD_EMPLOYEE_RESPONSE = 106;
	final int HRPROTOCOL_ADD_DEPARTMENT_REQUEST = 107;
	final int HRPROTOCOL_ADD_DEPARTMENT_RESPONSE = 108;

	// Update operations
	final int HRPROTOCOL_UPDATE_EMPLOYEE_REQUEST = 109;
	final int HRPROTOCOL_UPDATE_EMPLOYEE_RESPONSE = 110;
	final int HRPROTOCOL_UPDATE_DEPARTMENT_REQUEST = 111;
	final int HRPROTOCOL_UPDATE_DEPARTMENT_RESPONSE = 112;

	// Delete operations
	final int HRPROTOCOL_DELETE_EMPLOYEE_REQUEST = 113;
	final int HRPROTOCOL_DELETE_EMPLOYEE_RESPONSE = 114;
	final int HRPROTOCOL_DELETE_DEPARTMENT_REQUEST = 115;
	final int HRPROTOCOL_DELETE_DEPARTMENT_RESPONSE = 116;
}
