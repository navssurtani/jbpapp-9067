package org.jboss.test.iiop.jbpapp6462.generated;

/**
 * Generated from IDL interface "TestServant".
 *
 * @author JacORB IDL compiler V 2.3.2 (JBoss patch 0), 23-Dec-2011
 * @version generated at Sep 11, 2012 11:48:24 AM
 */

public final class TestServantHolder	implements org.omg.CORBA.portable.Streamable{
	 public TestServant value;
	public TestServantHolder()
	{
	}
	public TestServantHolder (final TestServant initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return TestServantHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TestServantHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		TestServantHelper.write (_out,value);
	}
}
