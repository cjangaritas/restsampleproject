package com.restsample.web.controller;

import com.restsample.Application;
import com.restsample.TestContext;
import com.restsample.data.model.Festivity;
import com.restsample.service.FestivityService;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, TestContext.class})
@WebAppConfiguration
public class FestivityControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier(value = "festivityService")
    private FestivityService festivityServiceMock;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        Mockito.reset(festivityServiceMock);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void findAllFestivitiesShouldReturn200() throws Exception{
        // Given that
        List<Festivity> festivities = new LinkedList<>();
        festivities.add(createFestivity());
        //when
        Mockito.when(festivityServiceMock.findAll()).thenReturn(festivities);

        // validate
        mockMvc.perform(get("/festivity/findAll"))
                .andExpect(status().isOk());
        verify(festivityServiceMock, times(1)).findAll();
    }

    @Test
    public void testFindAllWithNoElementsShouldReturn404() throws Exception{
        // Given that
        List<Festivity> festivities = new LinkedList<>();
        festivities.add(createFestivity());
        //when
        Mockito.when(festivityServiceMock.findAll()).thenReturn(Collections.EMPTY_LIST);

        // validate
        mockMvc.perform(get("/festivity/findAll"))
                .andExpect(status().isNotFound());
        verify(festivityServiceMock, times(1)).findAll();
    }

    @Test
    public void testFindAllWithNoElemeentsShouldReturn404_2() throws Exception{
        // Given that
        List<Festivity> festivities = new LinkedList<>();
        festivities.add(createFestivity());
        //when
        Mockito.when(festivityServiceMock.findAll()).thenReturn(Collections.EMPTY_LIST);

        // validate
        mockMvc.perform(get("/festivity/findAll"))
                .andExpect(status().isNotFound());
        verify(festivityServiceMock, times(1)).findAll();
    }

    @Test
    public void createFestivityShouldReturn201() throws Exception{
        // Given that
        Festivity festivity = createFestivity();
        festivity.setId("aabbcc");
        //when
        Mockito.when(festivityServiceMock.create(anyObject())).thenReturn(festivity);

        // validate
        mockMvc.perform(post("/festivity")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"TEST_NAME\",\"startDate\": \"2016-01-01\"," +
                        "\"endDate\": \"2016-01-01\",\"locationName\": \"Bogota\"}"))
                .andExpect(status().isCreated());
        verify(festivityServiceMock, times(1)).create(any());



    }

    @Test
    public void updateFestivityShouldReturn200() throws Exception{
        // Given that
        Festivity festivity = createFestivity();
        festivity.setId("aabbcc");

        // when
        Mockito.when(festivityServiceMock.update(anyObject())).thenReturn(festivity);

        // validate
        mockMvc.perform(put("/festivity")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"TEST_NAME\",\"startDate\": \"2016-01-01\"," +
                        "\"endDate\": \"2016-01-01\",\"locationName\": \"Bogota\"}"))
                .andExpect(status().isOk());
        verify(festivityServiceMock, times(1)).update(any());
    }

    private Festivity createFestivity(){
        final Festivity festivity = new Festivity();
        festivity.setName("TEST_NAME");
        festivity.setStartDate(LocalDate.now());
        festivity.setEndDate(LocalDate.now());
        festivity.setLocationName("Bogota");
        return festivity;
    }
}
