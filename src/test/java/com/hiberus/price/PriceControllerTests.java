package com.hiberus.price;

import com.google.gson.Gson;

import com.hiberus.price.model.Destination;
import com.hiberus.price.model.Transport;
import com.hiberus.price.model.dto.PostPriceRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PriceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    Gson gson = new Gson();

    @Test
    public void postAPrice() throws Exception {
        PostPriceRequestDto postPriceRequestDto = PostPriceRequestDto.builder()
                .destination(Destination.MADRID)
                .transport(Transport.TRAIN)
                .price(40.99)
                .build();

        mockMvc.perform(post("/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(postPriceRequestDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void postAndGetAPrice() throws Exception {
        PostPriceRequestDto postPriceRequestDto = PostPriceRequestDto.builder()
                .destination(Destination.MADRID)
                .transport(Transport.TRAIN)
                .price(40.99)
                .build();

        mockMvc.perform(post("/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(postPriceRequestDto)));

        mockMvc.perform(get("/price" +
                        "?destination=" + "MADRID" +
                        "&transport=" + "TRAIN"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(postPriceRequestDto)));
    }

    @Test
    public void getAPriceWithBadEnum() throws Exception {
        mockMvc.perform(get("/price" +
                        "?destination=" + "BERLÍN" +
                        "&transport=" + "CAMELLO"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAPriceWithEmptyDestination() throws Exception {
        mockMvc.perform(get("/price" +
                        "?destination=" + "" +
                        "&transport=" + "TRAIN"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAPriceWithEmptyTransport() throws Exception {
        mockMvc.perform(get("/price" +
                        "?destination=" + "MADRID" +
                        "&transport=" + ""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAPriceWithEmptyDestinationAndTransport() throws Exception {
        mockMvc.perform(get("/price" +
                        "?destination=" + "" +
                        "&transport=" + ""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAPriceThatDoesNotExist() throws Exception {
        mockMvc.perform(get("/price" +
                        "?destination=" + "MADRID" +
                        "&transport=" + "AIRPLANE"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
