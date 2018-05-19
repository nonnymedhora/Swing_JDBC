/*********************************************************************************
File Name :			DBConnection.java
Description :		A generic class for database connection.
*********************************************************************************/

import java.sql.*;

public class DBConnection
{
	/*****************************************************************************
	Constructor Name: DBConnection
	Parameters:
		driver: Full name of driver needed to registered with DriverManager.
		db_Url:	Full name of DBUrl for database connection.
		uName:	User Name for database connection.
		pswd:	Password for database connection.
	Return: N/A
	Depscription: Constructor for database connection.
	******************************************************************************/

	public DBConnection(String driver, String db_Url, Connection theCon)
	{
		driverName			=	driver;
		dbUrl				=	db_Url;
		con					=	theCon;
		
		loadDriver();
		connectToDb();
	}
	
	public DBConnection(String driver, String db_Url, Connection theCon, String uName, String passwd)
	{
		driverName			=	driver;
		dbUrl				=	db_Url;
		con					=	theCon;
		
		userName			= 	uName;
		password			=	passwd;
		
		loadDriver();
		connectToDb();
	}




	/*****************************************************************************
	Method Name: loadDriver
	Parameters: void
	Return: 		void
	Depscription: Register the driver provided to DriverManager.
	Exception:
		ClassNotFoundException
		InstantiationException
		IllegalAccessException
	******************************************************************************/	
	private void loadDriver()
	{
		if(driverName.indexOf("mysql") != -1)
		{
			try
			{
				// load the mysql driver
				Class.forName(driverName).newInstance();
			}catch (ClassNotFoundException e)
			{
				System.out.println("Unable to load Driver Class " + driverName);
				return;
			}catch (InstantiationException e)
			{
				System.out.println("Unable to Instantiation Driver Class");
				return;
			}catch (IllegalAccessException e)
			{
				System.out.println("Illegal access Driver Class");
				return;
			}
		}else
		{
			try
			{
				// load the driver other than mysql(not tested for other DB yet)
				Class.forName(driverName);
			}catch (ClassNotFoundException e)
			{
				System.out.println("Unable to load Driver Class " + driverName);
				return;
			}
		}

	}

	/*****************************************************************************
	Method Name: connectToDb
	Parameters: void
	Return: void
	Depscription: Connect to database using DBUrl, user name, and password
	Exceptions:
		SQLException
	******************************************************************************/	
	private void connectToDb()
	{
		try
		{
			// Connect to database, specifying particular database, username, and password
			con = DriverManager.getConnection(dbUrl, userName, password);
		}catch (SQLException se)
		{
			// Inform user of connection errors
			System.out.println("Cannot connect to database: " + dbUrl);
			System.out.println("SQL Exception: " + se.getMessage());
			se.printStackTrace(System.out);
		}
	}

	/*****************************************************************************
	Method Name: findRecords
	Parameters:
		String sqlStmt: SQL statement like, SELECT.
	Return: void
	Depscription:	Select records according to SQL statement supplied. Set "rs"
						to result send back from database.
	Exceptions:
		SQLException
	******************************************************************************/
	public ResultSet findRecords(String sqlStmt)
	{
		try
		{
			// Create and execute an SQL Statement
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlStmt);

		}catch (SQLException se)
		{
			// Inform user of any SQL errors
			System.out.println("Error in finding records.");
			System.out.println("SQL Exception: " + se.getMessage());
			se.printStackTrace(System.out);
		}
		
		return rs;
	}

	/*****************************************************************************
	Method Name: updateRecords
	Parameters:
		String sqlStmt: SQL statements like UPDATE, DELETE, INSERT.
	Return: void
	Depscription:	Update records according to SQL statement supplied. Set "result"
						to number of records executed.
	Exceptions:
		SQLException
	******************************************************************************/
	public int changeRecords(String sqlStmt)
	{
		int result = 0;
		try
		{
			// Create and execute an SQL Statement
			stmt = con.createStatement();
			result = stmt.executeUpdate(sqlStmt);

		}catch (SQLException se)
		{
			// Inform user of any SQL errors
			System.out.println("Error in updating records.");
			System.out.println("SQL Exception: " + se.getMessage());
			se.printStackTrace(System.out);
		}
		
		return result;
	}

	/*****************************************************************************
	Method Name: disConnectDb
	Parameters: void
	Return: void
	Depscription: Disconnect from database.
	Exceptions:
		SQLException
		NullPointerException
	******************************************************************************/
	public void closeConnection()
	{
		try
		{
			rs.close();
			stmt.close();
			con.close();
		}catch (SQLException se)
		{
			// Inform user of any SQL errors
			System.out.println("Error disconnecting from database.");
			System.out.println("SQL Exception: " + se.getMessage());
			se.printStackTrace(System.out);
		}catch(NullPointerException e)
		{
			e.printStackTrace(System.out);
		}
	}

	/******************************************************************************
	Field:
		private:
			driverName: Full name of driver needed to registered with DriverManager.
			dbUrl: Full name of DBUrl for database connection.
			userName: User Name for database connection.
			password: Password for database connection.
			con: a Connection object
			stmt: SQL statements
		public:
			rs: ResultSet object. Result send back from database.
			result: Number of records executed.
	******************************************************************************/
	private String driverName;
	private String dbUrl;
	private String userName;
	private String password;
	
	private Connection	con;
	private Statement	stmt;
	private ResultSet	rs;
}


