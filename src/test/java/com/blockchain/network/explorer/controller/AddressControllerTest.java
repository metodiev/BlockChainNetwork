package com.blockchain.network.explorer.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    //exist test - check if the controller exist
    @Autowired
    private AddressController addressController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(addressController).isNotNull();
    }
}
