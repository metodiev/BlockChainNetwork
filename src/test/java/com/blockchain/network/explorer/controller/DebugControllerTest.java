package com.blockchain.network.explorer.controller;

import com.blockchain.network.explorer.controller.BlockController;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


public class DebugControllerTest {

        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        //exist test - check if the controller exist
        @Autowired
        private DebugController debugController;

        @Test
        public void debugController() throws Exception {
            assertThat(debugController).isNotNull();
        }

        //check the http message from the controller
        @Test
        public void defaultDebugMessage() throws Exception {
            assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/debug",
                    String.class)).contains("block");
        }


        @Test
        public void defaultNodeMessate() throws Exception {
            assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/debug", String.class)).isNotEmpty();
        }

    }


