/*
 * JBoss, Home of Professional Open Source.
 * Copyright (c) 2012, Red Hat, Inc., and individual contributors
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
package org.jboss.resource.adapter.jdbc.jdbc4_1;

import org.jboss.resource.adapter.jdbc.WrappedStatement;
import org.jboss.resource.adapter.jdbc.jdbc4.WrappedResultSetJDBC4;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 */
public abstract class WrappedResultSetJDBC4_1 extends WrappedResultSetJDBC4
{
   protected WrappedResultSetJDBC4_1(WrappedStatement statement, ResultSet resultSet)
   {
      super(statement, resultSet);
   }

   @Override
   public <T> T getObject(int columnIndex, Class<T> type) throws SQLException
   {
      try
      {
         return getUnderlyingResultSet().getObject(columnIndex, type);
      }
      catch (Throwable t)
      {
         throw checkException(t);
      }
   }

   @Override
   public <T> T getObject(String columnLabel, Class<T> type) throws SQLException
   {
      try
      {
         return getUnderlyingResultSet().getObject(columnLabel, type);
      }
      catch (Throwable t)
      {
         throw checkException(t);
      }
   }
}
