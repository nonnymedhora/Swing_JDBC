/*********************************************************************************
File Name :		HRProtocol.java
Description :		A transition unit within network.
Version :		v0.1
*********************************************************************************/

public class HRProtocol implements java.io.Serializable
{
	public HRProtocol(int code, Object o, String m) {
		passingCode = code;
		obj = o;
		msg = m;
	}

	/*
	** Method Name: getPassingCode
	** Method Desc: get passing code defined within HRDBProtocolQueryInterface
	*/
	public int getPassingCode() {
		return passingCode;
	}

	/*
	** Method Name: getPassingObject
	** Method Desc: get object tranferred.
	*/
	public Object getPassingObject() {
		return obj;
	}

	/*
	** Method Name: getPassingMessage
	** Method Desc: get additional message from either side of communication.
	*/
	public String getPassingMessage() {
		return msg;
	}

	private int passingCode;
	private Object obj;
	private String msg;
}