/*********************************************************************************
File Name :			Department.java
Description :		Encapsulate deepratment information.
*********************************************************************************/

public class Department implements java.io.Serializable
{
	
	public Department(int DNo, String DName) {
		this.depNum = DNo;
		this.depName = DName;
	}
	
	
	public int getDepartmentId()
	{
		return this.depNum;
	}

	public void setDepartmentId(int deptId)
	{
		this.depNum = deptId;
	}

	public String getDepartmentName()
	{
		return this.depName;
	}

	public void setDepartmentName(String deptName)
	{
		this.depName = deptName;
	}

	private int depNum;
	private String depName;
}
