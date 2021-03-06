/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.test.aop.test;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.jboss.test.JBossTestCase;

/**
 * Sample client for the jboss container.
 *
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Id: JMXUnitTestCase.java 81036 2008-11-14 13:36:39Z dimitris@jboss.org $
 */

public class JMXUnitTestCase
        extends JBossTestCase
{
   org.jboss.logging.Logger log = getLog();

   static boolean deployed = false;
   static int test = 0;

   public JMXUnitTestCase(String name)
   {
      super(name);
   }

   public void testIntrospected() throws Exception
   {
      MBeanServerConnection server = getServer();
      ObjectName testerName = new ObjectName("jboss.aop:name=JMXIntrospectedTester");
      assertEquals("hello", server.getAttribute(testerName, "Value"));
      server.setAttribute(testerName, new Attribute("Value", "world"));
      assertEquals("world", server.getAttribute(testerName, "Value"));
      Boolean rtn = (Boolean) server.getAttribute(testerName, "Flag");
      assertFalse(rtn.booleanValue());
      server.setAttribute(testerName, new Attribute("Flag", Boolean.TRUE));
      rtn = (Boolean) server.getAttribute(testerName, "Flag");
      assertTrue(rtn.booleanValue());

      Object[] params = {};
      String[] sig = {};
      server.invoke(testerName, "reset", params, sig);
      assertEquals("hello", server.getAttribute(testerName, "Value"));
      rtn = (Boolean) server.getAttribute(testerName, "Flag");
      assertFalse(rtn.booleanValue());

      Object[] params2 = {new Integer(1), new Float(1.1), new Float(5.5)};
      String[] sig2 = {"int", "float", "java.lang.Float"};
      String val = (String) server.invoke(testerName, "params", params2, sig2);


   }

   public static Test suite() throws Exception
   {
      TestSuite suite = new TestSuite();
      suite.addTest(new TestSuite(JMXUnitTestCase.class));

      AOPTestSetup setup = new AOPTestSetup(suite, "jmx-aoptest.sar");
      return setup;
   }

}
