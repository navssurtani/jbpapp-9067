/*
 * Generated by XDoclet - Do not edit!
 */
package org.jboss.test.cmp2.cmr.interfaces;

/**
 * Local interface for CMRBugManagerEJB.
 */
public interface CMRBugManagerEJBLocal
   extends javax.ejb.EJBLocalObject
{

   public void setupLoadFKState(  ) throws java.lang.Exception;

   public void moveLastNodeBack(  ) throws java.lang.Exception;

   public boolean lastHasNextNode(  ) throws java.lang.Exception;

   public void tearDownLoadFKState(  ) throws java.lang.Exception;

}