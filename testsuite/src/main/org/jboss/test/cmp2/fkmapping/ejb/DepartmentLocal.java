/*
 * Generated by XDoclet - Do not edit!
 */
package org.jboss.test.cmp2.fkmapping.ejb;

/**
 * Local interface for Department.
 */
public interface DepartmentLocal
   extends javax.ejb.EJBLocalObject
{

   public java.lang.String getDepartmentCode(  ) ;

   public java.lang.String getDepartmentCode2(  ) ;

   public java.lang.String getDescription(  ) ;

   public org.jboss.test.cmp2.fkmapping.ejb.InstituteLocal getInstitute(  ) ;

   public void setInstitute( org.jboss.test.cmp2.fkmapping.ejb.InstituteLocal institute ) ;

   public java.util.Collection getGroups(  ) ;

   public void setGroups( java.util.Collection groups ) ;

   public java.util.Collection getStudents(  ) ;

   public void setStudents( java.util.Collection students ) ;

}
