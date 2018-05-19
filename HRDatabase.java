/*********************************************************************************
File Name :			HRDatabase.java
Description :		Handle query to and from database.
*********************************************************************************/

import java.sql.*;

public class HRDatabase implements HRDBInterface
{
	private String msg;
	private Connection theConnection;
	private String driverName = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost/EMP";
	private String userName = "collegeportal";
	private String passwd = "tribalmysql8";



	/*
	** Method Name: findEmp
	** Method Desc: find employee information with employee id specified.
	*/
	public Employee findEmployee(String id)
	{								
		DBConnection dbCon = new DBConnection(driverName, dbUrl, theConnection, userName, passwd);

		try
		{
			String sqlStmt = "SELECT * FROM employee, department WHERE EmpID = " +
									id + " AND employee.DeptNo = department.DeptNo";

			ResultSet rs = dbCon.findRecords(sqlStmt);

			if(rs.next())
			{   
				int eID 		= rs.getInt("EmpID");
				String lName 	= rs.getString("LastName");
				String fName	= rs.getString("FirstName");
				int depNum		= rs.getInt("DeptNo");
				
				Employee emp = new Employee(fName, lName, eID, depNum);

				msg = "Successfully found record.";
				
//				dbCon.closeConnection();
				return emp;

			}else // Find no match
			{
				msg = "Cannot find match record for Employee#" + id;
				return null;
			}
		}catch(SQLException e)
		{
			e.printStackTrace(System.out);
		}

//		dbCon.closeConnection();
		return null;
	}

	/*
	** Method Name: findDept
	** Method Desc: find department information with department id specified.
	*/
	public Department findDepartment(String num)
	{
		DBConnection dbCon = new DBConnection(driverName, dbUrl, theConnection, userName, passwd);

		String sqlStmt = "SELECT * FROM department WHERE DeptNo = " + num;

		ResultSet rs = dbCon.findRecords(sqlStmt);

		try
		{
			if(rs.next())
			{
				int DepNum = rs.getInt("DeptNo");
				String DepName = rs.getString("DeptName");
				Department dep = new Department(DepNum, DepName);
				
				msg = "Successfully found record.";
//				dbCon.closeConnection();
				return dep;

			}else // Find no match
			{
				msg = "Cannot find match record for Department#" + num;//id
				return null;
			}
		}catch(SQLException e)
		{
			e.printStackTrace(System.out);
		}
		
//		dbCon.closeConnection();
		return null;
	}

	/*
	** Method Name: addEmp
	** Method Desc: add employee information with information provided.
	*/
	public void add(Employee emp)
	{
		DBConnection dbCon = new DBConnection(driverName, dbUrl, theConnection, userName, passwd);
		if(findEmployee(String.valueOf(emp.getEmployeeId())) == null)
		{
			if(findDepartment(String.valueOf(emp.getDepartmentId())) != null)
			{
				String sqlStmt="INSERT INTO employee (EmpID, LastName, FirstName, DeptNo)" +
								" VALUES(\"" + emp.getEmployeeId() +	"\", \"" +
												 emp.getLastName() + "\", \"" +
												 emp.getFirstName() + "\", " +
												 emp.getDepartmentId() + ")";

				int result = dbCon.changeRecords(sqlStmt);

				msg = "Successfully added " + result + " record.";

			}else
			{
				msg = "Department# " + emp.getDepartmentId() + " does not exist.";
			}
		}else // Duplicated record
		{
			msg = "Employee# " + emp.getEmployeeId() + " already existed.";
		}
	}

	/*
	** Method Name: addDept
	** Method Desc: add department with department information provided.
	*/
	public void add(Department dep)
	{
		DBConnection dbCon = new DBConnection(driverName, dbUrl, theConnection, userName, passwd);

		if(findDepartment(String.valueOf(dep.getDepartmentId())) == null)
		{
			String sqlStmt = "INSERT INTO department (DeptNo, DeptName) VALUES(" +
									dep.getDepartmentId() + ", \"" +
									dep.getDepartmentName() + "\")";

			int result = dbCon.changeRecords(sqlStmt);

			msg = "Successfully added " + result + " record.";
		}else // Duplicated record
		{
			msg = "Department# " + dep.getDepartmentId() + " already existed.";
		}
	}

	/*
	** Method Name: deleteEmp
	** Method Desc: delete employee information with employee id provided.
	*/
	public void deleteEmployee(String empId)
	{
		DBConnection dbCon = new DBConnection(driverName, dbUrl, theConnection, userName, passwd);

		if(findEmployee(empId) != null)
		{
			String sqlStmt = "DELETE FROM employee WHERE EmpID = \"" + empId + "\"";

			int result = dbCon.changeRecords(sqlStmt);

			msg = "Successfully deleted " + result + " record.";
		}else // Record does not exist
		{
			msg = "Employee# " + empId + " does not exist.";
		}
	}

	/*
	** Method Name: deleteDept
	** Method Desc: delete department with department id specified
	*/
	public void deleteDepartment(String deptNum)
	{
		DBConnection dbCon = new DBConnection(driverName, dbUrl, theConnection, userName, passwd);

		if(findDepartment(deptNum) != null)
		{
			String sqlStmt = "SELECT * FROM employee WHERE DeptNo = " + deptNum;
			ResultSet rs = dbCon.findRecords(sqlStmt);
			try
			{
				if(!rs.next())// No employee with deptNum
				{
					sqlStmt = "DELETE FROM department WHERE DeptNo = " + deptNum;

					int result = dbCon.changeRecords(sqlStmt);

					msg = "Successfully deleted " + result + " record.";
				}else// Still have employee(s) with deptNum 
				{
					msg = "There are still employee(s) with Department# " + deptNum;
				}
			}catch(SQLException e)
			{
				e.printStackTrace(System.out);
			}
		}else // Record does not exist
		{
			msg = "Department# " + deptNum + " does not exist.";
		}
	}

	/*
	** Method Name: updateEmp
	** Method Desc: update employee information with information provided
	*/
	public void update(Employee emp)
	{
		DBConnection dbCon = new DBConnection(driverName, dbUrl, theConnection, userName, passwd);

		if(findEmployee(String.valueOf(emp.getEmployeeId())) != null)
		{
			if(findDepartment(String.valueOf(emp.getDepartmentId())) != null)
			{
				String sqlStmt = "UPDATE employee SET LastName = \"" + emp.getLastName() +
													"\",  FirstName = \"" + emp.getFirstName() +
													"\",  DeptNo = " + emp.getDepartmentId() +
													" WHERE EmpID = \"" + emp.getEmployeeId() + "\"";

				int result = dbCon.changeRecords(sqlStmt);

				msg = "Successfully updated " + result + " record.";
			}else
			{
				msg = "Department# " + emp.getDepartmentId() + " does not exist.";
			}
		}else // Employee record does not exist
		{
			msg = "Employee# " + emp.getEmployeeId() + " does not exist.";
		}

	}

	/*
	** Method Name: updateDept
	** Method Desc: update department information with information provided
	*/
	public void update(Department dep)
	{
		DBConnection dbCon = new DBConnection(driverName, dbUrl, theConnection, userName, passwd);

		if(findDepartment(String.valueOf(dep.getDepartmentId())) != null)
		{
			String sqlStmt = "UPDATE department SET DeptName = \"" +
												dep.getDepartmentName() +
												"\" WHERE DeptNo = " + dep.getDepartmentId();

			int result = dbCon.changeRecords(sqlStmt);

			msg = "Successfully updated " + result + " record.";
		}else // Record deos not exist
		{
			msg = "Department# " + dep.getDepartmentId() + " does not exist.";
		}
	}
	
	public String getMessage()
	{
		return msg;
	}

}
