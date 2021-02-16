package com.ysjleader.weightlog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysjleader.weightlog.dto.request.SignUpRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void badInputTest() throws Exception {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO("ysjleader", "ysjleader@gmail.com", "1234");
        String content = objectMapper.writeValueAsString(signUpRequestDTO);
        int statusCode = mockMvc.perform(post("/member/signUp").content(content).contentType("application/json")).andReturn().getResponse().getStatus();
        assertEquals(400, statusCode);

        signUpRequestDTO = new SignUpRequestDTO("ysjleader", "ysjleader@gmail.com", "12345678");
        content = objectMapper.writeValueAsString(signUpRequestDTO);
        MockHttpServletResponse response = mockMvc.perform(post("/member/signUp").content(content).contentType("application/json")).andReturn().getResponse();
        statusCode = response.getStatus();
        String result = response.getContentAsString();
        assertEquals(200, statusCode);
        assertTrue(result.contains(signUpRequestDTO.getUserID()));
        assertTrue(result.contains(signUpRequestDTO.getEmail()));
    }
}
