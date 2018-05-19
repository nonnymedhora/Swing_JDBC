/*********************************************************************************
File Name :			Empolyee.java
Description :		Encapsulate employee information.
*********************************************************************************/

public class Employee implements java.io.Serializable
{
	public Employee(String fName, String lName, int eID, int DNo) {
		this.fName = fName;
		this.lName = lName;
		this.empID = eID;
		this.depNum = DNo;
	}
	
	public int getEmployeeId()
	{
		return this.empID;
	}

	public void setEmployeeId(int id)
	{
		this.empID = id;
	}

	public String getFirstName()
	{
		return this.fName;
	}

	public void setFirstName(String fname)
	{
		this.fName = fname;
	}

	public String getLastName()
	{
		return this.lName;
	}

	public void setLastName(String lname)
	{
		this.lName = lname;
	}

	public int getDepartmentId()
	{
		return this.depNum;
	}

	public void setDepartmentId(int id)
	{
		this.depNum = id;
	}

	private int empID;
	private String lName;
	private String fName;
	private int depNum;	
}





