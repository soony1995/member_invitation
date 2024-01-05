package com.example.member_invitation.controller;

import com.example.member_invitation.dto.CreateInviteCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class InvitationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // controller 테스트
    @Test
    void successCreateInviteCode() throws Exception {
        CreateInviteCode.Request requestDto = new CreateInviteCode.Request();
        requestDto.setInviteMemberName("John Doe");
        requestDto.setInviteMemberPhoneNumber("123-456-7890");
        requestDto.setInviteMemberEmail("johndoe@example.com");
        requestDto.setInviteGroupId(1234L);

        mockMvc.perform(post("/invite")
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print()) // api 수행내역 로그 출력
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.inviteCode").exists()) // inviteCode 필드 존재 여부 검증
                .andExpect(jsonPath("$.inviteCode").isString()); // inviteCode 필드 타입이 문자열인지 검증
//                .andExpect(jsonPath("$.inviteCode").value("fdsfasd"));
    }

    @Test
    @DisplayName("notFound")
    void notFoundAcceptCode() throws Exception {
        String code = "123123";
        mockMvc.perform(get("/accept/"+code))
                .andDo(print()) // api 수행내역 로그 출력
                .andExpect(status().isNotFound());
//                .andExpect(jsonPath("method").value("GET"))
//                .andExpect(jsonPath("$.msg").exists()) // inviteCode 필드 존재 여부 검증
//                .andExpect(jsonPath("$.msg").value("초대 코드 생성이 완료되었습니다.")); // inviteCode 필드 타입이 문자열인지 검증
    }
}