//
// Copyright (C) 2022-2023 Catena-X Association and others. 
// 
// This program and the accompanying materials are made available under the
// terms of the Apache License 2.0 which is available at
// http://www.apache.org/licenses/.
//  
// SPDX-FileType: SOURCE
// SPDX-FileCopyrightText: 2022-2023 Catena-X Association
// SPDX-License-Identifier: Apache-2.0
//
package org.eclipse.tractusx.agents.conforming;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the standard matchmaking agent. this is not a test for conformance! +
 * It is rather a test for "exactness" such that the implementation tested can serve
 * as a conformance tool.
 */

public class MatchmakingAgentTest extends ConformingAgentTest {

    @Override
    protected int getNumberVars() {
        return 3;
    }

    @Test
    public void testMatchSkillGet() throws IOException {
        Response response =target(getPath()).queryParam("asset","urn:cx:SkillAsset#Test").request().get();
        assertTrue(response.getStatus()>=200 && response.getStatus()<300,"Successful get json request");
        testJsonResultSet(response);
    }

    @Test
    public void testMatchSkillPost() throws IOException {
        Response response =target(getPath()).queryParam("asset","urn:cx:SkillAsset#Test").request().
                post(Entity.entity(ConformingAgent.emptyJson,"application/sparql-results+json"));
        assertTrue(response.getStatus()>=200 && response.getStatus()<300,"Successful post skill request");
        testJsonResultSet(response);
    }

    @Test
    public void testMatchSkillPostXml() throws IOException {
        Response response =target(getPath()).queryParam("asset","urn:cx:SkillAsset#Test").request()
                .accept("application/sparql-results+xml")
                .post(Entity.entity(ConformingAgent.emptyXml,"application/sparql-results+xml"));
        assertTrue(response.getStatus()>=200 && response.getStatus()<300,"Successful post skill xml request");
        testXmlResultSet(response);
    }

    @Test
    public void testMatchSkillPostJsonXml() throws IOException {
        Response response =target(getPath()).queryParam("asset","urn:cx:SkillAsset#Test").request()
                .accept("application/sparql-results+json")
                .post(Entity.entity(ConformingAgent.emptyXml,"application/sparql-results+xml"));
        assertTrue(response.getStatus()>=200 && response.getStatus()<300,"Successful post skill json request");
        testJsonResultSet(response);
    }

    @Test
    public void testMatchSkillPostXmlJson() throws IOException {
        Response response =target(getPath()).queryParam("asset","urn:cx:SkillAsset#Test").request()
                .accept("application/sparql-results+xml")
                .post(Entity.entity(ConformingAgent.emptyJson,"application/sparql-results+json"));
        assertTrue(response.getStatus()>=200 && response.getStatus()<300,"Successful post skill xml request");
        testXmlResultSet(response);
    }

    @Override
    protected String getPath() {
        return "/match";
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(MatchmakingAgent.class);
    }

}
