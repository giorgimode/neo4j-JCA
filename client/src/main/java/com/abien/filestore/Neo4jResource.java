package com.abien.filestore;

import com.poolingpeople.neo4j.api.boundary.QueryParams;
import com.poolingpeople.neo4j.api.boundary.Statement;
import demo.org.connectorz.files.Neo4JConnectionFactory;
import demo.org.connectorz.files.Neo4jConnection;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.resource.ResourceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 *
 * @author adam bien, adam-bien.com
 */

@Path("files")
@Stateless
@Consumes(MediaType.TEXT_PLAIN)
//@Produces(MediaType.TEXT_PLAIN)
public class Neo4jResource {

    @Resource(name = "demo-jca/files")
    Neo4JConnectionFactory neo4JConnectionFactory;


    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") String id, String content) {
        List<Map<String, Object>> r=null;
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

        URI createdURI = URI.create(id);
        return Response.created(createdURI).build();
    }

    @GET
    @Path("{id}")
    public List<Map<String, Object>> fetch(@PathParam("id") String id) {
        List<Map<String, Object>> r=null;
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

//    @DELETE
//    @Path("{id}")
//    public void delete(@PathParam("id") String id) {
//        try (Bucket bucket = bucketStore.getBucket();) {
//            bucket.delete(id);
//        }
//    }
}

