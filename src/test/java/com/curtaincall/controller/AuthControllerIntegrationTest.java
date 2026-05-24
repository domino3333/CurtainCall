package com.curtaincall.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 이미_가입된_id로_회원가입하면_실패한다() throws Exception {

        String id = "testid_" + System.nanoTime();

        //같은 id로 첫 회원가입 -> 성공해야 함
        mockMvc.perform(post("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signUpJson(id, id + "1@test.com")))
                .andExpect(status().isOk());

        //같은 id로 두 번째 회원가입 -> 실패해야 함
        mockMvc.perform(post("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signUpJson(id, id + "1@test.com")))
                .andExpect(status().isConflict());

    }

    private String signUpJson(String id, String email) {
        return """
                {
                  "name": "홍길동",
                  "id": "%s",
                  "password": "12345678",
                  "email": "%s",
                  "dateOfBirth": "2000-01-01",
                  "gender": "M"
                }
                """.formatted(id, email);
    }

}
