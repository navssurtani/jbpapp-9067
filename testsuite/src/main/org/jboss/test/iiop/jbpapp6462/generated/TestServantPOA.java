package org.jboss.test.iiop.jbpapp6462.generated;


/**
 * Generated from IDL interface "TestServant".
 *
 * @author JacORB IDL compiler V 2.3.2 (JBoss patch 0), 23-Dec-2011
 * @version generated at Sep 11, 2012 11:48:24 AM
 */

public abstract class TestServantPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, org.jboss.test.iiop.jbpapp6462.generated.TestServantOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "testServantMethod", new java.lang.Integer(0));
	}
	private String[] ids = {"IDL:TestServant:1.0"};
	public org.jboss.test.iiop.jbpapp6462.generated.TestServant _this()
	{
		return org.jboss.test.iiop.jbpapp6462.generated.TestServantHelper.narrow(_this_object());
	}
	public org.jboss.test.iiop.jbpapp6462.generated.TestServant _this(org.omg.CORBA.ORB orb)
	{
		return org.jboss.test.iiop.jbpapp6462.generated.TestServantHelper.narrow(_this_object(orb));
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // testServantMethod
			{
				_out = handler.createReply();
				testServantMethod();
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
