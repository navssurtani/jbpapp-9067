/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.test.naming.test;

import java.util.Properties;

import org.jboss.test.JBossTestCase;
import org.jboss.test.NamingUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.jboss.test.JBossTestCase;

/** Simple unit tests for the jndi implementation. Note that there cannot
 * be any security related tests in this file as typically it is not run
 * with the right classpath resources for that.
 */
public class SimpleUnitTestCase extends JBossTestCase
{
   /**
    * Constructor for the SimpleUnitTestCase object
    *
    * @param name  Test name
    */
   public SimpleUnitTestCase(String name)
   {
      super(name);
   }

   /**
    * Tests that the second time you create a subcontext you get an exception.
    *
    * @exception Exception  Description of Exception
    */
   public void testCreateSubcontext() throws Exception
   {
      getLog().debug("+++ testCreateSubcontext");
      InitialContext ctx = NamingUtil.getFullInitialContext(getServerHost());
      ctx.createSubcontext("foo");
      try
      {
         ctx.createSubcontext("foo");
         fail("Second createSubcontext(foo) did NOT fail");
      }
      catch (NameAlreadyBoundException e)
      {
         getLog().debug("Second createSubcontext(foo) failed as expected");
      }
      ctx.createSubcontext("foo/bar");
      ctx.unbind("foo/bar");
      ctx.unbind("foo");
   }

   /** Lookup a name to test basic connectivity and lookup of a known name
    *
    * @throws Exception
    */
   public void testLookup() throws Exception
   {
      getLog().debug("+++ testLookup");
      InitialContext ctx = getInitialContext();
      Object obj = ctx.lookup("");
      getLog().debug("lookup('') = "+obj);
   }

   /** List the root context
    *
    * @throws Exception
    */
   public void testListing() throws Exception
   {
      log.debug("+++ testListing");
      InitialContext ctx = getInitialContext();
      NamingEnumeration names = ctx.list("");
      int count = 0;
      while( names.hasMore() )
      {
         NameClassPair ncp = (NameClassPair) names.next();
         log.info(ncp);
         count ++;
      }
      assertTrue("list count > 0 ", count > 0);
      ctx.close();
   }

   public void testNameChanges() throws Exception
   {
      getLog().debug("+++ testNameChanges");
      InitialContext ctx = NamingUtil.getFullInitialContext(getServerHost());
      Name name = ctx.getNameParser("").parse("jnp://" + getServerHost() + "/jmx");
      Name copy = (Name) name.clone();
      Object obj = ctx.lookup(name);
      getLog().debug("lookup("+name+") = "+obj);
      assertTrue("name.equals(copy), name="+name, name.equals(copy));
   }

   /** Lookup a name to test basic connectivity and lookup of a known name
    *
    * @throws Exception
    */
   public void testLookupFailures() throws Exception
   {
      getLog().debug("+++ testLookupFailures");
      // Look a name that does not exist
      Properties env = new Properties();
      InitialContext ctx = new InitialContext(env);
      try
      {
         Object obj = ctx.lookup("__bad_name__");
         fail("lookup(__bad_name__) should have thrown an exception, obj="+obj);
      }
      catch(NameNotFoundException e)
      {
         getLog().debug("lookup(__bad_name__) failed as expected", e);
      }

      // Do a lookup on a server port that does not exist
      env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
      env.setProperty(Context.PROVIDER_URL, "jnp://" + getServerHost() + ":65535/");
      env.setProperty("jnp.disableDiscovery", "true");
      getLog().debug("Creating InitialContext with env="+env);
      try
      {
         ctx = new InitialContext(env);
         Object obj = ctx.lookup("");
         fail("lookup('') should have thrown an exception, obj="+obj);
      }
      catch(NamingException e)
      {
         getLog().debug("lookup('') failed as expected", e);
      }
   }

   public void testHaInvoker() throws Exception
   {
      getLog().debug("+++ testHaInvoker");
      Properties env = new Properties();
      env.setProperty(Context.PROVIDER_URL, "jnp://" + getServerHost() + ":1100/");
      getLog().debug("Creating InitialContext with env="+env);
      InitialContext ctx = new InitialContext(env);
      getLog().debug("Created InitialContext");
      Object obj = ctx.lookup("jmx");
      getLog().debug("lookup(jmx) : "+obj);
      Context invokersCtx = (Context) obj;
      NamingEnumeration list = invokersCtx.list("");
      while( list.hasMore() )
      {
         Object entry = list.next();
         getLog().debug(" + "+entry);
      }
      ctx.close();
   }

   /**
    * Test the creation of HA-JNDI sub-context
    *
    * @throws Exception
    */
   public void testCreateHaJndiSubcontext() throws Exception
   {
      getLog().debug("+++ testCreateHaJndiSubcontext");
      // Look a name that does not exist
      InitialContext ctx = NamingUtil.getFullHAInitialContext(getServerHost());
      Object obj = ctx.lookup("");
      getLog().debug("lookup('') against HA-JNDI succeeded as expected, obj="+obj);

      ctx.createSubcontext("foo");
      try
      {
         ctx.createSubcontext("foo");
         fail("Second createSubcontext(foo) against HA-JNDI did NOT fail");
      }
      catch (javax.naming.NameAlreadyBoundException e)
      {
         getLog().debug("Second createSubcontext(foo) against HA-JNDI failed as expected");
      }

      getLog().debug("binding foo/bar");
      ctx.createSubcontext("foo/bar");

      getLog().debug("unbinding foo/bar");
      ctx.unbind("foo/bar");

      getLog().debug("unbinding foo");
      ctx.unbind("foo");

      try
      {
         obj = ctx.lookup("foo");
         fail("lookup(foo) should have thrown an exception");
      }
      catch(NameNotFoundException e)
      {
         getLog().debug("lookup(foo) failed as expected", e);
      }
   }
   public void testHttpInvoker() throws Exception
   {
      getLog().debug("+++ testHttpInvoker");
      Properties env = new Properties();
      env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.HttpNamingContextFactory");
      env.setProperty(Context.PROVIDER_URL, "http://" + getServerHost() + ":8080/invoker/JNDIFactory");
      getLog().debug("Creating InitialContext with env="+env);
      InitialContext ctx = new InitialContext(env);
      getLog().debug("Created InitialContext");
      Object obj = ctx.lookup("invokers");
      getLog().debug("lookup(invokers) : "+obj);
      Context invokersCtx = (Context) obj;
      NamingEnumeration list = invokersCtx.list("");
      while( list.hasMore() )
      {
         Object entry = list.next();
         getLog().debug(" + "+entry);
      }
      ctx.close();
   }

}
