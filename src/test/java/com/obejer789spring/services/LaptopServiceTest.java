package com.obejer789spring.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obejer789spring.controllers.LaptopController;
import com.obejer789spring.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(LaptopController.class)
class LaptopServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LaptopService laptopService;

    @Autowired
    private ObjectMapper objectMapper;

    private Laptop laptop;

    private static final String url = "http://127.0.0.1:8081/api/laptops";

    @BeforeEach
    public void init(){
        laptop = new Laptop(999999999L,"MarcaPrueba","ModeloPrueba");
    }

    @Test
    void findAll() throws Exception {
        this.mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception{
        when(laptopService.findById(laptop.getId())).thenReturn(ResponseEntity.ok(laptop));

        this.mockMvc.perform(get(url+"/{id}",laptop.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(laptop.getId().intValue())))
                .andExpect(jsonPath("$.marca", is(laptop.getMarca())))
                .andExpect(jsonPath("$.modelo", is(laptop.getModelo())));
    }

    @Test
    void save() throws Exception{
        Laptop laptop1 = new Laptop("MarcaPrueba","ModeloPrueba");
        this.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(laptop1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(laptop1.getId().intValue())))
                .andExpect(jsonPath("$.marca",is(laptop1.getMarca())))
                .andExpect(jsonPath("$.modelo", is(laptop1.getModelo())));
    }

    @Test
    void updateIdNull() throws Exception{
        Laptop laptop1 = new Laptop();

        this.mockMvc.perform(put(url)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNoExistId() throws Exception{
        this.mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(laptop)))
                .andExpect(status().isNotFound());
    }


    @Test
    void deleteNoExistId() throws Exception {
        this.mockMvc.perform(delete(url+"/{id}", laptop.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAll() throws Exception{
        this.mockMvc.perform(delete(url+"/deleteall"))
                .andExpect(status().isOk());
    }
}