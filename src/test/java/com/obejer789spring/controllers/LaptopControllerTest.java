package com.obejer789spring.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LaptopControllerTest {

    @Autowired
    LaptopController laptopController;

    @Test
    public void contextLoad() throws Exception{
        assertThat(laptopController).isNotNull();
    }
}