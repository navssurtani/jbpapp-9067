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
package org.jboss.tm.usertx.client;

import org.jboss.tm.TransactionPropagationContextUtil;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Reference;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

import javax.transaction.UserTransaction;

/**
 *  This is an object factory for producing client
 *  UserTransactions.
 *  usage for standalone clients.
 *      
 *  @author <a href="mailto:osh@sparre.dk">Ole Husgaard</a>
 *  @author <a href="mailto:galder.zamarreno@jboss.com">Galder Zamarreno</a>
 *  @version $Revision: 81039 $
 */
public class ClientUserTransactionObjectFactory
   implements ObjectFactory
{
   /**
    *  Get the <code>UserTransaction</code> this factory will return.
    *  This may return a cached value from a previous call.
    */
   static private UserTransaction getUserTransaction()
   {
      UserTransaction userTransaction = null;
      ServerVMClientUserTransaction serverUserTransaction = ServerVMClientUserTransaction.getSingleton();
      if (serverUserTransaction.isServer())
      {
         userTransaction = serverUserTransaction;
      }
      else
      {
         ClientUserTransaction cut = ClientUserTransaction.getSingleton();
         TransactionPropagationContextUtil.setTPCFactory(cut);
         userTransaction = cut;
      }
      return userTransaction;
   }

   public Object getObjectInstance(Object obj, Name name,
                                   Context nameCtx, Hashtable environment)
      throws Exception
   {
      Reference ref = (Reference)obj;
 
      if (!ref.getClassName().equals(ClientUserTransaction.class.getName()))
         return null;

      return getUserTransaction();
   }
}

