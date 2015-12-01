package com.abien.filestore;

import com.poolingpeople.neo4j.api.boundary.QueryParams;
import com.poolingpeople.neo4j.api.boundary.Statement;
import demo.org.connectorz.files.Neo4JConnectionFactory;
import demo.org.connectorz.files.Neo4jConnection;

import javax.annotation.Resource;
import javax.resource.ResourceException;
import java.util.List;
import java.util.Map;

public class FilesResource2 {

    @Resource(name = "demo-jca/files")
    Neo4JConnectionFactory neo4JConnectionFactory;

    public List<Map<String, Object>> fetch() {
        List<Map<String, Object>> r = null;
        try (Neo4jConnection bucket = neo4JConnectionFactory.getConnection();) {
            bucket.beginTransaction();

            String query = "CREATE (n:c{start:{start}}), (m:c{end:{end}}) return count(n) as total";
            QueryParams params = new QueryParams().add("start", 1).add("end", 2);

            r = bucket.cypherParamsQuery(new Statement(query, params));
            bucket.commitTransaction();

            bucket.commitTransaction();
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;
    }


}


