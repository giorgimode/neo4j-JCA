package com.abien.filestore;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author adam bien, adam-bien.com
 */
public class Neo4jResourceIT {
    private Client client;
    private WebResource files;

    @Before
    public void init(){
        this.client = Client.create();
        this.files = this.client.resource("http://localhost:8080/demo-jca-file-client/v1/").path("files");

    }

    @Test
    public void crud() {
        String key = "duke1";
        final String origin = "hey joe";
        this.files.path(key).put(origin);
        String fetched = this.files.path(key).get(String.class);
        assertThat(fetched,is(origin));
//        this.files.path(key).delete();
//        ClientResponse response = this.files.path(key).get(ClientResponse.class);
//        assertThat(response.getStatus(), is(204));
    }
}
