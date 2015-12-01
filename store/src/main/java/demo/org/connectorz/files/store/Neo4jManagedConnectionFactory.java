/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat Middleware LLC, and individual contributors
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
package demo.org.connectorz.files.store;

import demo.org.connectorz.files.Neo4JConnectionFactory;
import demo.org.connectorz.files.Neo4jConnection;

import javax.resource.ResourceException;
import javax.resource.spi.*;
import javax.security.auth.Subject;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Neo4jManagedConnectionFactory
 *
 * @version $Revision: $
 */
@ConnectionDefinition(connectionFactory = Neo4JConnectionFactory.class,
        connectionFactoryImpl = Neo4JConnectionFactoryImpl.class,
        connection = Neo4jConnection.class,
        connectionImpl = Neo4JConnectionImpl.class)
public class Neo4jManagedConnectionFactory implements ManagedConnectionFactory, Serializable {

    /**
     * The serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * The resource adapter
     */
    private Neo4jResourceAdapter ra;

    /**
     * The logwriter
     */
    private PrintWriter logwriter;

    private int connectionsCreated = 0;

    private String rootDirectory;

    /**
     * Default constructor
     */
    public Neo4jManagedConnectionFactory() {
        this.logwriter = new PrintWriter(System.out);
    }

    // @Min(1)
    @ConfigProperty(defaultValue = "/tmp/graphdb", supportsDynamicUpdates = true, description = "The root folder of the file store")
    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public String getRootDirectory() {
        return rootDirectory;
    }

    /**
     * Creates a Connection Factory instance.
     *
     * @param cxManager ConnectionManager to be associated with created EIS connection
     *                  factory instance
     * @return EIS-specific Connection Factory instance or
     * javax.resource.cci.ConnectionFactory instance
     * @throws ResourceException Generic exception
     */
    public Object createConnectionFactory(ConnectionManager cxManager) throws ResourceException {
        logwriter.append("createConnectionFactory()");
        return new Neo4JConnectionFactoryImpl(this, cxManager);
    }

    /**
     * Creates a Connection Factory instance.
     *
     * @return EIS-specific Connection Factory instance or
     * javax.resource.cci.ConnectionFactory instance
     * @throws ResourceException Generic exception
     */
    public Object createConnectionFactory() throws ResourceException {
//		throw new ResourceException(
//				"This resource adapter doesn't support non-managed environments");
        return new Neo4JConnectionFactoryImpl(this, null);
    }

    /**
     * Creates a new physical connection to the underlying EIS resource manager.
     *
     * @param subject       Caller's security information
     * @param cxRequestInfo Additional resource adapter specific connection request
     *                      information
     * @return ManagedConnection instance
     * @throws ResourceException generic exception
     */
    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        logwriter.append("createManagedConnection()");

        return new Neo4jManagedConnection(this.rootDirectory, this, cxRequestInfo);
    }

    /**
     * Returns a matched connection from the candidate set of connections.
     *
     * @param connectionSet Candidate connection set
     * @param subject       Caller's security information
     * @param cxRequestInfo Additional resource adapter specific connection request
     *                      information
     * @return ManagedConnection if resource adapter finds an acceptable match
     * otherwise null
     * @throws ResourceException generic exception
     */
    public ManagedConnection matchManagedConnections(Set connectionSet, Subject subject, ConnectionRequestInfo
			cxRequestInfo) throws ResourceException {
        logwriter.append("matchManagedConnections()");
        ManagedConnection result = null;
        Iterator it = connectionSet.iterator();
        while (result == null && it.hasNext()) {
            ManagedConnection mc = (ManagedConnection) it.next();
            if (mc instanceof Neo4jManagedConnection) {
                result = mc;
            }

        }
        return result;
    }

    /**
     * Get the log writer for this ManagedConnectionFactory instance.
     *
     * @return PrintWriter
     * @throws ResourceException generic exception
     */
    public PrintWriter getLogWriter() throws ResourceException {
        logwriter.append("getLogWriter()");
        return logwriter;
    }

    /**
     * Set the log writer for this ManagedConnectionFactory instance.
     *
     * @param out PrintWriter - an out stream for error logging and tracing
     * @throws ResourceException generic exception
     */
    public void setLogWriter(PrintWriter out) throws ResourceException {
        logwriter.append("setLogWriter()");
        logwriter = out;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.rootDirectory);
        return hash;
    }

    /**
     * Indicates whether some other object is equal to this one.
     *
     * @param obj The reference object with which to compare.
     * @return true if this object is the same as the obj argument, false
     * otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Neo4jManagedConnectionFactory other = (Neo4jManagedConnectionFactory) obj;
        if (!Objects.equals(this.rootDirectory, other.rootDirectory)) {
            return false;
        }
        return true;
    }

}
