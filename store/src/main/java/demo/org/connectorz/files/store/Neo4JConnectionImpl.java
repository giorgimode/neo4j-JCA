/*
 Copyright 2012 Adam Bien, adam-bien.com

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package demo.org.connectorz.files.store;

import com.poolingpeople.neo4j.api.boundary.Endpoint;
import com.poolingpeople.neo4j.api.boundary.MultiStatementBuilder;
import com.poolingpeople.neo4j.api.boundary.Neo4jClient;
import com.poolingpeople.neo4j.api.boundary.Statement;
import demo.org.connectorz.files.Neo4jConnection;

import java.util.List;
import java.util.Map;

public class Neo4JConnectionImpl implements Neo4jConnection {

    Neo4jClient neo4jClient;
    private String rootDirectory;

    public Neo4JConnectionImpl() {
        neo4jClient = new Neo4jClient();
       // this.rootDirectory = rootDirectory;

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
    public List<List<Map<String, Map<String, Object>>>> cypherMultipleEntityColumnsQuery(MultiStatementBuilder
                                                                                                     statements) {
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

    @Override
    public String toString() {
        return "FileBucket{" + "rootDirectory=" + rootDirectory + "}";
    }

    @Override
    public void close() throws Exception {

    }
}
