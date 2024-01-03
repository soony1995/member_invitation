package com.example.member_invitation.dto;

import com.example.member_invitation.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import java.util.Optional;

public class GetMember {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private long id;
        private String name;
        private String phoneNumber;
        private String email;

        public static Response from(final Optional<Member> optionalMember) {
            if (optionalMember.isEmpty()) {
                throw new EntityNotFoundException("Member not found");
            }
            Member member = optionalMember.get();
            return Response.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .phoneNumber(member.getPhoneNumber())
                    .email(member.getEmail())
                    .build();
        }
    }
}