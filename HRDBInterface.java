/*********************************************************************************
File Name :			HRDBInterface.java
Description :		Define common interface for HR Database Operation
*********************************************************************************/

public interface HRDBInterface
{
	/*
	** Method Name: findEmp
	** Method Desc: find employee information with employee id specified.
	*/
	
	Employee findEmployee(String id);

	/*
	** Method Name: findDept
	** Method Desc: find department information with department id specified.
	*/
	Department findDepartment(String num);

	/*
	** Method Name: addEmp
	** Method Desc: add employee information with information provided.
	*/
	void add(Employee emp);

	/*
	** Method Name: addDept
	** Method Desc: add department with department information provided.
	*/
	void add(Department dep);

	/*
	** Method Name: deleteEmp
	** Method Desc: delete employee information with employee id provided.
	*/
	void deleteEmployee(String empId);

	/*
	** Method Name: deleteDept
	** Method Desc: delete department with department id specified
	*/
	void deleteDepartment(String deptNum);

	/*
	** Method Name: updateEmp
	** Method Desc: update employee information with information provided
	*/
	void update(Employee emp);

	/*
	** Method Name: updateDept
	** Method Desc: update department information with information provided
	*/
	void update(Department dep);
}
