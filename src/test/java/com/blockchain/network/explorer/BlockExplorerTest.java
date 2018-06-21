package com.blockchain.network.explorer;

import com.blockchain.network.explorer.BlockController;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BlockExplorerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    //exist test - check if the controller exist
    @Autowired
    private BlockController blockController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(blockController).isNotNull();
    }

    //check the http message from the controller
    @Test
    public void defaultBlockMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/block",
                String.class)).contains("block");
    }

    @Test
    public void defaultBlockNode() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/node",
                String.class)).contains("this is the node par");
    }

//this is with the mock
    /*@Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }*/
}