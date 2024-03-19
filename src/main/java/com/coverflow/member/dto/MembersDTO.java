package com.coverflow.member.dto;

import com.coverflow.member.domain.Member;
import com.coverflow.member.domain.MemberStatus;
import com.coverflow.member.domain.Role;
import com.coverflow.member.domain.SocialType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MembersDTO {
    private UUID id;
    private String email;
    private String nickname;
    private String tag;
    private String age;
    private String gender;
    private int fishShapedBun;
    private MemberStatus memberStatus;
    private LocalDate createdAt;
    private LocalDate connectedAt;
    private Role role;
    private SocialType socialType;

    public static MembersDTO from(final Member member) {
        return new MembersDTO(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getTag(),
                member.getAge(),
                member.getGender(),
                member.getFishShapedBun(),
                member.getMemberStatus(),
                member.getCreatedAt().toLocalDate(),
                member.getConnectedAt().toLocalDate(),
                member.getRole(),
                member.getSocialType()
        );
    }
}
