package org.jboss.test.iiop.jbpapp6462.generated;


/**
 * Generated from IDL interface "TestServant".
 *
 * @author JacORB IDL compiler V 2.3.2 (JBoss patch 0), 23-Dec-2011
 * @version generated at Sep 11, 2012 11:48:24 AM
 */

public class _TestServantStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements org.jboss.test.iiop.jbpapp6462.generated.TestServant
{
	private String[] ids = {"IDL:TestServant:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = org.jboss.test.iiop.jbpapp6462.generated.TestServantOperations.class;
	public void testServantMethod()
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			org.omg.CORBA.portable.OutputStream _os = null;
			try
			{
				_os = _request( "testServantMethod", true);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
					try
					{
							_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
					throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
					throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "testServantMethod", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			TestServantOperations _localServant = (TestServantOperations)_so.servant;
			try
			{
				_localServant.testServantMethod();
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

}
