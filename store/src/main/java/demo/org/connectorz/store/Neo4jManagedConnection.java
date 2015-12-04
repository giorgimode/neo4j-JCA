/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2013, Red Hat Inc, and individual contributors
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
package demo.org.connectorz.store;

import demo.org.connectorz.api.Neo4jConnection;

import javax.resource.ResourceException;
import javax.resource.spi.*;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Neo4jManagedConnection
 *
 * @version $Revision: $
 */
public class Neo4jManagedConnection implements ManagedConnection
{

   /** The logger */
   private static Logger log = Logger.getLogger(Neo4jManagedConnection.class.getName());

   /** The logwriter */
   private PrintWriter logwriter;

   /** ManagedConnectionFactory */
   private Neo4jManagedConnectionFactory mcf;

   /** Listeners */
   private List<ConnectionEventListener> listeners;

   /** Connection */
   private Neo4jConnectionImpl connection;

   /**
    * Default constructor
    * @param mcf mcf
    */
   public Neo4jManagedConnection(Neo4jManagedConnectionFactory mcf)
   {
      this.mcf = mcf;
      this.logwriter = new PrintWriter(System.out);
      this.listeners = Collections.synchronizedList(new ArrayList<ConnectionEventListener>(1));
      this.connection = null;
   }

   /**
    * Creates a new connection handle for the underlying physical connection 
    * represented by the ManagedConnection instance. 
    *
    * @param subject Security context as JAAS subject
    * @param cxRequestInfo ConnectionRequestInfo instance
    * @return generic Object instance representing the connection handle. 
    * @throws ResourceException generic exception if operation fails
    */
   public Object getConnection(Subject subject,
      ConnectionRequestInfo cxRequestInfo) throws ResourceException
   {
      log.finest("getConnection()");
      connection = new Neo4jConnectionImpl(this, mcf);
      return connection;
   }

   /**
    * Used by the container to change the association of an 
    * application-level connection handle with a ManagedConneciton instance.
    *
    * @param connection Application-level connection handle
    * @throws ResourceException generic exception if operation fails
    */
   public void associateConnection(Object connection) throws ResourceException
   {
      log.finest("associateConnection()");

      if (connection == null)
         throw new ResourceException("Null connection handle");

      if (!(connection instanceof Neo4jConnectionImpl))
         throw new ResourceException("Wrong connection handle");

      this.connection = (Neo4jConnectionImpl)connection;
   }

   /**
    * Application server calls this method to force any cleanup on the ManagedConnection instance.
    *
    * @throws ResourceException generic exception if operation fails
    */
   public void cleanup() throws ResourceException
   {
      log.finest("cleanup()");

   }

   /**
    * Destroys the physical connection to the underlying resource manager.
    *
    * @throws ResourceException generic exception if operation fails
    */
   public void destroy() throws ResourceException
   {
      log.finest("destroy()");

   }

   /**
    * Adds a connection event listener to the ManagedConnection instance.
    *
    * @param listener A new ConnectionEventListener to be registered
    */
   public void addConnectionEventListener(ConnectionEventListener listener)
   {
      log.finest("addConnectionEventListener()");
      if (listener == null)
         throw new IllegalArgumentException("Listener is null");
      listeners.add(listener);
   }

   /**
    * Removes an already registered connection event listener from the ManagedConnection instance.
    *
    * @param listener already registered connection event listener to be removed
    */
   public void removeConnectionEventListener(ConnectionEventListener listener)
   {
      log.finest("removeConnectionEventListener()");
      if (listener == null)
         throw new IllegalArgumentException("Listener is null");
      listeners.remove(listener);
   }

   /**
    * Close handle
    *
    * @param handle The handle
    */
   void closeHandle(Neo4jConnection handle)
   {
      ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
      event.setConnectionHandle(handle);
      for (ConnectionEventListener cel : listeners)
      {
         cel.connectionClosed(event);
      }

   }

   /**
    * Gets the log writer for this ManagedConnection instance.
    *
    * @return Character output stream associated with this Managed-Connection instance
    * @throws ResourceException generic exception if operation fails
    */
   public PrintWriter getLogWriter() throws ResourceException
   {
      log.finest("getLogWriter()");
      return logwriter;
   }

   /**
    * Sets the log writer for this ManagedConnection instance.
    *
    * @param out Character Output stream to be associated
    * @throws ResourceException  generic exception if operation fails
    */
   public void setLogWriter(PrintWriter out) throws ResourceException
   {
      log.finest("setLogWriter()");
      logwriter = out;
   }

   /**
    * Returns an <code>javax.resource.spi.LocalTransaction</code> instance.
    *
    * @return LocalTransaction instance
    * @throws ResourceException generic exception if operation fails
    */
   public LocalTransaction getLocalTransaction() throws ResourceException
   {
      log.finest("getLocalTransaction()");
      return null;
   }

   /**
    * Returns an <code>javax.transaction.xa.XAresource</code> instance. 
    *
    * @return XAResource instance
    * @throws ResourceException generic exception if operation fails
    */
   public XAResource getXAResource() throws ResourceException
   {
      log.finest("getXAResource()");
      return null;
   }

   /**
    * Gets the metadata information for this connection's underlying EIS resource manager instance. 
    *
    * @return ManagedConnectionMetaData instance
    * @throws ResourceException generic exception if operation fails
    */
   public ManagedConnectionMetaData getMetaData() throws ResourceException
   {
      log.finest("getMetaData()");
      return new Neo4jManagedConnectionMetaData();
   }

   /**
    * Call me
    */
   void callMe()
   {
      log.finest("callMe()");

   }

}
