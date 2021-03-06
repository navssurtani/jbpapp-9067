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
package org.jboss.test.ws.jaxws.samples.serviceref;

import java.io.InputStream;
import java.net.URL;

import javax.naming.InitialContext;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;

import junit.framework.Test;

import org.jboss.wsf.test.JBossWSTest;
import org.jboss.wsf.test.JBossWSTestSetup;

/**
 * Test the JAXWS <service-ref>
 *
 * @author Thomas.Diesler@jboss.com
 * @author <a href="mailto:richard.opalka@jboss.org">Richard Opalka</a>
 */
public class ServiceRefClientTestCase extends JBossWSTest
{
   public final String TARGET_ENDPOINT_ADDRESS = "http://" + getServerHost() + ":8080/jaxws-samples-serviceref";

   public static Test suite()
   {
      String archives = "jaxws-samples-serviceref.war,jaxws-samples-serviceref-client.jar";
      return new JBossWSTestSetup(ServiceRefClientTestCase.class, archives);
   }

   public void testWSDLAccess() throws Exception
   {
      URL wsdlURL = new URL(TARGET_ENDPOINT_ADDRESS + "?wsdl");
      InputStream inputStream = wsdlURL.openStream();
      assertNotNull(inputStream);
      inputStream.close();
   }

   public void testDynamicProxy() throws Exception
   {
      URL wsdlURL = getResourceURL("META-INF/wsdl/Endpoint.wsdl");
      QName qname = new QName("http://serviceref.samples.jaxws.ws.test.jboss.org/", "EndpointService");
      Service service = Service.create(wsdlURL, qname);
      Endpoint port = (Endpoint)service.getPort(Endpoint.class);

      String request = "DynamicProxy";
      String response = port.echo(request);
      assertEquals(request, response);
   }

   public void testApplicationClient() throws Exception
   {
      InitialContext iniCtx = getInitialContext("jbossws-client");
      Service service = (Service) iniCtx.lookup("java:comp/env/service2");
      Endpoint port = service.getPort(Endpoint.class);
      assertNotNull(port);

      if(isIntegrationNative())
      {
         BindingProvider bp = (BindingProvider)port;
         boolean mtomEnabled = ((SOAPBinding)bp.getBinding()).isMTOMEnabled();
         assertTrue("MTOM should be enabled on port", mtomEnabled);
      }
      else
      {
         // MTOM property at service-ref level not possible with sun-ri         
      }

      String request = "ApplicationClient";
      String response = port.echo(request);
      assertEquals(response, request);

   }

}
