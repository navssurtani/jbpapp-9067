/*
 * Generated by XDoclet - Do not edit!
 */
package org.jboss.test.cmp2.fkmapping.ejb;

/**
 * CMP layer for Child.
 */
public abstract class ChildCMP
   extends org.jboss.test.cmp2.fkmapping.ejb.ChildCMPBean
   implements javax.ejb.EntityBean
{

   public void ejbLoad() 
   {
      super.ejbLoad();
   }

   public void ejbStore() 
   {
         super.ejbStore();
   }

   public void ejbActivate() 
   {
      super.ejbActivate();
   }

   public void ejbPassivate() 
   {
      super.ejbPassivate();

   }

   public void setEntityContext(javax.ejb.EntityContext ctx) 
   {
      super.setEntityContext(ctx);
   }

   public void unsetEntityContext() 
   {
      super.unsetEntityContext();
   }

   public void ejbRemove() throws javax.ejb.RemoveException
   {
      super.ejbRemove();

   }

   public abstract java.lang.Long getId() ;

   public abstract void setId( java.lang.Long id ) ;

   public abstract java.lang.String getFirstName() ;

   public abstract void setFirstName( java.lang.String firstName ) ;

   public abstract java.lang.Long getMotherId() ;

   public abstract void setMotherId( java.lang.Long motherId ) ;

   public abstract java.lang.String getMotherName() ;

   public abstract void setMotherName( java.lang.String motherName ) ;

}
