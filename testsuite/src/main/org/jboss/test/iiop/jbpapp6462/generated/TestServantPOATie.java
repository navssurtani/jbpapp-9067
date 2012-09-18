package org.jboss.test.iiop.jbpapp6462.generated;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "TestServant".
 *
 * @author JacORB IDL compiler V 2.3.2 (JBoss patch 0), 23-Dec-2011
 * @version generated at Sep 11, 2012 11:48:24 AM
 */

public class TestServantPOATie
	extends TestServantPOA
{
	private TestServantOperations _delegate;

	private POA _poa;
	public TestServantPOATie(TestServantOperations delegate)
	{
		_delegate = delegate;
	}
	public TestServantPOATie(TestServantOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public org.jboss.test.iiop.jbpapp6462.generated.TestServant _this()
	{
		return org.jboss.test.iiop.jbpapp6462.generated.TestServantHelper.narrow(_this_object());
	}
	public org.jboss.test.iiop.jbpapp6462.generated.TestServant _this(org.omg.CORBA.ORB orb)
	{
		return org.jboss.test.iiop.jbpapp6462.generated.TestServantHelper.narrow(_this_object(orb));
	}
	public TestServantOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(TestServantOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public void testServantMethod()
	{
_delegate.testServantMethod();
	}

}
