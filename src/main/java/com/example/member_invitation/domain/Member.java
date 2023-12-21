package com.example.member_invitation.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    private String name;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @PrePersist
    void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        lastModifiedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        lastModifiedAt = LocalDateTime.now();
    }
}
