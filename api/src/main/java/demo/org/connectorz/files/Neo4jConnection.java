package demo.org.connectorz.files;

import com.poolingpeople.neo4j.api.boundary.Endpoint;
import com.poolingpeople.neo4j.api.boundary.MultiStatementBuilder;
import com.poolingpeople.neo4j.api.boundary.Neo4jClient;
import com.poolingpeople.neo4j.api.boundary.Statement;

import java.util.List;
import java.util.Map;

public interface Neo4jConnection extends AutoCloseable{

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
