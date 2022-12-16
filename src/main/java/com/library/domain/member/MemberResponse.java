package com.library.domain.member;

import com.library.status.YnStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberResponse {

    // 회원 member 변수
    private Long id;                        // PK
    private MemberType type;                // 회원 유형
    private String loginId;                 // 이메일(ID)
    private String name;                    // 회원 이름
    private String profileImage;            // 프로필 이미지
    private String authorityYn;             // 정회원 승인여부
    private YnStatus deleteYn;              // 삭제 여부
    private LocalDateTime createdDate;      // 회원가입 시간
    private LocalDateTime modifiedDate;     // 회원 데이터 수정일

    // 정회원 member 변수
    private String phone;                   // 연락처

    public MemberResponse(Member entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.loginId = entity.getLoginId();
        this.name = entity.getName();
        this.profileImage = entity.getProfileImage();
        this.authorityYn = entity.getAuthorityYn();
        this.deleteYn = entity.getDeleteYn();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();

        if (entity.getType() == MemberType.REGULAR) {
            this.phone = phone;
        }
    }

}
