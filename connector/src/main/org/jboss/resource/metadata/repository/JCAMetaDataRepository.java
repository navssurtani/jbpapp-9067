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
package org.jboss.resource.metadata.repository;

import javax.resource.spi.ActivationSpec;

import org.jboss.resource.deployment.AdminObject;
import org.jboss.resource.metadata.ConnectorMetaData;
import org.jboss.resource.metadata.mcf.ManagedConnectionFactoryDeploymentGroup;

/**
 * A JCAMetaDataRepository.
 * 
 * @author <a href="weston.price@jboss.org">Weston Price</a>
 * @version $Revision: 85945 $
 */
public interface JCAMetaDataRepository
{   
   public int getConnectorMetaDataCount();
   
   public int getActivationSpecCount();
   
   public int getManagedConnectionFactoryCount();
   
   public int getAdminObjectCount();
   
   public void setFormatterClassName(String formatterClassName);
   
   public String getFormatterClassName();
   
   public void addConnectorMetaData(String name, ConnectorMetaData cmd);
   
   public ConnectorMetaData getConnectorMetaData(String name);
   
   public void addActivationSpec(String rarName, ActivationSpec spec);
   
   public void addManagedConnectionFactoryDeploymentGroup(ManagedConnectionFactoryDeploymentGroup group);
   
   public void addAdminObject(String rarName, AdminObject adminObject);
   
   public Object listDeploymentsForConnector(String rarName);
     
   
}
