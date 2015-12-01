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

import javax.resource.ResourceException;
import javax.resource.spi.*;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Neo4jResourceAdapter
 * 
 * @version $Revision: $
 */
@Connector(reauthenticationSupport = false, transactionSupport = TransactionSupport.TransactionSupportLevel.XATransaction)
public class Neo4jResourceAdapter implements ResourceAdapter {

	/** The logger */
	private static Logger log = Logger.getLogger("Neo4jResourceAdapter");


	/**
	 * This is called during the activation of a message endpoint.
	 * 
	 * @param endpointFactory
	 *            A message endpoint factory instance.
	 * @param spec
	 *            An activation spec JavaBean instance.
	 * @throws ResourceException
	 *             generic exception
	 */
	public void endpointActivation(MessageEndpointFactory endpointFactory,
			ActivationSpec spec) throws ResourceException {
		log.info("endpointActivation()");
	}

	/**
	 * This is called when a message endpoint is deactivated.
	 * 
	 * @param endpointFactory
	 *            A message endpoint factory instance.
	 * @param spec
	 *            An activation spec JavaBean instance.
	 */
	public void endpointDeactivation(MessageEndpointFactory endpointFactory,
			ActivationSpec spec) {
		log.info("endpointDeactivation()");
	}

	/**
	 * This is called when a resource adapter instance is bootstrapped.
	 * 
	 * @param ctx
	 *            A bootstrap context containing references
	 * @throws ResourceAdapterInternalException
	 *             indicates bootstrap failure.
	 */
	public void start(BootstrapContext ctx)
			throws ResourceAdapterInternalException {
		log.info("start()");

	}

	/**
	 * This is called when a resource adapter instance is undeployed or during
	 * application server shutdown.
	 */
	public void stop() {
		log.info("stop()");

	}

	/**
	 * This method is called by the application server during crash recovery.
	 * 
	 * @param specs
	 *            An array of ActivationSpec JavaBeans
	 * @throws ResourceException
	 *             generic exception
	 * @return An array of XAResource objects
	 */
	public XAResource[] getXAResources(ActivationSpec[] specs)
			throws ResourceException {
		log.info("getXAResources()");
		return null;
	}





}
