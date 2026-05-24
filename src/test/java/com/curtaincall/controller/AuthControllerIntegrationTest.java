package com.curtaincall.controller;

import com.curtaincall.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void 이미_가입된_id로_회원가입하면_실패한다() throws Exception {

        String id = "testid_" + System.nanoTime();

        assertThat(memberMapper.isAlreadyExistedId(id)).isEqualTo(0);

        mockMvc.perform(post("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signUpJson(id,id+"1@test.com")))
                .andExpect(status().isOk());

        assertThat(memberMapper.isAlreadyExistedId(id)).isEqualTo(1);

        mockMvc.perform(post("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signUpJson(id,id+"2@test.com")))
                .andExpect(status().isConflict());

        assertThat(memberMapper.isAlreadyExistedId(id)).isEqualTo(1);

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
