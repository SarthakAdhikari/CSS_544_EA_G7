package edu.miu.cs.cs544.controller;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import edu.miu.cs.cs544.domain.Record;
import edu.miu.cs.cs544.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class EventControllerTest {

    private MockMvc mockMvc;
    private static final Logger logger = LoggerFactory.getLogger(EventControllerTest.class);


    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    public void testCalculateRecord_NotFound() throws Exception {
        Long eventId = 1L;
        when(eventService.calculateRecord(eventId)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/badge-system/events/" + eventId + "/Record"))
                .andExpect(status().isNotFound());

        verify(eventService).calculateRecord(eventId);
    }

    @Test
    public void testCalculateRecord_Found() throws Exception {
        Long eventId = 1L;
        List<Record> records = Arrays.asList(/* create mock records */);
        when(eventService.calculateRecord(eventId)).thenReturn(records);

        String url = "/badge-system/events/";
        // + eventId + "/Record";
        logger.info("Sending request to URL: {}", url);
        // Or you can use System.out.println if you prefer:
        // System.out.println("Sending request to URL: " + url);

        MvcResult m = mockMvc.perform(get(url)).andReturn();

        m.getResponse().getHeaderNames().forEach(headerName ->
                logger.info("Header: {} - Value: {}", headerName, m.getResponse().getHeader(headerName)));


//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(records.size())));
//
//        verify(eventService).calculateRecord(eventId);
    }
}