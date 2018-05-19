/*********************************************************************************
File Name :			DBServer.java
Description :		interface for receiving requests and sending responses
			in server side
*********************************************************************************/

import java.io.*;
import java.net.*;

public class DBServer implements HRProtocolInterface
{
	
	public static final int PORT = 9999;	// Listening port.
	
	public static void main(String[] args)
	{
		try
		{
			ServerSocket serverSock = new ServerSocket(PORT);
			while(true)
			{
				System.out.println("Awaiting Client connection.........");
				// Wait for client connection.
				ObjectSocket clientSock = new ObjectSocket(serverSock.accept());
				System.out.println("Connected to Client.....");
				// Create new request processing thread.
				Thread newServerThread = new Thread(new DBServerThread(clientSock));
				System.out.println("Processing Thread........");
				newServerThread.start();	// Runprocessing thread.
			}
		}catch(IOException ioe)
		{
			System.out.println("Server I/O error.");
			ioe.printStackTrace();
		}
	}

}
