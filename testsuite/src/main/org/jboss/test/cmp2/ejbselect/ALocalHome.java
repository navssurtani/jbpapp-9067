/*
 * Generated by XDoclet - Do not edit!
 */
package org.jboss.test.cmp2.ejbselect;

/**
 * Local home interface for A.
 */
public interface ALocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ALocal";
   public static final String JNDI_NAME="ALocal";

   public org.jboss.test.cmp2.ejbselect.ALocal create(java.lang.String id)
      throws javax.ejb.CreateException;

   public org.jboss.test.cmp2.ejbselect.ALocal findByPrimaryKey(java.lang.String pk)
      throws javax.ejb.FinderException;

   public java.util.Collection getSomeBs(org.jboss.test.cmp2.ejbselect.ALocal a) throws javax.ejb.FinderException;

   public java.util.Collection getSomeBsDeclaredSQL(org.jboss.test.cmp2.ejbselect.ALocal a) throws javax.ejb.FinderException;

   public void checkFinderForNull() throws javax.ejb.FinderException;

   public java.util.Collection selectDynamic(java.lang.String ql,java.lang.Object[] params) throws javax.ejb.FinderException;   
}
