package com.example.member_invitation.controller;

import com.example.member_invitation.dto.CreateInviteCode;
import com.example.member_invitation.service.InvitationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvitationRestController.class)
class InvitationRestControllerTest {

    @MockBean
    private InvitationService invitationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // controller 테스트
    @Test
    void successCreateAccount() throws Exception {
        // given
        CreateInviteCode.Request requestDto;
        requestDto = new CreateInviteCode.Request();
        requestDto.setInviteMemberName("John Doe");
        requestDto.setInviteMemberPhoneNumber("123-456-7890");
        requestDto.setInviteMemberEmail("johndoe@example.com");
        requestDto.setInviteGroupId(1234L);

        // when
        String expectedString = " sample code " ;
        given(invitationService.createInviteCode(any(CreateInviteCode.Request.class)))
                .willReturn(expectedString);
        // then
        mockMvc.perform(post("/invite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
                .andDo(print()); // print 해주는 메서드
    }
}