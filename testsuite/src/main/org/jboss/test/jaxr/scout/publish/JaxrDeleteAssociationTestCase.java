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
package org.jboss.test.jaxr.scout.publish;
 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.registry.BulkResponse;
import javax.xml.registry.BusinessLifeCycleManager;
import javax.xml.registry.BusinessQueryManager;
import javax.xml.registry.Connection;
import javax.xml.registry.JAXRException;
import javax.xml.registry.LifeCycleManager;
import javax.xml.registry.RegistryService;
import javax.xml.registry.infomodel.Association;
import javax.xml.registry.infomodel.Concept;
import javax.xml.registry.infomodel.Key;
import javax.xml.registry.infomodel.Organization;

import org.jboss.logging.Logger;
import org.jboss.test.jaxr.scout.JaxrBaseTestCase;
import org.jboss.test.jaxr.scout.util.ScoutUtil;

/**
 * Tests Save and Delete of Associations
 *
 * @author <mailto:Anil.Saldhana@jboss.org>Anil Saldhana
 * @since Mar 9, 2005
 */
public class JaxrDeleteAssociationTestCase extends JaxrBaseTestCase
{
   private static Logger log = Logger.getLogger(JaxrDeleteAssociationTestCase.class);
   
    /**
     * Usecase:
     * 1. Authenticate two users
     * 2. First user creates an org (Source org)
     * 3. Second user creates an org (Target org)
     * 4. First user creates an association using the target org
     * 5. Both the users confirm the association
     * 6. Second user saves the association
     * 7. First user deletes the association
     * 8. When the first user asks the registry for associations,
     * need to get back empty!
     *
     * @throws JAXRException
     */
    public void testDeleteAssociations() throws JAXRException
    {
        Key savekey = null;
        Key assockey = null;
        BusinessQueryManager bqm2 = null;
        BusinessLifeCycleManager blm2 = null;
        Collection sourceKeys = null;
        Collection targetKeys = null;


        try
        {
            login();
            getJAXREssentials();
            // second user.
            Connection con2 = loginSecondUser();
            RegistryService rs2 = con2.getRegistryService();
            blm2 = rs2.getBusinessLifeCycleManager();
            bqm2 = rs2.getBusinessQueryManager();
            String orgTarget = "Target Organization";
            String orgSource = "Source Organization";

            Organization target = blm2.createOrganization(blm.createInternationalString(orgTarget));
            Organization source = blm.createOrganization(blm.createInternationalString(orgSource));

            Collection orgs = new ArrayList();
            orgs.add(source);
            br = blm.saveOrganizations(orgs);
            if (br.getExceptions() != null)
            {
                fail(" Source::Save Organizations failed");
            }
            log.debug("Saved Source Organization");
            
            sourceKeys = br.getCollection();
            Iterator iter = sourceKeys.iterator();
            while (iter.hasNext())
            {
                savekey = (Key) iter.next();
            }
            String sourceid = savekey.getId();
            String objectType = LifeCycleManager.ORGANIZATION;

            Organization pubSource = (Organization) bqm.getRegistryObject(sourceid, objectType);
            assertNotNull("Source Org", pubSource.getName().getValue());

            orgs.clear();
            orgs.add(target);
            br = blm2.saveOrganizations(orgs);
            if (br.getExceptions() != null)
            {
                fail("Target:: Save Organizations failed");
            }
            targetKeys = br.getCollection();
            iter = targetKeys.iterator();
            while (iter.hasNext())
            {
                savekey = (Key) iter.next();
            }
            String targetid = savekey.getId();
            Organization targetOrg = (Organization) bqm2.getRegistryObject(targetid, objectType);
            assertNotNull("Target Org", targetOrg.getName().getValue());

            Concept associationType = getAssociationConcept("Implements");
            assertNotNull("AssociationType", associationType);

            Association a = blm.createAssociation(targetOrg, associationType);
            a.setSourceObject(pubSource);
            a.setTargetObject(targetOrg);

            blm.confirmAssociation(a);
            blm2.confirmAssociation(a);

            log.debug("Confirmed the association");
            
            // publish the Association
            Collection associations = new ArrayList();
            associations.add(a);
            br = blm2.saveAssociations(associations, false);

            if (br.getExceptions() != null)
            {
                fail("Second User :save Association failed");
            }

            log.debug("Second User: saved the association");
            
            br = bqm.findCallerAssociations(null,
                    new Boolean(true),
                    new Boolean(true),
                    null);

            if (br.getExceptions() == null)
            {
                Collection results = br.getCollection();
                assertTrue("Result is 1",results.size() == 1);
                if (results.size() > 0)
                {
                    iter = results.iterator();
                    while (iter.hasNext())
                    {
                        Association a1 = (Association) iter.next();
                        assockey = a1.getKey();
                        ScoutUtil.validateAssociation(a1, orgSource);
                    }
                }
            }
            if (assockey != null)
            {
                Collection keys = new ArrayList();
                keys.add(assockey);
                br = blm.deleteAssociations(keys);
                
                if(br.getExceptions() != null)
                   fail("Deletion of Associations failed");

                System.out.println("JBAS-7129 needs to be fixed");
                
                
                BulkResponse brq = bqm.findCallerAssociations(null, Boolean.TRUE, Boolean.TRUE, null);
                if (brq.getExceptions() == null)
                {
                    Collection retAssocs = brq.getCollection();
                    if (retAssocs.size() == 0)
                    {
                        //Pass
                    } else
                    {
                       Iterator iterAss = retAssocs.iterator();
                       while(iterAss.hasNext())
                       {
                          Association assc = (Association) iterAss.next();
                          if(assc.getKey().getId().equals(assockey.getId()))
                             fail("Deleted Association found");
                       }
                    } 
                }
               
            }

        } catch (Exception e)
        {
           log.error("Exception::",e); 
           fail(" failed with :" + e.getMessage());
        } finally
        {
            // Clean up
            try
            {
                blm2.deleteOrganizations(targetKeys);
                blm.deleteOrganizations(sourceKeys);
            } catch (JAXRException je)
            {
                System.out.println("Error: Clean Up Failed");
            }
        }
    }
}
