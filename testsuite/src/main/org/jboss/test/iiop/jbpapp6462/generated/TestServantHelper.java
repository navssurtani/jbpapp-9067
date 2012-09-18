package org.jboss.test.iiop.jbpapp6462.generated;


/**
 * Generated from IDL interface "TestServant".
 *
 * @author JacORB IDL compiler V 2.3.2 (JBoss patch 0), 23-Dec-2011
 * @version generated at Sep 11, 2012 11:48:24 AM
 */

public final class TestServantHelper
{
	public static void insert (final org.omg.CORBA.Any any, final org.jboss.test.iiop.jbpapp6462.generated.TestServant s)
	{
			any.insert_Object(s);
	}
	public static org.jboss.test.iiop.jbpapp6462.generated.TestServant extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:TestServant:1.0", "TestServant");
	}
	public static String id()
	{
		return "IDL:TestServant:1.0";
	}
	public static TestServant read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(org.jboss.test.iiop.jbpapp6462.generated._TestServantStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final org.jboss.test.iiop.jbpapp6462.generated.TestServant s)
	{
		_out.write_Object(s);
	}
	public static org.jboss.test.iiop.jbpapp6462.generated.TestServant narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jboss.test.iiop.jbpapp6462.generated.TestServant)
		{
			return (org.jboss.test.iiop.jbpapp6462.generated.TestServant)obj;
		}
		else if (obj._is_a("IDL:TestServant:1.0"))
		{
			org.jboss.test.iiop.jbpapp6462.generated._TestServantStub stub;
			stub = new org.jboss.test.iiop.jbpapp6462.generated._TestServantStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static org.jboss.test.iiop.jbpapp6462.generated.TestServant unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof org.jboss.test.iiop.jbpapp6462.generated.TestServant)
		{
			return (org.jboss.test.iiop.jbpapp6462.generated.TestServant)obj;
		}
		else
		{
			org.jboss.test.iiop.jbpapp6462.generated._TestServantStub stub;
			stub = new org.jboss.test.iiop.jbpapp6462.generated._TestServantStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
