/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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
package org.jboss.test.deployment.jbpapp6517;

/**
 * @author bmaxwell
 *
 */
public class CheckCompleteEarTestCase extends CheckCompleteTestCase
{     

   public CheckCompleteEarTestCase(String name)
   {
      super(name);
   }

   // this will check complete on just ear deployments
   public void testCheckSubDeploymentCompleteDeployerEAROnly()
   {
      // deploy the CheckSubDeploymentCompleteDeployer that only acts on ears
      deployFailOnException(checkCompleteDeployerEAR);

      // deploy ear & eat expected exception
      // check jmx or jndi - with CheckSubDeploymentCompleteDeployer the EJB3 should not be in jmx or jndi
      // undeploy ear
      // call fail if not valid
      verifyCheckCompleteCalled(testEar, earEjb3JMXName);

      // same as above, except in the case of the sar, the ejb3 should still be deployed since the checkCompleteDeployerEAR should only check for .ear files 
      verifyCheckCompleteNotCalled(testSar, sarEjb3JMXName);

      // undeploy the CheckSubDeploymentCompleteDeployer that only acts on ears
      undeployLogException(checkCompleteDeployerEAR);
   }
   
}
