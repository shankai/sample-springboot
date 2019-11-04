package io.github.shankai.springboot.restunittest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * AuthnControllerTester
 */
public class AuthnControllerTester extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testAuthn() throws Exception {
        String uri = "/authn";

        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.post(uri).header("Authorization", "Basic YWRtaW46cHdk")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        HashMap<String, Object> result = mapFromJson(content, HashMap.class);
        assertEquals("admin", result.get("id"));

    }
}