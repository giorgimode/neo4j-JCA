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
package com.netoprise.neo4j.connection;

import com.poolingpeople.neo4j.api.boundary.Endpoint;
import com.poolingpeople.neo4j.api.boundary.MultiStatementBuilder;
import com.poolingpeople.neo4j.api.boundary.Neo4jClient;
import com.poolingpeople.neo4j.api.boundary.Statement;
import com.poolingpeople.neo4j.api.control.ResponseStreamingParser;
import com.poolingpeople.neo4j.api.control.StatementBuilder;
import com.poolingpeople.neo4j.api.control.parsing.states.StatesManager;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Neo4JConnectionImpl
 *
 * @version $Revision: $
 */
public class Neo4JConnectionImpl implements Neo4jConnection {

    Neo4jClient neo4jClient;


/*    public Neo4JConnectionImpl(Neo4jClient neo4jClient) {
//        cut = new Neo4jClient();
//        cut.endpoint = new Endpoint();
//        cut.endpoint.setUsername("neo4j");
//        cut.endpoint.setPassword("admin");
        cut.helper = new StatementBuilder();
        StatesManager statesManager = new StatesManager();
        cut.responseParser = new ResponseStreamingParser(statesManager);
        cut.endpoint.statesManager = statesManager;
        this.neo4jClient = neo4jClient;
    }*/

    public Neo4JConnectionImpl() {
        neo4jClient = new Neo4jClient();
    }

    @Override
    public List<Map<String, Object>> cypherOneColumnQuery(Statement statement) {
        return neo4jClient.cypherOneColumnQuery(statement);
    }

    @Override
    public List<Map<String, Map<String, Object>>> cypherMultipleEntityColumnsQuery(Statement statement) {
        return neo4jClient.cypherMultipleEntityColumnsQuery(statement);
    }

    @Override
    public List<Map<String, Object>> cypherParamsQuery(Statement statement) {
        return neo4jClient.cypherParamsQuery(statement);
    }

    @Override
    public Map<String, Object> cypherSingleEntityQuery(Statement statement) {
        return neo4jClient.cypherSingleEntityQuery(statement);
    }

    @Override
    public Object cypherSinglePropertyQuery(Statement statement) {
        return neo4jClient.cypherSinglePropertyQuery(statement);
    }

    @Override
    public List<List<Map<String, Object>>> cypherOneColumnQuery(MultiStatementBuilder statements) {
        return neo4jClient.cypherOneColumnQuery(statements);
    }

    @Override
    public List<List<Map<String, Map<String, Object>>>> cypherMultipleEntityColumnsQuery(MultiStatementBuilder statements) {
        return neo4jClient.cypherMultipleEntityColumnsQuery(statements);
    }

    @Override
    public List<List<Map<String, Object>>> cypherParamsQuery(MultiStatementBuilder statements) {
        return neo4jClient.cypherParamsQuery(statements);
    }

    @Override
    public List<Map<String, Object>> cypherSingleEntityQuery(MultiStatementBuilder statements) {
        return neo4jClient.cypherSingleEntityQuery(statements);
    }

    @Override
    public List<Object> cypherSinglePropertyQuery(MultiStatementBuilder statements) {
        return neo4jClient.cypherSinglePropertyQuery(statements);
    }

    @Override
    public void beginTransaction() {
        neo4jClient.beginTransaction();
    }

    @Override
    public void commitTransaction() {
        neo4jClient.commitTransaction();
    }

    @Override
    public Neo4jClient setEndpoint(Endpoint endpoint) {
        return neo4jClient.setEndpoint(endpoint);
    }

    @Override
    public void manipulativeQuery(Statement statement) {
        neo4jClient.manipulativeQuery(statement);
    }

    @Override
    public void manipulativeQuery(MultiStatementBuilder statements) {
        neo4jClient.manipulativeQuery(statements);
    }

    @Override
    public Endpoint getEndpoint() {
        return neo4jClient.getEndpoint();
    }


}
