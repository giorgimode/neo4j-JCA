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
package demo.org.connectorz.api;

import com.poolingpeople.neo4j.api.boundary.Endpoint;
import com.poolingpeople.neo4j.api.boundary.MultiStatementBuilder;
import com.poolingpeople.neo4j.api.boundary.Neo4jClient;
import com.poolingpeople.neo4j.api.boundary.Statement;

import java.util.List;
import java.util.Map;

/**
 * Neo4jConnection
 *
 * @version $Revision: $
 */
public interface Neo4jConnection
{
	/**
	 * Call me
	 */
	public void callMe();
	/**
	 * Close
	 */
	public void close();
	List<Map<String, Object>> cypherOneColumnQuery(Statement statement);

	List<Map<String, Map<String, Object>>> cypherMultipleEntityColumnsQuery(Statement statement);

	List<Map<String, Object>> cypherParamsQuery(Statement statement);

	Map<String, Object> cypherSingleEntityQuery(Statement statement);

	Object cypherSinglePropertyQuery(Statement statement);

	List<List<Map<String, Object>>> cypherOneColumnQuery(MultiStatementBuilder statements);

	List<List<Map<String, Map<String, Object>>>> cypherMultipleEntityColumnsQuery(MultiStatementBuilder statements);

	List<List<Map<String, Object>>> cypherParamsQuery(MultiStatementBuilder statements);

	List<Map<String, Object>> cypherSingleEntityQuery(MultiStatementBuilder statements);

	List<Object> cypherSinglePropertyQuery(MultiStatementBuilder statements);

	void beginTransaction();

	void commitTransaction();

	Neo4jClient setEndpoint(Endpoint endpoint);

	void manipulativeQuery(Statement statement);

	void manipulativeQuery(MultiStatementBuilder statements);
	Endpoint getEndpoint();
}
