/*
 * Generated by XDoclet - Do not edit!
 */
package org.jboss.test.cmp2.fkmapping.ejb;

/**
 * Remote interface for Manager.
 */
public interface Manager
   extends javax.ejb.EJBObject
{

   public void testStandaloneFKMapping(  )
      throws java.rmi.RemoteException;

   public void testCompleteFKToPKMapping(  )
      throws java.rmi.RemoteException;

   public void testPartialFKToPKMapping(  )
      throws java.rmi.RemoteException;

   public void testFKToCMPMapping(  )
      throws java.rmi.RemoteException;

   public void createParent( java.lang.Long id,java.lang.String firstName )
      throws java.lang.Exception, java.rmi.RemoteException;

   public void createChild( java.lang.Long id,java.lang.String firstName )
      throws java.lang.Exception, java.rmi.RemoteException;

   public void createChild( java.lang.Long id,java.lang.String firstName,java.lang.Long parentId,java.lang.String parentName )
      throws java.lang.Exception, java.rmi.RemoteException;

   public void assertChildHasMother( java.lang.Long childId,java.lang.Long parentId,java.lang.String parentName )
      throws java.lang.Exception, java.rmi.RemoteException;

   public java.lang.Object createChildUPKWithMother(  )
      throws java.lang.Exception, java.rmi.RemoteException;

   public void loadChildUPKWithMother( java.lang.Object pk )
      throws java.lang.Exception, java.rmi.RemoteException;

   public java.lang.Object createChildUPKWithFather(  )
      throws java.lang.Exception, java.rmi.RemoteException;

   public void loadChildUPKWithFather( java.lang.Object pk )
      throws java.lang.Exception, java.rmi.RemoteException;

}