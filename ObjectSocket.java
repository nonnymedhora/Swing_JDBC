/*********************************************************************************
File Name :			ObjectSocket.java
Description :		Wrapper class for scoket creation
*********************************************************************************/

import java.io.*;
import java.net.*;

/*
** Class Name: ObjectSocket
** Class Desc: A class which wrap up the socket and I/O managemnet.
*/
public class ObjectSocket
{
	public ObjectSocket(String host, int port)
		throws UnknownHostException, IOException
	{
		this(new Socket(host, port));
	}
	
	public ObjectSocket(Socket s)
	{
		aSocket = s;
	}

	/*
	** Method Name: receiveObject
	** Method Desc: Receive "Object" object from socket.
	*/
	public Object receiveObject()
	{
		Object obj = null;
		try
		{
			objIn = new ObjectInputStream(aSocket.getInputStream());
			obj = objIn.readObject();

		}catch(IOException ioe)
		{
			System.out.println("ObjectSocket Read Error :");
			ioe.printStackTrace(System.out);
		}catch(ClassNotFoundException e)
		{
			System.out.println("ObjectSocket Read Error :");
			e.printStackTrace();
		}
		return obj;
	}

	/*
	** Method Name: sendObject
	** Method Desc:Send an "Object" object to socket.
	*/
	public void sendObject(Object obj)
	{
		try
		{
			objOut = new ObjectOutputStream(aSocket.getOutputStream());
			objOut.writeObject(obj);
			objOut.flush();
		}catch(IOException ioe)
		{
			System.out.println("ObjectSocket Read Error :");
			ioe.printStackTrace(System.out);
		}
	}
	
	/*
	** Method Name: Close
	** Method Desc:Close socket connection.
	*/
	public void close()
	{
		try
		{
			aSocket.close();
		}catch(IOException ioe)
		{
			System.out.println("ObjectSocket Close Error :");
			ioe.printStackTrace(System.out);
		}

	}

	ObjectOutputStream objOut;	// Write object with ObjectOutputStream
	ObjectInputStream objIn;	// Read object using ObjectInputStream
	Socket aSocket;
}