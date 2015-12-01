package com.abien.filestore;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(CdiRunner.class)
public class Neo4jResourceIT {

    @Inject
    FilesResource2 filesResource2;

    @Before
    public void init() {

    }

    @Test
    public void crud() {
        System.out.println(filesResource2);
        filesResource2.fetch();
    }
}
